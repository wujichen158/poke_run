package com.qdu.pokerun;

import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.qdu.pokerun.core.PokeRunServer;

public class ServerLauncher {
	public static void main (String[] arg) {
		HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
		new HeadlessApplication(new PokeRunServer(), config);
	}
}
