package com.hongj.dmitri;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;

public class Assets {

	public static BitmapFont font;

	public static Animation dmitriWalking;
	public static TextureRegion dmitriWalking1, dmitriWalking2, yellowBlock;
	public static Texture objects, tiles, background, background2;

	public static Texture[] backgroundLayer;

	public static void load() {
		font = new BitmapFont(Gdx.files.internal("white.fnt"));

		objects = new Texture(Gdx.files.internal("Objects.png"));
		tiles = new Texture(Gdx.files.internal("tiles.png"));
		background = new Texture(Gdx.files.internal("bg_grasslands.png"));
		background2 = new Texture(Gdx.files.internal("bg_shroom.png"));
		dmitriWalking1 = new TextureRegion(objects, 509, 224, 26, 32);
		dmitriWalking2 = new TextureRegion(objects, 592, 329, 26, 32);
		yellowBlock = new TextureRegion(tiles, 35, 511, 32, 32);

		dmitriWalking = new Animation(1 / 12f, new TextureRegion[] {
				dmitriWalking1, dmitriWalking2 });
		dmitriWalking.setPlayMode(PlayMode.LOOP_PINGPONG);

		backgroundLayer = new Texture[] { background, background2 };
	}

	public static void dispose() {
		font.dispose();
		objects.dispose();
		tiles.dispose();

	}

}
