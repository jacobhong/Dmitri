package com.hongj.dmitri.World;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Array;
import com.hongj.dmitri.Assets;
import com.hongj.dmitri.Models.KinematicEntity;
import com.hongj.dmitri.Models.Player;
import com.hongj.dmitri.Models.Player.PlayerState;

public class WorldRenderer {
	public static final float OFFSET = .5f;

	private B2DWorld world;
	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera camera;
	private OrthogonalTiledMapRenderer mapRenderer;
	private Body playerBody;
	private Player player;
	private Animation playerWalking, slimeWalking, snailWalking,
			spinnerHalfSpinning;
	private SpriteBatch batch;
	private Texture background;
	private Array<KinematicEntity> kinematicEntities;
	private Array<Body> bodies;

	public WorldRenderer(B2DWorld world) {

		this.world = world;
		bodies = new Array<Body>();
		player = world.getPlayer();
		slimeWalking = Assets.slimeWalking;
		snailWalking = Assets.snailWalking;
		spinnerHalfSpinning = Assets.spinnerHalfSpinning;
		kinematicEntities = world.getKinematicEntities();
		playerWalking = Assets.dmitriWalking;

		playerBody = world.getPlayerBody();
		debugRenderer = new Box2DDebugRenderer();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 12, 7);

		batch = new SpriteBatch();
		background = Assets.background;

		mapRenderer = new OrthogonalTiledMapRenderer(world.getMap(), 1 / 32f);

	}

	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		// batch.begin();
		// batch.draw(background, -5, -5, 40, 20);
		// batch.end();

		// follow player

		if (playerBody.getPosition().x < (8 / 2f)) {
			camera.position.set(new Vector2(8 / 2f, 6 / 2f), 0);
		} else {
			camera.position.x = playerBody.getPosition().x;
		}
		camera.position.y = playerBody.getPosition().y + 2;
		camera.update();

		// render tile map to camera matrix
		mapRenderer.setView(camera);
		mapRenderer.render();

		world.getWorld().getBodies(bodies);
		// draw player
		batch.begin();
		for (Body body : bodies) {
			if (body.getUserData() != null && body.getUserData() == player) {
				if (player.getState() == PlayerState.IDLE
						&& player.getFacingLeft()) {
					batch.draw(
							playerWalking.getKeyFrame(player.getStateTime()),
							playerBody.getPosition().x - OFFSET,
							playerBody.getPosition().y - OFFSET, .5f, .5f, 1,
							1, 1, 1, playerBody.getAngle()
									* MathUtils.radiansToDegrees);
				} else if (player.getState() == PlayerState.MOVINGLEFT) {
					batch.draw(
							playerWalking.getKeyFrame(player.getStateTime()),
							playerBody.getPosition().x - OFFSET,
							playerBody.getPosition().y - OFFSET, .5f, .5f, 1,
							1, -1, 1, playerBody.getAngle()
									* MathUtils.radiansToDegrees);
				} else if (player.getState() == PlayerState.MOVINGRIGHT) {
					batch.draw(
							playerWalking.getKeyFrame(player.getStateTime()),
							playerBody.getPosition().x - OFFSET,
							playerBody.getPosition().y - OFFSET, .5f, .5f, 1,
							1, 1, 1, playerBody.getAngle()
									* MathUtils.radiansToDegrees);
				} else {
					batch.draw(
							playerWalking.getKeyFrame(player.getStateTime()),
							playerBody.getPosition().x - OFFSET,
							playerBody.getPosition().y - OFFSET, .5f, .5f, 1,
							1, 1, 1, playerBody.getAngle()
									* MathUtils.radiansToDegrees);
				}

			}
			if (body.getFixtureList().get(0).getUserData() != null
					&& body.getFixtureList().get(0).getUserData()
							.equals("elevator")) {
				batch.draw(Assets.elevator, body.getPosition().x - OFFSET,
						body.getPosition().y - OFFSET, 1, 1);
			} else if (body.getFixtureList().get(0).getUserData() != null
					&& body.getFixtureList().get(0).getUserData()
							.equals("slime")) {
				batch.draw(slimeWalking.getKeyFrame(0), body.getPosition().x,
						body.getPosition().y, 1, 1);
			} else if (body.getFixtureList().get(0).getUserData() != null
					&& body.getFixtureList().get(0).getUserData()
							.equals("snail")) {
				batch.draw(snailWalking.getKeyFrame(0), body.getPosition().x
						- OFFSET, body.getPosition().y - OFFSET, 1, 1);
			} else if (body.getFixtureList().get(0).getUserData() != null
					&& body.getFixtureList().get(0).getUserData()
							.equals("spinning half")) {
				batch.draw(spinnerHalfSpinning.getKeyFrame(0),
						body.getPosition().x - OFFSET, body.getPosition().y
								- OFFSET, 1, 1);
			}
		}

		// for (KinematicEntity kinematicEntity : kinematicEntities) {
		// Body body = kinematicEntity.getBody();
		// if (body.getUserData() != null
		// && body.getUserData().equals("elevator")) {
		// batch.draw(Assets.elevator, body.getPosition().x - OFFSET,
		// body.getPosition().y - OFFSET, 1, 1);
		// }
		// if (body.getUserData() != null
		// && body.getUserData().equals("slime")) {
		// batch.draw(slimeWalking.getKeyFrame(kinematicEntity
		// .getStateTime()), body.getPosition().x, body
		// .getPosition().y, 1, 1);
		// }
		// if (body.getUserData() != null
		// && body.getUserData().equals("snail")) {
		// batch.draw(snailWalking.getKeyFrame(kinematicEntity
		// .getStateTime()), body.getPosition().x - OFFSET,
		// body.getPosition().y - OFFSET, 1, 1);
		// }
		// if (body.getUserData() != null
		// && body.getUserData().equals("spinner half")) {
		// batch.draw(spinnerHalfSpinning.getKeyFrame(kinematicEntity
		// .getStateTime()), body.getPosition().x - OFFSET,
		// body.getPosition().y - OFFSET, 1, 1);
		// }
		//
		// }

		batch.end();

		// shows debug lines in Box2dWorld
		debugRenderer.render(world.getWorld(), camera.combined);
	}

	public OrthographicCamera getCamera() {
		return camera;
	}
}
