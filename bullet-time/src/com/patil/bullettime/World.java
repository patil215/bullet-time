package com.patil.bullettime;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class World {

	// The speed the world moves. 1 = normal, 0.5 = half, etc
	double worldSpeed;
	
	
	/** Our player controlled hero **/
	Player player;
	/** A world has a level through which Player needs to go through **/
	Level level;

	/** The collision boxes **/
	Array<Rectangle> collisionRects = new Array<Rectangle>();
	
	ArrayList<Bullet> bullets = new ArrayList<Bullet>();

	OnScreenJoyStick leftJoystick;
	OnScreenJoyStick rightJoystick;

	// Getters -----------

	public Array<Rectangle> getCollisionRects() {
		return collisionRects;
	}

	public Player getPlayer() {
		return player;
	}

	public Level getLevel() {
		return level;
	}
	
	public void addBullet(Bullet bullet) {
		bullets.add(bullet);
	}
	
	public ArrayList<Bullet> getBullets() {
		return bullets;
	}

	/** Return only the blocks that need to be drawn **/
	public List<Wall> getDrawableWalls(int width, int height) {
		int x = (int) player.getPosition().x - width;
		int y = (int) player.getPosition().y - height;
		if (x < 0) {
			x = 0;
		}
		if (y < 0) {
			y = 0;
		}
		int x2 = x + 2 * width;
		int y2 = y + 2 * height;
		if (x2 > level.getWidth()) {
			x2 = level.getWidth() - 1;
		}
		if (y2 > level.getHeight()) {
			y2 = level.getHeight() - 1;
		}

		List<Wall> blocks = new ArrayList<Wall>();
		Wall block;
		for (int col = x; col <= x2; col++) {
			for (int row = y; row <= y2; row++) {
				block = level.getWalls()[col][row];
				if (block != null) {
					blocks.add(block);
				}
			}
		}
		return blocks;
	}

	public OnScreenJoyStick getLeftJoystick() {
		return leftJoystick;
	}

	public OnScreenJoyStick getRightJoystick() {
		return rightJoystick;
	}

	// --------------------
	public World(int width, int height) {
		createDemoWorld(width, height);
	}

	private void createDemoWorld(int width, int height) {
		System.out.println(width);
		System.out.println(height);
		leftJoystick = new OnScreenJoyStick(200, 400, 50, 20);
		rightJoystick = new OnScreenJoyStick(width - 150, 400, 50, 20);
		leftJoystick.resetKnob();
		rightJoystick.resetKnob();
		player = new Player(new Vector2(7, 2));
		level = new Level();
	}
}
