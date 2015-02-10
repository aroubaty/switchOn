package com.switchOn.assets;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.*;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;


public class Assets {
	private static Assets instance;
	
	public static Assets getInstance(){
		if (instance == null)
				instance = new Assets();
		
		return instance;
	}
	
	
	public Texture bg;
	public BitmapFont font12;
	public Skin skin;
	
	private Assets(){
	}
	
	public void loadMainMenu(){
		bg = loadTexture("background/bgMenu.png");	
		
		// Generate font
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/Calibri.ttf"));
		FreeTypeFontParameter param = new FreeTypeFontParameter();
		
		param.size = 50;
		BitmapFont caliHead = generator.generateFont(param); 
		
		param.size = 44;
		BitmapFont caliButton = generator.generateFont(param); 
		
		generator.dispose();
		
		skin = new Skin();
		skin.add("caliHead", caliHead, BitmapFont.class);
		skin.add("caliButton", caliButton, BitmapFont.class);
		skin.addRegions(new TextureAtlas(Gdx.files.internal("pack/mainMenu.pack")));
		skin.load(Gdx.files.internal("pack/mainMenu.json"));
		
	}
	
	public void loadLevelMap(){
		bg = loadTexture("background/bgMenu.png");	
		
		// Generate font
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/Calibri.ttf"));
		FreeTypeFontParameter param = new FreeTypeFontParameter();
		
		param.size = 50;
		BitmapFont caliHead = generator.generateFont(param); 
		
		param.size = 25;
		BitmapFont caliButton = generator.generateFont(param); 
		
		generator.dispose();
		
		skin = new Skin();
		skin.add("caliHead", caliHead, BitmapFont.class);
		skin.add("caliButton", caliButton, BitmapFont.class);
		skin.addRegions(new TextureAtlas(Gdx.files.internal("pack/mainMenu.pack")));
		skin.load(Gdx.files.internal("pack/mainMenu.json"));
	}
	
	public static Texture loadTexture (String file) {
		return new Texture(Gdx.files.internal(file));
	}

	
}
