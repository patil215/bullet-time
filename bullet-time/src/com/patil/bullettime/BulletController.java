package com.patil.bullettime;

import java.util.ArrayList;

public class BulletController {
	private boolean d = true;

	public BulletController() {

	}

	public void update(float delta, ArrayList<Bullet> bullets) {
		for (int i = 0; i < bullets.size(); i++) {
			Bullet bullet = bullets.get(i);
			bullet.position.x += bullet.xSpeed;
			bullet.position.y += bullet.ySpeed;
			if (d) {
				System.out.println(bullet.xSpeed / bullet.ySpeed);
				d	 = false;
			}
		}

	}
	
	public void checkCollisionWithWalls(ArrayList<Bullet> bullets, ArrayList<Wall> walls) {
		for(int i = 0; i < bullets.size(); i++) {
			Bullet bullet = bullets.get(i);
			for(int d = 0; d < walls.size(); d++) {
				Wall wall = walls.get(d);
				if(bullet.bounds.x > wall.bounds.x){
					bullet.xSpeed *= -1;
				}
				if(bullet.bounds.x < wall.bounds.x){
					bullet.xSpeed *= -1;
				}
			}
		}
	}
}
