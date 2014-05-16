package com.hongj.dmitri.Screens;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.hongj.dmitri.DmitriGame;

public class SplashScreen implements Screen {

	DmitriGame game;
	SpriteBatch batch;
	Sprite splashSprite;
	ShapeRenderer renderer;
	private TweenManager manager;

	public SplashScreen(DmitriGame dmitriGame) {
		this.game = dmitriGame;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		renderer.begin(ShapeType.Filled);
		renderer.rect(Gdx.graphics.getWidth() / 2,
				Gdx.graphics.getHeight() / 2, 100, 100);
		renderer.end();

		manager.update(delta);
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
		// Tween.registerAccessor(Sprite.cass, new SpriteAccessor());
		manager = new TweenManager();
		renderer = new ShapeRenderer();
		renderer.setColor(Color.LIGHT_GRAY);
		//
		// Tween.to(particle1, ParticleAccessor.POSITION_XY, 1.0f)
		// .target(100, 200)
		// .start(manager);
		//
		// Tween.to(particle2, ParticleAccessor.POSITION_XY, 0.5f)
		// .target(0, 0)
		// .ease(Bounce.OUT)
		// .delay(1.0f)
		// .repeatYoyo(2, 0.5f)
		// .start(manager);
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
