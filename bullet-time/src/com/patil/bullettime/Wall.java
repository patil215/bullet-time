package com.patil.bullettime;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Wall {

	Vector2 	position = new Vector2();
	Rectangle 	bounds = new Rectangle();
	
	int sizeX = 50;
	int sizeY = 50;

	public Wall(Vector2 pos) {
		this.position = pos;
		this.bounds.setX(pos.x);
		this.bounds.setY(pos.y);
		this.bounds.width = sizeX;
		this.bounds.height = sizeY;
	}

	public Vector2 getPosition() {
		return position;
	}

	public Rectangle getBounds() {
		return bounds;
	}
}