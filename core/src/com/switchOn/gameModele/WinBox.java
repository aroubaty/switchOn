package com.switchOn.gameModele;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;

public class WinBox {
	private ShapeRenderer sr;
	private Viewport viewPort;
	
	private Array<Vector2> points;
	private int nbNeeded;
	private int currentInter;

	public WinBox(Viewport view, int nbNeeded, Array<Vector2> points){
		viewPort = view;
		sr = new ShapeRenderer();
		this.points = points;
		this.nbNeeded = nbNeeded;	
	}
	
	public void draw(){
		
	}
	
	public void update(Laser laser){
		currentInter = 0;
		Array<Vector2> pathLaser = laser.getLaserPath();
		Vector2 lastPts = pathLaser.first();
		
		for(Vector2 pts : pathLaser){
			checkLine(lastPts, pts);
			lastPts = pts;
		}
		
	}
	
	private void checkLine(Vector2 p1, Vector2 p2){
		Vector2 lastPts = null;
		Vector2 inter = new Vector2();
		for(Vector2 pts : points) {
			if(lastPts != null){
				if(Intersector.intersectLines(p1, p2, lastPts, pts, inter)){
					currentInter++;
				}
			}
			
			lastPts = pts;
		}
	}
	
}
