package com.hongj.dmitri.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Player extends Entity {
	private Body body;
	private float speedx = 4, speedy = 0;
	private PlayerState state;
	private boolean facingLeft, touchingGround;

	public Player(Vector2 position, float width, float height, Body body) {
		super(position, body, height, width);
		this.body = body;
		state = PlayerState.IDLE;
		facingLeft = false;
		touchingGround = true;
	}

	@Override
	public void update() {
		if (state == PlayerState.MOVINGLEFT) {
			body.applyForceToCenter(new Vector2(-speedx, speedy), true);
			stateTime += Gdx.graphics.getDeltaTime();
		}
		if (state == PlayerState.MOVINGRIGHT) {
			body.applyForceToCenter(new Vector2(speedx, speedy), true);
			stateTime += Gdx.graphics.getDeltaTime();
		}

	}

	public void setTouchingGround(boolean touchingGround) {
		this.touchingGround = touchingGround;
	}

	public Boolean getTouchingGround() {
		return touchingGround;
	}

	public void setFacingLeft(boolean facingLeft) {
		this.facingLeft = facingLeft;
	}

	public Boolean getFacingLeft() {
		return facingLeft;
	}

	public PlayerState getState() {
		return state;
	}

	public void setState(PlayerState state) {
		this.state = state;
	}

	public enum PlayerState {
		IDLE, DEAD, MOVINGLEFT, MOVINGRIGHT;
	}

}