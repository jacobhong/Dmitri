package com.hongj.dmitri.World;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef;
import com.badlogic.gdx.utils.Array;
import com.hongj.dmitri.Assets;
import com.hongj.dmitri.Controllers.CollisionHandler;
import com.hongj.dmitri.Models.KinematicEntity;
import com.hongj.dmitri.Models.Player;

public class B2DWorld {
	private Array<Body> bodies;
	private Player player;
	private Body playerBody;
	private World world;
	private TiledMap map;
	private Array<KinematicEntity> kinematicEntities;

	// GameStates

	public B2DWorld() {
		bodies = new Array<Body>();
		// create world
		world = new World(new Vector2(0, -9.81f), true);
		world.setContactListener(new CollisionHandler(this));

		kinematicEntities = new Array<KinematicEntity>();

		// set up reusable BodyDef and FixtureDef
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();

		createTiledMap();
		createElevators(bdef, fdef);
		createHalfSpinners(bdef, fdef);
		createMoveableBlocks(bdef, fdef);
		createPlayer(bdef, fdef);
		createSlimes(bdef, fdef);
		createSnails(bdef, fdef);
	}

	private void createTiledMap() {

		map = new TmxMapLoader().load("map.tmx");

		MapLayer layer = map.getLayers().get("collision");
		for (MapObject mo : layer.getObjects()) {
			if (mo instanceof RectangleMapObject) {
				BodyDef bdef = new BodyDef();
				bdef.type = BodyType.StaticBody;
				bdef.position.set(((RectangleMapObject) mo).getRectangle().x
						/ 32f + ((RectangleMapObject) mo).getRectangle().width
						/ 32 / 2, ((RectangleMapObject) mo).getRectangle().y
						/ 32f + ((RectangleMapObject) mo).getRectangle().height
						/ 32 / 2);

				PolygonShape shape = new PolygonShape();
				shape.setAsBox(
						((RectangleMapObject) mo).getRectangle().width / 32f / 2,
						((RectangleMapObject) mo).getRectangle().height / 32f / 2);

				FixtureDef fdef = new FixtureDef();
				fdef.shape = shape;

				Body body = world.createBody(bdef);
				body.createFixture(fdef);
			}
		}
	}

	private void createPlayer(BodyDef bdef, FixtureDef fdef) {
		Vector2 pos = new Vector2();
		MapLayer layer = map.getLayers().get("spawn points");
		for (MapObject mo : layer.getObjects()) {
			if (mo instanceof RectangleMapObject) {
				if (mo.getName().equals("player")) {
					pos.x = ((RectangleMapObject) mo).getRectangle().x / 32f;
					pos.y = ((RectangleMapObject) mo).getRectangle().y / 32f;

					bdef.type = BodyType.DynamicBody;
					bdef.position.set(pos.x, pos.y);
					bdef.fixedRotation = true;

					// rectangle
					PolygonShape shape = new PolygonShape();
					shape.setAsBox(.5f, .5f);

					// body physics
					fdef.shape = shape;
					fdef.density = .3f;
					fdef.friction = 2;
					fdef.isSensor = false;
					fdef.restitution = 0;

					// create body and attach fixture
					playerBody = world.createBody(bdef);
					playerBody.createFixture(fdef);

					// create foot sensor
					shape.setAsBox(.4f, .1f, new Vector2(0, -.4f), 0);

					fdef.shape = shape;
					playerBody.createFixture(fdef).setUserData("foot");

					// create Player object and attach to playerBody
					player = new Player(new Vector2(pos.x, pos.y), 1, 1,
							playerBody);
					playerBody.setUserData(player);

					shape.dispose();
				}
			}
		}
	}

