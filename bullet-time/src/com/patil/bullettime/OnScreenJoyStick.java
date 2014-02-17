package com.patil.bullettime;

public class OnScreenJoyStick {

	private float origin_x;
	private float origin_y;
	private float knob_x;
	private float knob_y;
	private float joyStickRadius;
	private float knobRadius;

	public OnScreenJoyStick(float location_x, float location_y,
			float joyStickRadius, float knobRadius) {
		this.origin_x = location_x;
		this.origin_y = location_y;
		this.joyStickRadius = joyStickRadius;
		this.knob_x = location_x;
		this.knob_y = location_y;
		this.knobRadius = knobRadius;
	}

	public void updateKnobPosition(float x, float y) {
		// if ((x - origin_x) * (x - origin_x)
		// + (y - origin_y) * (y - origin_y)
		// <= joyStickRadius * joyStickRadius) {
		knob_x = x;
		knob_y = y;
		// } else {
		// resetKnob();
		// }
	}

	public float getKnobRadius() {
		return knobRadius;
	}

	public float getJoystickRadius() {
		return joyStickRadius;
	}

	public void resetKnob() {
		knob_x = ((origin_x ) - joyStickRadius / 2) - 4;
		knob_y = ((origin_y) - joyStickRadius / 2) - 4;
	}

	private float deltaX() {
		return knob_x - origin_x;
	}

	private float deltaY() {
		return knob_y - origin_y;
	}

	public float x_Normalized() {
		return deltaX() / joyStickRadius;
	}

	public float y_Normalized() {
		return deltaY() / joyStickRadius;
	}

	public float angle() {
		return (float) (Math.atan2(deltaY(), deltaX()) * 180 / Math.PI);
	}

	public float getDrawOriginX() {
		return origin_x - joyStickRadius;
	}

	public float getDrawOriginY() {
		return origin_y - joyStickRadius;
	}

	public float getDrawKnobX() {
		return knob_x - knobRadius;
	}

	public float getDrawKnobY() {
		return knob_y - knobRadius;
	}
}
