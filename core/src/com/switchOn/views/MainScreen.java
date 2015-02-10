package com.switchOn.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.switchOn.switchOn;
import com.switchOn.assets.Assets;

public class MainScreen extends ScreenAdapter {
	private Assets assets;

	private Skin skin;
	private Stage stage;
	private Table table;

	public MainScreen() {
		super();

		assets = Assets.getInstance();
		assets.loadMainMenu();

		skin = assets.skin;

		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		table = new Table(skin);
		table.setFillParent(true);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act(delta);
		stage.draw();

	}

	@Override
	public void show() {
		// creating heading
		Label heading = new Label("Switch On", skin, "big");
		heading.setFontScale(2);

		// creating buttons
		TextButton buttonPlay = new TextButton("Play", skin, "big");
		buttonPlay.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				((Game) Gdx.app.getApplicationListener()).setScreen(new LevelMap());
			}
		});
		buttonPlay.pad(15);

		// creating buttons
		TextButton buttonExit = new TextButton("Options", skin, "big");
		buttonExit.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log("test", "Options");
			}
		});
		buttonPlay.pad(15);

		// putting stuff together
		table.add(heading).spaceBottom(100).row();
		table.add(buttonPlay).spaceBottom(15).row();
		table.add(buttonExit).spaceBottom(15).row();
		table.setBackground(new TextureRegionDrawable(new TextureRegion(
				assets.bg)));

		stage.addActor(table);
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, false);
		table.invalidateHierarchy();
	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		stage.dispose();
		skin.dispose();
	}

}
