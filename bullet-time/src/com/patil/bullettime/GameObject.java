package com.patil.bullettime;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class GameObject {
	private Vector2 position = new Vector2(0, 0);
	private Vector2 acceleration = new Vector2(0, 0);
	private Vector2 velocity = new Vector2(0, 0);
	// Default width and height of 32
	private float width = 32;
	private float height = 32;
	private Rectangle bounds = new Rectangle(0, 0, width, height);
	
	public GameObject(float x, float y, float sizeX, float sizeY) {
		this.width = sizeX;
		this.height = sizeY;
		position.x = x;
		position.y = y;
		// Bounds at position, edges at position + size
		bounds.x = position.x;
		bounds.y = position.y;
		bounds.width = sizeX;
		bounds.height = sizeY;
	}
	
	public GameObject(float x, float y, float sizeX, float sizeY, float velocityX, float velocityY) {
		this(x, y, sizeX, sizeY);
		velocity.x = velocityX;
		velocity.y = velocityY;
	}
	
	public GameObject(float x, float y, float sizeX, float sizeY, float velocityX, float velocityY, float accelerationX, float accelerationY) {
		this(x, y, sizeX, sizeY, velocityX, velocityY);
		acceleration.x = accelerationX;
		acceleration.y = accelerationY;
	}
	
	private float getPositionX() {
		return position.x;
	}
	
	private void setPositionX(float x) {
		position.x = x;
	}
	
	private float getPositionY() {
		return position.y;
	}
	
	private void setPositionY(float y) {
		position.y = y;
	}
	
	private float getBoundsX() {
		return bounds.x;
	}
	
	private void setBoundsX(float x) {
		bounds.x = x;
	}
	
	private float getBoundsY() {
		return bounds.y;
	}
	
	private void setBoundsY(float y) {
		bounds.y = y;
	}
	
	private float getBoundsWidth() {
		return bounds.width;
	}
	
	private void setBoundsWidth(float width) {
		bounds.width = width;
	}
	
	private float getBoundsHeight() {
		return bounds.height;
	}
	
	private void setBoundsHeight(float height) {
		bounds.height = height;
	}
	
	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(float x, float y) {
		setPositionX(x);
		setPositionY(y);
		setBoundsX(x);
		setBoundsY(y);
	}
	
	private float getAccelerationX() {
		return acceleration.x;
	}
	
	private void setAccelerationX(float x) {
		acceleration.x = x;
	}
	
	private float getAccelerationY() {
		return acceleration.y;
	}
	
	public void setAccelerationY(float y) {
		acceleration.y = y;
	}

	public Vector2 getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(float accelerationX, float accelerationY) {
		setAccelerationX(accelerationX);
		setAccelerationY(accelerationY);
	}
	
	private float getVelocityX() {
		return velocity.x;
	}
	
	private void setVelocityX(float x) {
		velocity.x = x;
	}
	
	private float getVelocityY() {
		return velocity.y;
	}
	
	private void setVelocityY(float y) {
		velocity.y = y;
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public void setVelocity(float velocityX, float velocityY) {
		setVelocityX(velocityX);
		setVelocityY(velocityY);
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
		setBoundsWidth(width);
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
		setBoundsHeight(height);
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(float x, float y, float width, float height) {
		setBoundsX(x);
		setBoundsY(y);
		setBoundsWidth(width);
		setBoundsHeight(height);
	}	
}