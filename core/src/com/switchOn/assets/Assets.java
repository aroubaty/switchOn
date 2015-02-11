package com.switchOn.assets;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.switchOn.finals.Finals;


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
	public Map<Finals.COMPONENT_TYPE, Sprite> compLoaded;
	
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
	
	public void loadLevel(String bg, String[] compToLoad){
		this.bg = loadTexture("background/" + bg);
		compLoaded = new HashMap<Finals.COMPONENT_TYPE, Sprite>();
		
		TextureAtlas compAtlas = new TextureAtlas(Gdx.files.internal("pack/gameComponents.pack"));
		
		for(int i = 0; i < compToLoad.length; i ++){
			Finals.COMPONENT_TYPE compType = Finals.COMPONENT_TYPE.values()[Integer.parseInt(compToLoad[i])];

			Sprite newComp = compAtlas.createSprite(compType.toString());
			compLoaded.put(compType, newComp);
		}
		
		
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/Calibri.ttf"));
		FreeTypeFontParameter param = new FreeTypeFontParameter();
		param.size = 12;
		font12 = generator.generateFont(param); 
	}
	
	public static Texture loadTexture (String file) {
		return new Texture(Gdx.files.internal(file));
	}

	
}
