package com.switchOn.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.switchOn.assets.Assets;
import com.switchOn.assets.XmlParser;
import com.switchOn.finals.Finals;
import com.switchOn.gameModele.Component;

public class GameScreen extends ScreenAdapter{
	private Assets assets;
	private XmlParser xmlParse;
	
	private SpriteBatch batch;
	private Camera camera;
	private StretchViewport viewport;
	
	private String gestureInfo;
	private String gestureInfo2;
	
	
	
	public GameScreen(int levelId){
		super();
		gestureInfo = new String();
		gestureInfo2 = new String();
		
		FileHandle xmlFile = Gdx.files.internal("data/level"+levelId+".xml");
		xmlParse = new XmlParser(xmlFile);
		
		assets = Assets.getInstance();
		assets.loadLevel(xmlParse.bg, xmlParse.used.split(","));
		
		batch = new SpriteBatch();
		viewport = new StretchViewport(Finals.SCREEN_WIDTH, Finals.SCREEN_HEIGHT);
		
		camera = viewport.getCamera();
		camera.position.set(Finals.SCREEN_WIDTH / 2, Finals.SCREEN_HEIGHT / 2, 0);
		
		Gdx.input.setInputProcessor(new GestureDetector(new MyGestureListener()));
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		batch.disableBlending();
		batch.begin();
		batch.draw(assets.bg, 0, 0);
		batch.end();	
		
		batch.enableBlending();
		batch.begin();
	
		for(Component comp : xmlParse.components){
			Sprite toDraw = assets.compLoaded.get(comp.type);
			
			toDraw.setPosition(comp.x, comp.y);
			toDraw.setRotation(comp.rotation);
			toDraw.draw(batch);
		}
		
		
		assets.font12.setColor(Color.BLACK);
		assets.font12.drawMultiLine(batch, gestureInfo, 700, 700);
		assets.font12.drawMultiLine(batch, gestureInfo2, 900, 700);
		
		batch.end();	
		
	}
	
	@Override
	public void show() {
		
	}
	
	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	}
	
	
	public class MyGestureListener implements GestureListener{

	    @Override
	    public boolean touchDown(float x, float y, int pointer, int button) {
	    	gestureInfo2 = "tap : x=> " + x + " y => " + y + "\n" 
	    			+ " pointer => " + pointer + " button => " + button;
	    	
	        return false;
	    }

	    @Override
	    public boolean tap(float x, float y, int count, int button) {
	    	
	        return false;
	    }

	    @Override
	    public boolean longPress(float x, float y) {

	        return false;
	    }

	    @Override
	    public boolean fling(float velocityX, float velocityY, int button) {

	        return false;
	    }

	    @Override
	    public boolean pan(float x, float y, float deltaX, float deltaY) {
	    	gestureInfo = "pan : x=> " + x + " y => " + y + "\n" 
	    			+ " deltaX => " + deltaX + " deltaY => " + deltaY;
	    	
	        return false;
	    }

	    @Override
	    public boolean panStop(float x, float y, int pointer, int button) {
	    	
	        return false;
	    }

	    @Override
	    public boolean zoom (float originalDistance, float currentDistance){

	       return false;
	    }

	    @Override
	    public boolean pinch (Vector2 initialFirstPointer, Vector2 initialSecondPointer, Vector2 firstPointer, Vector2 secondPointer){

	       return false;
	    }
	}

}
