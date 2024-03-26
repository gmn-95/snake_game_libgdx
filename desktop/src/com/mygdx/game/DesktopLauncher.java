package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.SnakeGame;
import com.mygdx.game.info.GameInfo;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(GameInfo.FPS);
		config.setTitle(GameInfo.TITULO);
		config.setWindowedMode(GameInfo.SCREAM_WIDTH, GameInfo.SCREAM_HEIGHT);
		new Lwjgl3Application(new SnakeGame(), config);
	}
}
