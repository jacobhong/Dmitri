package com.hongj.dmitri.Models;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.hongj.dmitri.Models.Player.PlayerState;

public abstract class Entity {

	protected Body body;
	protected float width, height;
	protected Vector2 position;
	protected float stateTime;

	protected Entity(Vector2 position, Body body, float width, float height) {
		this.position = position;
		this.body = body;
		this.width = width;
		this.height = height;
	}

	public abstract void update();

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public float getStateTime() {
		return stateTime;
	}

	public Body getBody() {
		return body;
	}

}
