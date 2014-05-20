package com.hongj.dmitri.Models;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.hongj.dmitri.Models.Player.PlayerState;

public abstract class Entity {

	protected Body body;
	protected float width, height;
	protected Vector2 position;
	protected Rectangle bounds;
	protected float stateTime;

	public Entity(Vector2 position, Body body, float width, float height) {
		this.position = position;
		this.body = body;
		this.width = width;
		this.height = height;
		bounds = new Rectangle(position.x, position.y, width, height);
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

	public Rectangle getBounds() {
		return bounds;
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
