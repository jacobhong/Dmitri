package com.hongj.dmitri.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.hongj.dmitri.DmitriGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = 480;
		config.width = 800;
		config.title = DmitriGame.TITLE;
		
		new LwjglApplication(new DmitriGame(), config);
	}
}
