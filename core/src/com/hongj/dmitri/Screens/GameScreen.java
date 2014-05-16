package com.hongj.dmitri.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.hongj.dmitri.DmitriGame;
import com.hongj.dmitri.Controllers.InputHandler;
import com.hongj.dmitri.World.B2DWorld;
import com.hongj.dmitri.World.WorldRenderer;

public class GameScreen implements Screen {

	B2DWorld world;
	WorldRenderer worldRenderer;
	Box2DDebugRenderer debugRenderer;
	DmitriGame game;

	// GameState state;
	// public enum GameState

	public GameScreen(DmitriGame game) {

		this.game = game;
		world = new B2DWorld();
		worldRenderer = new WorldRenderer(world);
		// gdx input
		Gdx.input.setInputProcessor(new InputHandler(world, worldRenderer));

	}

	@Override
	public void render(float delta) {
		world.update();
		worldRenderer.render();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}

}
