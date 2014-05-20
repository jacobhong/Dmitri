package com.hongj.dmitri.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class KinematicEntity extends Entity {
	private float distance = 0, maxDistance = 0;
	private Vector2 vel;
	private boolean isVerticalMovement;
	private boolean isPositiveDirection;
	private Vector2 startingPoint;

	public KinematicEntity(Vector2 position, Body body, float width,
			float height, float maxDistance, Vector2 vel) {
		super(position, body, height, width);
		body.setUserData(this);
		this.maxDistance = maxDistance;
		this.vel = vel;
		isVerticalMovement = vel.y != 0 ? true : false;
		isPositiveDirection = vel.x > 0 || vel.y > 0 ? true : false;
		startingPoint = position;

	}

	@Override
	public void update() {

		stateTime += Gdx.graphics.getDeltaTime();
		// distance += 1 * Gdx.graphics.getDeltaTime();

		if (isVerticalMovement && isPositiveDirection) {
			if (body.getPosition().y > startingPoint.y + maxDistance) {
				vel.y *= -1;
			}
			if (body.getPosition().y < startingPoint.y) {
				vel.y *= -1;
			}
		} else if (isVerticalMovement && isPositiveDirection != true) {
			if (body.getPosition().y < startingPoint.y - maxDistance) {
				vel.y *= -1;
			}
			if (body.getPosition().y > startingPoint.y) {
				vel.y *= -1;
			}
		}
		if (!isVerticalMovement && isPositiveDirection) {
			if (body.getPosition().x > startingPoint.x + maxDistance) {
				vel.x *= -1;
			}
			if (body.getPosition().x < startingPoint.x) {
				vel.x *= -1;
			}
		} else if (!isVerticalMovement && isPositiveDirection != true) {
			if (body.getPosition().x < startingPoint.x - maxDistance) {
				vel.x *= -1;
			}
			if (body.getPosition().x > startingPoint.x) {
				vel.x *= -1;
			}
		}

		// if (distance > maxDistance) {
		// vel.scl(-1);
		// distance = 0;
		// }
		body.setLinearVelocity(vel.x, vel.y);
	}
}
