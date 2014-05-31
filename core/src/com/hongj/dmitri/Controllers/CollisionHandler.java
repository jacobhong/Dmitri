package com.hongj.dmitri.Controllers;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactFilter;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.hongj.dmitri.Models.Player;
import com.hongj.dmitri.World.B2DWorld;
import com.hongj.dmitri.World.WorldRenderer;

public class CollisionHandler implements ContactListener, ContactFilter {

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
		if (a.getUserData() != null && a.getUserData().equals("foot")
				|| b.getUserData() != null && b.getUserData().equals("foot")) {
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

	@Override
	public boolean shouldCollide(Fixture fixtureA, Fixture fixtureB) {
		if (fixtureB.getUserData() != null
				&& fixtureB.getUserData().equals("foot")
				&& fixtureA.getUserData().equals("passable")) {
			if (fixtureB.getBody().getLinearVelocity().y > 0) {
				return false;
			} else {
				return true;
			}
		} else if (fixtureB.getUserData() != null
				&& fixtureB.getUserData().equals("foot")
				&& fixtureA.getUserData().equals("collision")) {
			return true;
		}
		return false;

	}

}
