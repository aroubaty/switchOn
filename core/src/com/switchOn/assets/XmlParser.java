package com.switchOn.assets;

import java.io.IOException;
import java.util.Iterator;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.switchOn.finals.Finals;
import com.switchOn.gameModele.Component;

public class XmlParser {
	private XmlReader.Element root;
	
	public String bg;
	public String used;
	public Array<Component> components;
	
	public XmlParser(FileHandle file){
		XmlReader xml = new XmlReader();
		components = new Array<Component>();
		
		try {
			root = xml.parse(file);
			
			bg = root.getChildByName("background").getText();
			used = root.getChildByName("used").getText();
			
			Array<XmlReader.Element> compoNode = root.getChildByName("components").getChildrenByName("component");
			Iterator<XmlReader.Element> it = compoNode.iterator();
			
			while(it.hasNext()){
				XmlReader.Element crt = it.next();
				
				components.add(new Component(
						Finals.COMPONENT_TYPE.values()[crt.getInt("id")],
						crt.getInt("x"), 
						crt.getInt("y"), 
						crt.getInt("rotation")));
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
