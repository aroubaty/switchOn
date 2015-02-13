package com.switchOn.gameModele;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;

public class WinBox {
	private ShapeRenderer sr;
	private Viewport viewPort;

	private Array<Segment2D> lines;
	private int nbNeeded;
	private int currentInter;

	public WinBox(Viewport view, int nbNeeded, Array<Segment2D> lines) {
		viewPort = view;
		sr = new ShapeRenderer();
		this.lines = lines;
		this.nbNeeded = nbNeeded;
	}

	public void draw() {
		Gdx.gl.glLineWidth(10);
		sr.begin(ShapeType.Line);
		sr.setColor(Color.GREEN);

		
		for (Segment2D line : lines){
			Vector2 p1 = viewPort.project(new Vector2(line.p1));
			Vector2 p2 = viewPort.project(new Vector2(line.p2));
			sr.line(p1, p2);
		}

		sr.end();
	}

	public void update(Laser laser) {
		currentInter = 0;
		Array<Segment2D> pathLaser = laser.getLaserPath();
		Vector2 inter = new Vector2();

		for (Segment2D laserSeg : pathLaser) {
			for (Segment2D line : lines) {
				if (Intersector.intersectLines(laserSeg.p1, laserSeg.p2, line.p1, line.p2, inter))
					currentInter++;
			}
		}
		
		currentInter /= 2;

	}


	public int getNbRemaining() {
		return nbNeeded - currentInter;
	}
}
