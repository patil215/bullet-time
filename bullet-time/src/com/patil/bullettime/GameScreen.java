package com.patil.bullettime;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.math.Vector2;

public class GameScreen implements Screen, InputProcessor {

	private World world;
	private WorldRenderer renderer;
	private PlayerController controller;
	private BulletController bulletController;

	private int width, height;

	@Override
	public void show() {
		world = new World(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		renderer = new WorldRenderer(world, false);
		bulletController = new BulletController();
		controller = new PlayerController(world);
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		controller.update(delta);
		bulletController.update(delta, world.getBullets());
		renderer.render();
	}

	@Override
	public void resize(int width, int height) {
		renderer.setSize(width, height);
		this.width = width;
		this.height = height;
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
	}

	@Override
	public void dispose() {
		Gdx.input.setInputProcessor(null);
	}

	// * InputProcessor methods ***************************//

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.A)
			controller.leftPressed();
		if (keycode == Keys.D)
			controller.rightPressed();
		if (keycode == Keys.W)
			controller.jumpPressed();
		if (keycode == Keys.S)
			controller.firePressed();
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.A)
			controller.leftReleased();
		if (keycode == Keys.D)
			controller.rightReleased();
		if (keycode == Keys.W)
			controller.jumpReleased();
		if (keycode == Keys.X)
			controller.fireReleased();
		if (keycode == Keys.X)
			renderer.setDebug(!renderer.isDebug());
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		if (Gdx.app.getType().equals(ApplicationType.Android)) {
			if (x < width / 2) {
				handleLeftJoystick(x, y);
			}
			if (x > width / 2) {
				handleRightJoystick(x, y);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		if (Gdx.app.getType().equals(ApplicationType.Android)) {
			if (x < width / 2) {
				controller.leftReleased();
				controller.rightReleased();
				controller.jumpReleased();
				world.getLeftJoystick().resetKnob();
			}
			if (x > width / 2) {
				world.getRightJoystick().resetKnob();
			}
			return true;
		} else {
			float distX = x - (controller.player.getPosition().x);
			float distY = (height - y) - (controller.player.getPosition().y);
			double distHypotenuse = Math.sqrt(Math.pow(distX, 2)
					+ Math.pow(distY, 2));
			double ratio = 0.1 / distHypotenuse;
			double bulletX = (distX * ratio);
			double bulletY = (distY * ratio);
			System.out.println(x + " " + y);
			System.out.println(controller.player.getPosition().x + " " + controller.player.getPosition().y);
			System.out.println();
			Bullet bullet = new Bullet(new Vector2(
					controller.player.getPosition().x,
					controller.player.getPosition().y), bulletX, bulletY);
			world.addBullet(bullet);
		}
		return false;
	}

	public void handleRightJoystick(int x, int y) {
		world.getRightJoystick().updateKnobPosition(x, height - y);
		controller.rightPressed();
	}

	public void handleLeftJoystick(int x, int y) {
		OnScreenJoyStick joystick = world.getLeftJoystick();
		joystick.updateKnobPosition(x, height - y);
		if (joystick.getDrawKnobX() > joystick.getDrawOriginX()
				+ (joystick.getJoystickRadius() / 2)) {
			controller.rightPressed();
			controller.leftReleased();
		}
		if (joystick.getDrawKnobX() < joystick.getDrawOriginX()
				- (joystick.getJoystickRadius() / 2)) {
			controller.leftPressed();
			controller.rightReleased();
		}
		if (joystick.getDrawKnobY() > joystick.getDrawOriginY()
				- (joystick.getJoystickRadius())) {
			controller.jumpPressed();
		} else {
			controller.jumpReleased();
		}
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		if (!Gdx.app.getType().equals(ApplicationType.Android))
			return false;
		if (x < width / 2) {
			handleLeftJoystick(x, y);
		}
		if (x > width / 2) {
			handleRightJoystick(x, y);
		}
		return true;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

}
