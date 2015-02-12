package com.switchOn.gameModele;

import com.badlogic.gdx.math.Rectangle;
import com.switchOn.finals.Finals;
import com.switchOn.finals.Finals.COMPONENT_TYPE;

public class Component {
	public Finals.COMPONENT_TYPE type;
	public int x;
	public int y;
	public int rotation;
	public int width;
	public int height;
	public Rectangle hitBox;
	
	public Component(COMPONENT_TYPE type, int x, int y, int rotation, int width, int heigth) {
		super();
		this.type = type;
		this.x = x;
		this.y = y;
		this.rotation = rotation;
		this.width = width;
		this.height = heigth;
		hitBox = new Rectangle(x, y, width, heigth);
	}
	
	public Component(){}
}
