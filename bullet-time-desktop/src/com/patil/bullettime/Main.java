package com.patil.bullettime;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Bullet Time";
		cfg.useGL20 = false;
		cfg.width = 1200;
		cfg.height = 768;
		new LwjglApplication(new BulletTime(), cfg);
	}
}