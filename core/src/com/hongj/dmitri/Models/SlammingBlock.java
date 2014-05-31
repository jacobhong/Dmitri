package com.hongj.dmitri.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.hongj.dmitri.World.WorldRenderer;

public class SlammingBlock extends KinematicEntity {

	public SlammingBlock(Vector2 position, Body body, float width,
			float height, float maxDistance, Vector2 vel) {
		super(position, body, width, height, maxDistance, vel);

	}

	@Override
	public void update() {
		stateTime += Gdx.graphics.getDeltaTime();
		// distance += 1 * Gdx.graphics.getDeltaTime();

		if (isVerticalMovement && isPositiveDirection != true) {
			if (body.getPosition().y - WorldRenderer.OFFSET < startingPoint.y
					- maxDistance) {
				vel.y = 1;

			} else if (body.getPosition().y - WorldRenderer.OFFSET > startingPoint.y) {
				vel.y = -4;
			}
		}
		if (!isVerticalMovement && isPositiveDirection) {
			if (body.getPosition().x - WorldRenderer.OFFSET > startingPoint.x
					+ maxDistance
					|| body.getPosition().x - WorldRenderer.OFFSET < startingPoint.x) {
				vel.x *= -1;
			}

		} else if (!isVerticalMovement && isPositiveDirection != true) {
			if (body.getPosition().x - WorldRenderer.OFFSET < startingPoint.x
					- maxDistance
					|| body.getPosition().x - WorldRenderer.OFFSET > startingPoint.x) {
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
