package com.hongj.dmitri;

import com.badlogic.gdx.Game;
import com.hongj.dmitri.Screens.GameScreen;

public class DmitriGame extends Game {
	public static final String TITLE = "Dmitri";

	@Override
	public void create() {
		Assets.load();
		setScreen(new GameScreen(this));
	}

	@Override
	public void dispose() {
		Assets.dispose();

	}

	@Override
	public void render() {

		super.render();
	}
}