	private void createMoveableBlocks(BodyDef bdef, FixtureDef fdef) {
		Vector2 pos = new Vector2();
		MapLayer layer = map.getLayers().get("spawn points");
		for (MapObject mo : layer.getObjects()) {
			if (mo instanceof RectangleMapObject
					&& mo.getName().equals("block")) {
				pos.x = ((RectangleMapObject) mo).getRectangle().x / 32f;
				pos.y = ((RectangleMapObject) mo).getRectangle().y / 32f;
				// create vertical moving blocks and joint
				bdef.type = BodyType.DynamicBody;
				bdef.position.set(pos.x, pos.y);

				PolygonShape shape = new PolygonShape();
				shape.setAsBox(.5f, .5f);

				fdef.shape = shape;
				fdef.density = .3f;
				fdef.friction = .3f;
				fdef.restitution = 0;

				Body body = world.createBody(bdef);
				body.createFixture(fdef).setUserData("block");

				// create body for joint
				bdef.type = BodyType.StaticBody;
				bdef.position.set(pos.x, pos.y);

				Body body2 = world.createBody(bdef);

				PrismaticJointDef joint = new PrismaticJointDef();
				joint.bodyA = body;
				joint.bodyB = body2;
				joint.localAxisA.set(0, 20);
				joint.enableLimit = true;
				joint.upperTranslation = .4f;
				world.createJoint(joint);

				shape.dispose();

			}
		}

	}

	private void createElevators(BodyDef bdef, FixtureDef fdef) {
		fdef.isSensor = false;
		createKinematicEntity(bdef, fdef, "elevator");
	}

	private void createSlimes(BodyDef bdef, FixtureDef fdef) {
		fdef.isSensor = true;
		createKinematicEntity(bdef, fdef, "slime");

	}

	private void createSnails(BodyDef bdef, FixtureDef fdef) {
		fdef.isSensor = true;
		createKinematicEntity(bdef, fdef, "snail");
	}

	private void createHalfSpinners(BodyDef bdef, FixtureDef fdef) {
		fdef.isSensor = true;
		createKinematicEntity(bdef, fdef, "spinner half");
	}

	private void createKinematicEntity(BodyDef bdef, FixtureDef fdef,
			String name) {
		Vector2 pos = new Vector2();
		MapLayer layer = map.getLayers().get("spawn points");
		float distance = 0;
		float speed = 0;
		String horizontal;
		Vector2 vel = new Vector2();
		for (MapObject mo : layer.getObjects()) {
			if (mo instanceof RectangleMapObject && mo.getName().equals(name)) {
				pos.x = ((RectangleMapObject) mo).getRectangle().x / 32f;
				pos.y = ((RectangleMapObject) mo).getRectangle().y / 32f;

				distance = Float.parseFloat((String) mo.getProperties().get(
						"maxDistance"));
				horizontal = (String) mo.getProperties().get("isHorizontal");
				speed = Float.parseFloat((String) mo.getProperties().get(
						"speed"));
				System.out.println(speed);
				if (horizontal.startsWith("t") && horizontal.endsWith("+")) {
					vel = new Vector2(speed, 0);
				} else if (horizontal.startsWith("t")
						&& horizontal.endsWith("-")) {
					vel = new Vector2(-speed, 0);
				} else if (horizontal.startsWith("f")
						&& horizontal.endsWith("+")) {
					vel = new Vector2(0, speed);
				} else if (horizontal.startsWith("f")
						&& horizontal.endsWith("-")) {
					vel = new Vector2(0, -speed);
				}
				bdef.type = BodyType.KinematicBody;
				bdef.position.set(pos.x + WorldRenderer.OFFSET, pos.y
						+ WorldRenderer.OFFSET);

				PolygonShape shape = new PolygonShape();
				shape.setAsBox(.5f, .5f);

				fdef.shape = shape;
				fdef.density = .2f;
				fdef.friction = 1;
				fdef.restitution = 0;

				Body body = world.createBody(bdef);

				body.createFixture(fdef).setUserData(name);

				KinematicEntity kinematicEntity = new KinematicEntity(
						new Vector2(pos.x, pos.y), body, .5f, .5f, distance,
						vel);
				kinematicEntities.add(kinematicEntity);

				shape.dispose();
			}
		}
	}

	public void update() {
		// update World
		world.step(1 / 60f, 6, 3);
		// player.update();
		// for (KinematicEntity kinematicEntity : kinematicEntities) {
		// kinematicEntity.update();
		// }
		world.getBodies(bodies);
		for (Body body : bodies) {
			if (body.getUserData() != null && body.getUserData() == player) {
				player.update();
			}
			if (body.getUserData() != null
					&& body.getUserData() instanceof KinematicEntity) {
				KinematicEntity b = (KinematicEntity) body.getUserData();
				b.update();

			}

		}
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

	public TiledMap getMap() {
		return map;
	}

	public Array<KinematicEntity> getKinematicEntities() {
		return kinematicEntities;
	}

}
