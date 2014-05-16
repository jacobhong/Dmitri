package com.hongj.dmitri.Controllers;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.hongj.dmitri.Models.Player;
import com.hongj.dmitri.World.B2DWorld;
import com.hongj.dmitri.World.WorldRenderer;

public class InputHandler extends InputAdapter {

	private Player player;
	private B2DWorld world;
	private WorldRenderer renderer;

	public InputHandler(B2DWorld world, WorldRenderer renderer) {
		this.world = world;
		this.renderer = renderer;
		this.player = world.getPlayer();
	}

	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
		case Keys.RIGHT:
			player.getPlayerBody().applyForceToCenter(55, 0, true);
			break;
		case Keys.LEFT:
			player.getPlayerBody().applyForceToCenter(-55, 0, true);
			break;
		case Keys.DOWN:
			player.getPlayerBody().applyForceToCenter(0, -55, true);
			break;
		case Keys.UP:
			player.getPlayerBody().applyForceToCenter(0, 55, true);
			break;
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {
		case Keys.RIGHT:
			player.getPlayerBody().applyForceToCenter(0, 0, true);
			break;
		case Keys.LEFT:
			player.getPlayerBody().applyForceToCenter(0, 0, true);
			break;
		case Keys.DOWN:
			player.getPlayerBody().applyForceToCenter(0, 0, true);
			break;
		case Keys.UP:
			player.getPlayerBody().applyForceToCenter(0, 0, true);
			break;
		}
		return true;

	}

	@Override
	public boolean scrolled(int amount) {
		renderer.getCamera().zoom += amount;
		return super.scrolled(amount);
	}
}
