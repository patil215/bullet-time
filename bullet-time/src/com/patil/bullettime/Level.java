package com.patil.bullettime;

import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.math.Vector2;

public class Level {

	private int width;
	private int height;
	private Wall[][] walls;

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Wall[][] getWalls() {
		return walls;
	}

	public void setWalls(Wall[][] walls) {
		this.walls = walls;
	}

	public Level() {
		loadDemoLevel();
	}

	public Wall get(int x, int y) {
		return walls[x][y];
	}

	private void loadDemoLevel() {
		width = 10;
		height = 7;
		walls = new Wall[width][height];
		for (int col = 0; col < width; col++) {
			for (int row = 0; row < height; row++) {
				walls[col][row] = null;
			}
		}

		for (int col = 0; col < 10; col++) {
			walls[col][0] = new Wall(new Vector2(col, 0));
			walls[col][6] = new Wall(new Vector2(col, 6));
			if (col > 2) {
				walls[col][1] = new Wall(new Vector2(col, 1));
			}
		}
		
		
		walls[0][2] = new Wall(new Vector2(0, 2));
		walls[0][3] = new Wall(new Vector2(0, 3));
		walls[0][4] = new Wall(new Vector2(0, 4));
		walls[0][5] = new Wall(new Vector2(0, 5));
		walls[0][1] = new Wall(new Vector2(0, 1));
		
		walls[9][2] = new Wall(new Vector2(9, 2));
		walls[9][3] = new Wall(new Vector2(9, 3));
		walls[9][4] = new Wall(new Vector2(9, 4));
		walls[9][5] = new Wall(new Vector2(9, 5));

		walls[6][3] = new Wall(new Vector2(6, 3));
		walls[6][4] = new Wall(new Vector2(6, 4));
		walls[6][5] = new Wall(new Vector2(6, 5));
	}
}