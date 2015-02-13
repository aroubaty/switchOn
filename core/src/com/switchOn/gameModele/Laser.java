package com.switchOn.gameModele;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.switchOn.finals.Finals;

public class Laser {
	private ShapeRenderer sr;
	private Viewport viewPort;
	
	private Array<Component> compLink;
	private Array<Component> compFree;
	private Vector3 touchPoint;
	
	private int deltaLaserDiplay;
	
	public Laser(Viewport view, Array<Component> components){
		sr = new ShapeRenderer();
		this.viewPort = view;
		touchPoint = new Vector3();
		
		compLink = new Array<Component>();
		compFree = new Array<Component>();
		
		for(Component comp: components){
			if(comp.type == Finals.COMPONENT_TYPE.startLaser)
				compLink.add(comp);
			else
				compFree.add(comp);
		}
		
		Component ori = compLink.first();
		touchPoint.set(ori.x + ori.width/2, ori.y + ori.height/2, 0);
		viewPort.project(touchPoint);
		
		deltaLaserDiplay = 0;
	}
	
	public void draw(){
		Gdx.gl.glLineWidth(10);
		sr.begin(ShapeType.Line);
		sr.setColor(Color.RED);
		
		Component lastCompLink = null;
		for(Component comp : compLink){
			if(lastCompLink != null){
				Vector3 src = viewPort.project(new Vector3(lastCompLink.x + lastCompLink.width/2, lastCompLink.y + lastCompLink.height/2, 0));
				Vector3 dest = viewPort.project(new Vector3(comp.x + comp.width/2, comp.y + comp.height/2, 0));
				
				sr.line(src, dest);
			}
			
			lastCompLink = comp;
		}
		
		if(deltaLaserDiplay != 0){
			//draw laser under the touch
			Vector3 ori = viewPort.project(new Vector3(lastCompLink.x + lastCompLink.width/2, lastCompLink.y + lastCompLink.height/2, 0));
			sr.line(ori, touchPoint);
			deltaLaserDiplay--;
		}
		
		sr.end();
	}
	
	public void clear(){
		
	}
	
	public void cancelLast(){
		if(compLink.size > 1){
			compFree.add(compLink.get(compLink.size-1)); 
			compLink.removeIndex(compLink.size-1);
		}
	}

	public void update(float x, float y){
		touchPoint = viewPort.unproject(new Vector3(x, y, 0));
		
		int indexHit = -1;
		for(int i = 0; i < compFree.size; i ++){
			if(compFree.get(i).hitBox.contains(touchPoint.x, touchPoint.y)){
				indexHit = i;
				break;
			}
		}
		
		if (indexHit != -1){
			Component comp = compFree.get(indexHit);
			compFree.removeIndex(indexHit);
			compLink.add(comp);
		}
		
		deltaLaserDiplay = 5;
		viewPort.project(touchPoint);
	}
	
	public Array<Segment2D> getLaserPath(){
		Array<Segment2D> path = new Array<Segment2D>();
		
		Vector2 lastPts = null;
		for(Component comp : compLink){
			Vector2 compPts = new Vector2(comp.x + comp.width/2, comp.y + comp.height/2);
			
			if(lastPts != null){
				path.add(new Segment2D(lastPts, compPts));
			}
			
			lastPts = compPts;
			
		}
		
		return path;
	}
	
}
