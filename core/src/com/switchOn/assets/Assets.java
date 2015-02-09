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

/*

freetype 
https://github.com/libgdx/libgdx/wiki/Gdx-freetype

exemple
https://bitbucket.org/dermetfan/blackpoint2/src/21c010c6da76113ff4f7a374743626483b81c23a/Blackpoint2-android/assets/font/?at=default

app
https://github.com/Leakedbits/Codelabs

*/
