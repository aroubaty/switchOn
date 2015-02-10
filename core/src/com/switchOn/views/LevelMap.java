package com.switchOn.views;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.switchOn.assets.Assets;
import com.switchOn.finals.Finals;

public class LevelMap extends ScreenAdapter{
	private Assets assets;

	private Skin skin;
	private Stage stage;
	private Table table;
	
	private Array<TextButton> levelButtons;

	public LevelMap() {
		super();

		assets = Assets.getInstance();
		assets.loadLevelMap();

		skin = assets.skin;

		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		table = new Table(skin);
		table.setFillParent(true);
		
		levelButtons = new Array<TextButton>();
		setButton();
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

		// putting stuff together
		for(int i = 0 ; i < levelButtons.size; i ++){
			table.add(levelButtons.get(i)).space(50).width(250);
			if ((i==2) || (i==5)) table.row();
		}
		
		table.row().row();
		
		TextButton buttonReturn = new TextButton("Return", skin, "big");
		buttonReturn.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				((Game) Gdx.app.getApplicationListener()).setScreen(new MainScreen());
			}
		});
		buttonReturn.pad(15);
		table.add(buttonReturn).space(50).row();
		
		
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
	
	private void setButton(){
		for(int i = 1; i <= Finals.MAP_PER_WORLD; i ++){
			TextButton crt = new TextButton(i + "", skin, "small");
			
			crt.addListener(new ClickListener(){
				@Override
				public void clicked(InputEvent event, float x, float y) {
					Gdx.app.log("test", "world load!");
				}
			});
			
			crt.pad(15);
			levelButtons.add(crt);
		}
	}


}
