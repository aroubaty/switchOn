package com.switchOn.assets;

import java.io.IOException;
import java.util.Iterator;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.switchOn.finals.Finals;
import com.switchOn.gameModele.Component;
import com.switchOn.gameModele.Segment2D;

public class XmlParser {
	private XmlReader.Element root;
	
	public String bg;
	public String used;
	public Array<Component> components;
	
	public int winBoxNb;
	public Array<Segment2D> winBox;
	
	public XmlParser(FileHandle file){
		XmlReader xml = new XmlReader();
		components = new Array<Component>();
		winBox = new Array<Segment2D>();
		
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
						crt.getInt("rotation"),
						crt.getInt("width"),
						crt.getInt("height")));
				
			}
			
			XmlReader.Element winBoxs = root.getChildByName("winBoxs");
			for(XmlReader.Element winBox : winBoxs.getChildrenByName("winBox")){
				winBoxNb = winBox.getIntAttribute("nbNeeded");
				
				for(XmlReader.Element pts : winBox.getChildrenByName("point")){
					Vector2 p1 = new Vector2(new Vector2(pts.getFloatAttribute("x1"), pts.getFloatAttribute("y1")));
					Vector2 p2 = new Vector2(new Vector2(pts.getFloatAttribute("x2"), pts.getFloatAttribute("y2")));
					this.winBox.add(new Segment2D(p1, p2));
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
