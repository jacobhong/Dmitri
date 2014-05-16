package com.hongj.dmitri.World;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef;
import com.hongj.dmitri.Models.Player;

public class B2DWorld {
	Player player;
	Body playerBody;
	World world;
	Box2DDebugRenderer debugRenderer;

	// GameStates

	public B2DWorld() {
		// create world
		world = new World(new Vector2(0, -9.81f), true);

		// set up reusable BodyDef and FixtureDef
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();

		// create playerBody and Player entity
		createPlayer(bdef, fdef);
		createMoveableBlock(bdef, fdef, new Vector2(3, 4));
		createMoveableBlock(bdef, fdef, new Vector2(4, 4));

	}

	private void createPlayer(BodyDef bdef, FixtureDef fdef) {
		bdef.type = BodyType.DynamicBody;
		bdef.position.set(.5f, 4);
		bdef.fixedRotation = true;
		// rectangle bounds
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(.5f, .5f);

		// body physics
		fdef.shape = shape;
		fdef.density = .3f;
		fdef.friction = 2;
		fdef.restitution = 0;

		// create body and attach fixture
		playerBody = world.createBody(bdef);
		playerBody.createFixture(fdef);

		// create Player object and attach to playerBody
		player = new Player(new Vector2(playerBody.getPosition().x,
				playerBody.getPosition().y), 1, 1, playerBody);
		playerBody.setUserData(player);
	}

	private void createMoveableBlock(BodyDef bdef, FixtureDef fdef,
			Vector2 position) {
		// create vertical moving blocks and joint
		bdef.type = BodyType.DynamicBody;
		bdef.position.set(position.x, position.y);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(.5f, .5f);

		fdef.shape = shape;
		fdef.density = .3f;
		fdef.friction = .3f;
		fdef.restitution = 0;

		Body body = world.createBody(bdef);
		body.setUserData("block");
		body.createFixture(fdef);

		// create body for joint
		bdef.type = BodyType.StaticBody;
		bdef.position.set(position.x, position.y);

		Body body2 = world.createBody(bdef);

		PrismaticJointDef joint = new PrismaticJointDef();
		joint.bodyA = body;
		joint.bodyB = body2;
		joint.localAxisA.set(0, 20);
		joint.enableLimit = true;
		joint.upperTranslation = .4f;
		world.createJoint(joint);

	}

	public void update() {
		// update World
		world.step(1 / 60f, 6, 3);
	}

	public World getWorld() {
		return world;
	}

	public Player getPlayer() {
		return player;
	}

	public Body getPlayerBody() {
		return playerBody;
	}
}
