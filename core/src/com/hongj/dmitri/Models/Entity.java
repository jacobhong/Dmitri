package com.hongj.dmitri.Models;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {

	private float width, height;
	private Vector2 position;
	private Rectangle bounds;

	public Entity(Vector2 position, float width, float height) {
		this.position = position;
		this.width = width;
		this.height = height;

		bounds = new Rectangle(position.x, position.y, width, height);
	}

	abstract void update();

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

}
