package com.switchOn.gameModele;

import com.switchOn.finals.Finals;
import com.switchOn.finals.Finals.COMPONENT_TYPE;

public class Component {
	public Finals.COMPONENT_TYPE type;
	public int x;
	public int y;
	public int rotation;
	
	public Component(COMPONENT_TYPE type, int x, int y, int rotation) {
		super();
		this.type = type;
		this.x = x;
		this.y = y;
		this.rotation = rotation;
	}
}
