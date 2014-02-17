package com.patil.bullettime;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Bullet {
	public static final float SIZE = 0.1f;

	Vector2 position = new Vector2();
	Vector2 velocity = new Vector2();
	double xSpeed = 0;
	double ySpeed = 0;
	Rectangle bounds = new Rectangle();

	public Bullet(Vector2 position, double xSpeed, double ySpeed) {
		this.position = position;
		this.bounds.x = position.x;
		this.bounds.y = position.y;
		this.bounds.height = SIZE;
		this.bounds.width = SIZE;
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
	}

	public Vector2 getPosition() {
		return position;
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
		this.bounds.setX(position.x);
		this.bounds.setY(position.y);
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
}
