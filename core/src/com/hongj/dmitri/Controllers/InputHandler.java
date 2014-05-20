package com.hongj.dmitri.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.hongj.dmitri.Models.Player;
import com.hongj.dmitri.Models.Player.PlayerState;
import com.hongj.dmitri.World.B2DWorld;
import com.hongj.dmitri.World.WorldRenderer;

public class InputHandler extends InputAdapter {

	private Player player;
	private B2DWorld world;
	private WorldRenderer renderer;
	private float stateTime;

	public InputHandler(B2DWorld world, WorldRenderer renderer) {
		this.world = world;
		this.renderer = renderer;
		this.player = world.getPlayer();
	}

	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
		case Keys.RIGHT:
			player.setState(PlayerState.MOVINGRIGHT);
			player.setFacingLeft(false);
			break;
		case Keys.LEFT:
			player.setState(PlayerState.MOVINGLEFT);
			player.setFacingLeft(true);
			break;
		case Keys.DOWN:
			player.getBody().applyForceToCenter(0, -55, true);
			break;
		case Keys.UP:
			jump();
			break;
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {
		case Keys.RIGHT:
		case Keys.LEFT:
		case Keys.DOWN:
		case Keys.UP:
			player.setState(PlayerState.IDLE);
			break;
		}
		return true;

	}

	@Override
	public boolean scrolled(int amount) {
		renderer.getCamera().zoom += amount;
		return super.scrolled(amount);
	}

	private void jump() {
		if (player.getTouchingGround()) {
			player.getBody().applyLinearImpulse(0, 3f, 0, 0, true);
			stateTime += Gdx.graphics.getDeltaTime();
			player.setTouchingGround(false);
		}
	}
}
