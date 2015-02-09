package com.switchOn.views;


import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.switchOn.switchOn;
import com.switchOn.assets.Assets;
import com.switchOn.finals.Finals;

public class MainScreen extends ScreenAdapter{
	private switchOn game;
	private SpriteBatch batch;
	private OrthographicCamera guiCam;
	private Assets assets;
	
	private Rectangle hitBox;
	private Vector3 touchPoint;
	
	private boolean other = false;

	public MainScreen(switchOn game) {
		super();
		this.game = game;
		batch = game.getBatch();
		
		assets = Assets.getInstance();
		assets.load();
		
		guiCam = new OrthographicCamera(Finals.SCREEN_WIDTH, Finals.SCREEN_HEIGHT);
		guiCam.position.set(Finals.SCREEN_WIDTH / 2, Finals.SCREEN_HEIGHT / 2, 0);
		hitBox = new Rectangle(100, 100, 100, 100);
		touchPoint = new Vector3();
		
	}
	
	public void update () {
		other = false;
		if (Gdx.input.isTouched()) {
			guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

			if (hitBox.contains(touchPoint.x, touchPoint.y)) {
				other = true;
				return;
			}
		}
	}

	public void draw () {
		GL20 gl = Gdx.gl;
		gl.glClearColor(1, 0, 0, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		guiCam.update();
		
		batch.setProjectionMatrix(guiCam.combined);

		batch.disableBlending();
		batch.begin();
		batch.draw(assets.bg, 0, 0, Finals.SCREEN_WIDTH, Finals.SCREEN_WIDTH);
		batch.end();
		
		
		batch.enableBlending();
		batch.begin();
		
		batch.draw(other ? assets.b1 : assets.b2, 100, 100, 100, 100);
		
		batch.end();
	}
	
	@Override
	public void render (float delta) {
		update();
		draw();
	}

	@Override
	public void pause () {
		//Settings.save();
	}
	
	
}
