package com.switchOn.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class Assets {
	private static Assets instance;
	
	public static Assets getInstance(){
		if (instance == null)
				instance = new Assets();
		
		return instance;
	}
	
	
	public Texture bg;
	public Texture b1;
	public Texture b2;
	
	private Assets(){
	}
	
	public void load(){
		bg = loadTexture("background/bg1.png");
		b1 = loadTexture("button/test1.png");
		b2 = loadTexture("button/test2.png");
	}
	
	public static Texture loadTexture (String file) {
		return new Texture(Gdx.files.internal(file));
	}

	
}
