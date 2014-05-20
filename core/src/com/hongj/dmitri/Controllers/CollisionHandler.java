package com.hongj.dmitri.Controllers;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.hongj.dmitri.World.B2DWorld;

public class CollisionHandler implements ContactListener {

	private B2DWorld world;

	public CollisionHandler(B2DWorld world) {
		this.world = world;
	}

	@Override
	public void beginContact(Contact contact) {
		Fixture a = contact.getFixtureA();
		Fixture b = contact.getFixtureB();
		if (a == null || b == null)
			return;
		if (a.getUserData() != null && a.getUserData().equals("foot")) {
			world.getPlayer().setTouchingGround(true);
		}
		if (b.getUserData() != null && b.getUserData().equals("foot")) {
			world.getPlayer().setTouchingGround(true);
		}

	}

	@Override
	public void endContact(Contact contact) {

	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {

	}

}
