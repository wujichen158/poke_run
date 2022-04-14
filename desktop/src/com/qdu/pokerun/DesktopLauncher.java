package com.qdu.pokerun;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.qdu.pokerun.PokeRun;
import com.qdu.pokerun.lib.LibMisc;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle(LibMisc.TITLE);
		config.setWindowedMode(LibMisc.SCREEN_W, LibMisc.SCREEN_H);
		config.setWindowIcon("img/icon/Login_icon.png");
		new Lwjgl3Application(new PokeRun(), config);
	}
}
