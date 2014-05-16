package com.hongj.dmitri.Models;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Player extends Entity {
	private Body playerBody;
	private float speedx = 100, speedy = 0;

	public Player(Vector2 position, float width, float height, Body body) {
		super(position, width, height);
		playerBody = body;
	}

	@Override
	void update() {
//		playerBody.applyForceToCenter(speedx, speedy, true);
		if(playerBody.getAngle() > 45){	
			
		}
	}

	public Body getPlayerBody() {
		return playerBody;
	}

}

// BodyDef bdef = new BodyDef();
// bdef.type = BodyType.DynamicBody;
// bdef.position.set(body.getPosition());
//
// PolygonShape pshape = new PolygonShape();
// pshape.setAsBox(width / 2, height / 2);
//
// FixtureDef fdef = new FixtureDef();
// fdef.shape = pshape;
// fdef.density = .3f;
// fdef.friction = .3f;
// fdef.restitution = .1f;