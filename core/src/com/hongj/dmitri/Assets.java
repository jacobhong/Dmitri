package com.hongj.dmitri;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;

public class Assets {

	public static BitmapFont font;

	public static Animation dmitriWalking, slimeWalking, spinnerHalfSpinning,
			snailWalking;
	public static TextureRegion bee, dmitriWalk1, dmitriWalk2, elevator,
			slimeWalk1, slimeWalk2, snailWalk1, snailWalk2, spinnerHalf,
			spinnerHalfSpin, yellowBlock;
	public static Texture objects, tiles, background, background2;

	public static Texture[] backgroundLayer;

	public static void load() {
		font = new BitmapFont(Gdx.files.internal("white.fnt"));

		objects = new Texture(Gdx.files.internal("Objects.png"));
		objects.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		tiles = new Texture(Gdx.files.internal("tiles.png"));
		objects.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		background = new Texture(Gdx.files.internal("bg_grasslands.png"));
		background2 = new Texture(Gdx.files.internal("bg_shroom.png"));

		dmitriWalk1 = new TextureRegion(objects, 509, 224, 26, 32);
		dmitriWalk2 = new TextureRegion(objects, 592, 329, 26, 32);

		elevator = new TextureRegion(objects, 466, 28, 32, 24);

		slimeWalk1 = new TextureRegion(objects, 733, 179, 32, 18);
		slimeWalk2 = new TextureRegion(objects, 171, 2, 32, 16);

		spinnerHalf = new TextureRegion(objects, 205, 2, 32, 16);
		spinnerHalfSpin = new TextureRegion(objects, 565, 35, 32, 16);

		snailWalk1 = new TextureRegion(objects, 813, 16, 32, 18);
		snailWalk2 = new TextureRegion(objects, 103, 1, 32, 17);

		yellowBlock = new TextureRegion(tiles, 35, 511, 32, 32);

		dmitriWalking = new Animation(1 / 12f, new TextureRegion[] {
				dmitriWalk1, dmitriWalk2 });

		dmitriWalking.setPlayMode(PlayMode.LOOP_PINGPONG);

		slimeWalking = new Animation(1 / 12, new TextureRegion[] { slimeWalk1,
				slimeWalk2 });
		snailWalking = new Animation(1 / 12, new TextureRegion[] { snailWalk1,
				snailWalk2 });

		spinnerHalfSpinning = new Animation(1 / 12, new TextureRegion[] {
				spinnerHalf, spinnerHalfSpin });
		spinnerHalfSpinning.setPlayMode(PlayMode.LOOP);

		backgroundLayer = new Texture[] { background, background2 };
	}

	public static void dispose() {
		font.dispose();
		objects.dispose();
		tiles.dispose();

	}

}
