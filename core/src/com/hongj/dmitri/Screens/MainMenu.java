package com.hongj.dmitri.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.hongj.dmitri.DmitriGame;

public class MainMenu implements Screen {

	private DmitriGame game;
	private Stage stage;
	private Table table;
	private OrthographicCamera camera;

	public MainMenu(DmitriGame game) {
		this.game = game;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		stage.draw();
		Table.drawDebug(stage);
	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(new FitViewport(800, 480, camera));
		table.invalidateHierarchy();
	}

	@Override
	public void show() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		stage = new Stage();

		Skin skin = new Skin(Gdx.files.internal("menuSkin.json"));

		table = new Table(skin);
		table.setFillParent(true);
		table.add("DMITRI").colspan(3).row().spaceBottom(5);
		table.add("play").row();
		table.debug();
		stage.addActor(table);
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
		stage.dispose();

		game.dispose();
	}

}
