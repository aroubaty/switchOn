package com.switchOn.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
	
	public GameScreen(int levelId){
		super();
		
		FileHandle xmlFile = Gdx.files.internal("data/level"+levelId+".xml");
		xmlParse = new XmlParser(xmlFile);
		
		assets = Assets.getInstance();
		assets.loadLevel(xmlParse.bg, xmlParse.used.split(","));
		
		batch = new SpriteBatch();
		viewport = new StretchViewport(Finals.SCREEN_WIDTH, Finals.SCREEN_HEIGHT);
		
		camera = viewport.getCamera();
		camera.position.set(Finals.SCREEN_WIDTH / 2, Finals.SCREEN_HEIGHT / 2, 0);
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
		
		batch.end();	
		
	}
	
	@Override
	public void show() {
		
	}
	
	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	}

}
