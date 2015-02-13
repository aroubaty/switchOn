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
import com.switchOn.gameModele.Laser;
import com.switchOn.gameModele.WinBox;

public class GameScreen extends ScreenAdapter {
	// Loading classes
	private Assets assets;
	private XmlParser xmlParse;

	// Render & View classes
	private SpriteBatch batch;
	private Camera camera;
	private StretchViewport viewport;

	// Line
	private Laser laser;
	
	// winBox
	private WinBox winBox;

	// Debug classes
	private String gestureInfo;
	private String gestureInfo2;
	
	private gameStatus status;
	
	private enum gameStatus {
		running, end
	}

	public GameScreen(int levelId) {
		super();
		gestureInfo = new String();
		gestureInfo2 = new String();

		// Load XML file
		FileHandle xmlFile = Gdx.files
				.internal("data/level" + levelId + ".xml");
		xmlParse = new XmlParser(xmlFile);

		// Load asset for this level
		assets = Assets.getInstance();
		assets.loadLevel(xmlParse.bg, xmlParse.used.split(","));

		// Setup camera and viewport
		batch = new SpriteBatch();
		viewport = new StretchViewport(Finals.SCREEN_WIDTH,
				Finals.SCREEN_HEIGHT);
		camera = viewport.getCamera();
		camera.position.set(Finals.SCREEN_WIDTH / 2, Finals.SCREEN_HEIGHT / 2,
				0);
		Gdx.input
				.setInputProcessor(new GestureDetector(new MyGestureListener()));

		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		laser = new Laser(viewport, xmlParse.components);
		
		winBox = new WinBox(viewport, xmlParse.winBoxNb, xmlParse.winBox);
		
		status = gameStatus.running;
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

		// Components
		for (Component comp : xmlParse.components) {
			Sprite toDraw = assets.compLoaded.get(comp.type);

			toDraw.setPosition(comp.x, comp.y);
			toDraw.setRotation(comp.rotation);
			toDraw.draw(batch);
		}
		
		if(status == gameStatus.end)
			gestureInfo2 = "WIN !";
		
		
		assets.font12.setColor(Color.BLACK);
		assets.font12.drawMultiLine(batch, gestureInfo, 700, 700);
		assets.font12.drawMultiLine(batch, gestureInfo2, 1000, 700);

		batch.end();
		
		//winBox
		winBox.draw();
		
		// laser
		laser.draw();
		
		//Check gameStatus
		if(winBox.getNbRemaining() <= 0){
			status = gameStatus.end;
		}


	}

	@Override
	public void show() {

	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	}

	public class MyGestureListener implements GestureListener {

		@Override
		public boolean touchDown(float x, float y, int pointer, int button) {

			return false;
		}

		@Override
		public boolean tap(float x, float y, int count, int button) {
			if(status == gameStatus.running){
				laser.cancelLast();
				winBox.update(laser);
			}
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
			if(status == gameStatus.running){
				laser.update(x, y);
				winBox.update(laser);
			}
			
			return false;
		}

		@Override
		public boolean panStop(float x, float y, int pointer, int button) {

			return false;
		}

		@Override
		public boolean zoom(float originalDistance, float currentDistance) {

			return false;
		}

		@Override
		public boolean pinch(Vector2 initialFirstPointer,
				Vector2 initialSecondPointer, Vector2 firstPointer,
				Vector2 secondPointer) {

			return false;
		}
	}

}
