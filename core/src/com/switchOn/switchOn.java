package com.switchOn;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.switchOn.views.GameScreen;
import com.switchOn.views.MainScreen;

public class switchOn extends Game {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		
		setScreen(new MainScreen());
		//setScreen(new GameScreen(101));
	}

	@Override
	public void render () {
		super.render();
	}

	public SpriteBatch getBatch() {
		return batch;
	}
	
}
