package com.hongj.dmitri.World;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.hongj.dmitri.Assets;

public class WorldRenderer {
	private B2DWorld world;
	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera camera;
	private TiledMap map;
	private OrthogonalTiledMapRenderer mapRenderer;
	private Body player;
	private Animation playerWalking;
	private SpriteBatch batch;
	private Texture background;
	private MapLayer layer;
	private Array<Body> bodies = new Array<Body>();

	public WorldRenderer(B2DWorld world) {

		this.world = world;
		playerWalking = Assets.dmitriWalking;

		player = world.getPlayerBody();
		debugRenderer = new Box2DDebugRenderer();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 8, 6);

		batch = new SpriteBatch();
		background = Assets.background;

		map = new TmxMapLoader().load("map.tmx");
		mapRenderer = new OrthogonalTiledMapRenderer(map, 1 / 32f);

		layer = map.getLayers().get("collision");
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

				Body body = world.getWorld().createBody(bdef);
				body.createFixture(fdef);
			}
		}

	}

	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(background, -5, -5);
		batch.end();
		// follow player
		if (player.getPosition().x < (8 / 2f)) {
			camera.position.set(new Vector2(8 / 2f, 6 / 2f), 0);
		} else {
			camera.position.x = player.getPosition().x;
		}
		camera.position.y = player.getPosition().y + 2;
		camera.update();

		// render tile map to camera matrix
		mapRenderer.setView(camera);
		mapRenderer.render();
		mapRenderer.render();
		// draw player
		batch.begin();
		batch.draw(playerWalking.getKeyFrame(Gdx.graphics.getDeltaTime()),
				player.getPosition().x - .5f, player.getPosition().y - .5f,
				.5f, .5f, 1, 1, 1, 1, player.getAngle()
						* MathUtils.radiansToDegrees);
		world.getWorld().getBodies(bodies);
		for (Body bod : bodies) {
			
			if (bod.getUserData() == "block") {
				Vector2 v = bod.getPosition();
				batch.draw(Assets.yellowBlock, v.x-.5f, v.y-.5f, 1, 1);
			}
		}
		batch.end();

		// shows debug lines in Box2dWorld
		debugRenderer.render(world.getWorld(), camera.combined);
	}

	public OrthographicCamera getCamera() {
		return camera;
	}
}
