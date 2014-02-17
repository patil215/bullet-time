package com.patil.bullettime;

import sun.rmi.runtime.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.patil.bullettime.Player.State;

public class WorldRenderer {

	private static final float CAMERA_WIDTH = 10f;
	private static final float CAMERA_HEIGHT = 7f;
	private static final float RUNNING_FRAME_DURATION = 0.06f;

	private World world;
	private OrthographicCamera cam;

	/** for debug rendering **/
	ShapeRenderer debugRenderer = new ShapeRenderer();

	/** Textures **/
	private TextureRegion playerIdleLeft;
	private TextureRegion playerIdleRight;
	private TextureRegion wallTexture;
	private TextureRegion playerFrame;
	private TextureRegion playerJumpLeft;
	private TextureRegion playerFallLeft;
	private TextureRegion playerJumpRight;
	private TextureRegion playerFallRight;

	/** Animations **/
	private Animation walkLeftAnimation;
	private Animation walkRightAnimation;

	private SpriteBatch spriteBatch;
	private boolean debug = false;
	private int width;
	private int height;
	float ppuX; // pixels per unit on the X axis
	 float ppuY; // pixels per unit on the Y axis

	public void setSize(int w, int h) {
		this.width = w;
		this.height = h;
		ppuX = (float) width / CAMERA_WIDTH;
		ppuY = (float) height / CAMERA_HEIGHT;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public WorldRenderer(World world, boolean debug) {
		this.world = world;
		this.cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
		this.cam.position.set(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f, 0);
		this.cam.update();
		this.debug = debug;
		spriteBatch = new SpriteBatch();
		loadTextures();
	}

	private void loadTextures() {
		TextureAtlas atlas = new TextureAtlas(
				Gdx.files.internal("images/textures.pack"));
		playerIdleLeft = atlas.findRegion("player-still");
		playerIdleRight = atlas.findRegion("player-still");
		playerIdleRight.flip(true, false);
		wallTexture = atlas.findRegion("wall");
		TextureRegion[] walkLeftFrames = new TextureRegion[3];
		for (int i = 0; i < walkLeftFrames.length; i++) {
			walkLeftFrames[i] = atlas.findRegion("player-left-" + (i + 1));
		}
		walkLeftAnimation = new Animation(RUNNING_FRAME_DURATION,
				walkLeftFrames);

		TextureRegion[] walkRightFrames = new TextureRegion[walkLeftFrames.length];

		for (int i = 0; i < walkRightFrames.length; i++) {
			walkRightFrames[i] = new TextureRegion(walkLeftFrames[i]);
			walkRightFrames[i].flip(true, false);
		}
		walkRightAnimation = new Animation(RUNNING_FRAME_DURATION,
				walkRightFrames);
		// playerJumpLeft = atlas.findRegion("player-up");
		// playerJumpRight = new TextureRegion(playerJumpLeft);
		// playerJumpRight.flip(true, false);
		// playerFallLeft = atlas.findRegion("player-down");
		// playerFallRight = new TextureRegion(playerFallLeft);
		// playerFallRight.flip(true, false);
	}

	public void render() {
		spriteBatch.begin();
		drawWalls();
		drawPlayer();
		drawBullets();
		spriteBatch.end();
		drawCollisionWalls();
		if (debug)
			drawDebug();
		if (!Gdx.app.getType().equals(ApplicationType.Android))
			return;
		drawJoysticks();
	}
	
	private void drawBullets() {
		for(Bullet bullet : world.getBullets()) {
			spriteBatch.draw(wallTexture, bullet.position.x * ppuX, bullet.position.y * ppuY, Bullet.SIZE * ppuX, Bullet.SIZE * ppuY);
		}
	}

	private void drawWalls() {
		for (Wall wall : world.getDrawableWalls((int) CAMERA_WIDTH,
				(int) CAMERA_HEIGHT)) {
			spriteBatch.draw(wallTexture, wall.getPosition().x * ppuX,
					wall.getPosition().y * ppuY, Wall.SIZE * ppuX, Wall.SIZE
							* ppuY);
		}
	}

	private void drawPlayer() {
		Player player = world.getPlayer();
		playerFrame = player.isFacingLeft() ? playerIdleLeft : playerIdleRight;
		if (player.getState().equals(State.WALKING)) {
			playerFrame = player.isFacingLeft() ? walkLeftAnimation
					.getKeyFrame(player.getStateTime(), true)
					: walkRightAnimation.getKeyFrame(player.getStateTime(),
							true);
		} // else if (player.getState().equals(State.JUMPING)) {
			// if (player.getVelocity().y > 0) {
			// playerFrame = player.isFacingLeft() ? playerJumpLeft :
			// playerJumpRight;
			// } else {
			// playerFrame = player.isFacingLeft() ? playerFallLeft :
			// playerFallRight;
			// }
			// }
		else {
			playerFrame = playerIdleLeft;
		}
		spriteBatch.draw(playerFrame, player.getPosition().x * ppuX,
				player.getPosition().y * ppuY, Player.SIZE * ppuX, Player.SIZE
						* ppuY);
	}

	private void drawJoysticks() {
		OnScreenJoyStick leftJoystick = world.getLeftJoystick();
		OnScreenJoyStick rightJoystick = world.getRightJoystick();

		ShapeRenderer joystickRenderer = new ShapeRenderer();
		Gdx.app.log("LeftJoystick", String.valueOf(leftJoystick.getDrawKnobX()));
		joystickRenderer.begin(ShapeType.Line);
		joystickRenderer.setColor(50, 12, 50, 1);
		joystickRenderer
				.circle(leftJoystick.getDrawOriginX(),
						leftJoystick.getDrawOriginY(),
						leftJoystick.getJoystickRadius());
		joystickRenderer.circle(leftJoystick.getDrawKnobX(),
				leftJoystick.getDrawKnobY(), leftJoystick.getKnobRadius());
		joystickRenderer.circle(rightJoystick.getDrawOriginX(),
				rightJoystick.getDrawOriginY(),
				rightJoystick.getJoystickRadius());
		joystickRenderer.circle(rightJoystick.getDrawKnobX(),
				rightJoystick.getDrawKnobY(), rightJoystick.getKnobRadius());
		joystickRenderer.end();
	}

	private void drawDebug() {
		// render walls
		debugRenderer.setProjectionMatrix(cam.combined);
		debugRenderer.begin(ShapeType.Filled);
		for (Wall wall : world.getDrawableWalls((int) CAMERA_WIDTH,
				(int) CAMERA_HEIGHT)) {
			Rectangle rect = wall.getBounds();
			debugRenderer.setColor(new Color(1, 0, 0, 1));
			debugRenderer.rect(rect.x, rect.y, rect.width, rect.height);
		}
		// render Player
		Player player = world.getPlayer();
		Rectangle rect = player.getBounds();
		debugRenderer.setColor(new Color(0, 1, 0, 1));
		debugRenderer.rect(rect.x, rect.y, rect.width, rect.height);
		debugRenderer.end();
	}

	private void drawCollisionWalls() {
		debugRenderer.setProjectionMatrix(cam.combined);
		debugRenderer.begin(ShapeType.Filled);
		debugRenderer.setColor(new Color(1, 1, 1, 1));
		debugRenderer.end();

	}
}
