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
			slammingBlock, slimeWalk1, slimeWalk2, snailWalk1, snailWalk2,
			spinnerHalf, spinnerHalfSpin, yellowBlock;
	public static Texture objects, background, background2;

	public static Texture[] backgroundLayer;

	public static void load() {
		font = new BitmapFont(Gdx.files.internal("white.fnt"));

		objects = new Texture(Gdx.files.internal("objects.png"));
		objects.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		objects.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		background = new Texture(Gdx.files.internal("bg_grasslands.png"));
		background2 = new Texture(Gdx.files.internal("bg_shroom.png"));

		dmitriWalk1 = new TextureRegion(objects, 813, 483, 68, 83);
		dmitriWalk2 = new TextureRegion(objects, 692, 80, 70, 86);

		elevator = new TextureRegion(objects, 466, 28, 32, 24);

		slammingBlock = new TextureRegion(objects, 69, 292, 32, 32);

		slimeWalk1 = new TextureRegion(objects, 973, 353, 50, 28);
		slimeWalk2 = new TextureRegion(objects, 467, 18, 51, 26);

		spinnerHalf = new TextureRegion(objects, 64, 1, 61, 30);
		spinnerHalfSpin = new TextureRegion(objects, 152, 38, 63, 31);

		snailWalk1 = new TextureRegion(objects, 339, 419, 60, 40);
		snailWalk2 = new TextureRegion(objects, 282, 38, 57, 31);

		dmitriWalking = new Animation(1 / 12f, new TextureRegion[] {
				dmitriWalk1, dmitriWalk2 });

		dmitriWalking.setPlayMode(PlayMode.LOOP_PINGPONG);

		slimeWalking = new Animation(1 / 12f, new TextureRegion[] { slimeWalk1,
				slimeWalk2 });
		snailWalking = new Animation(1 / 12f, new TextureRegion[] { snailWalk1,
				snailWalk2 });
		spinnerHalfSpinning = new Animation(1 / 10f, new TextureRegion[] {
				spinnerHalf, spinnerHalfSpin });
		spinnerHalfSpinning.setPlayMode(PlayMode.LOOP_PINGPONG);

		backgroundLayer = new Texture[] { background, background2 };
	}

	public static void dispose() {
		font.dispose();
		objects.dispose();

	}

}
