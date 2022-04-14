package com.qdu.pokerun;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {

//		ping1("192.168.43.94");

//		Log.i("Poke Run", "!!!!!!!!!!!!!!!!!"+isNetworkConnected());
//		StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
//		StrictMode.VmPolicy old = StrictMode.getVmPolicy();
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().build());
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer=false;
		config.useCompass=false;
		initialize(new PokeRun(), config);
	}

	private boolean isNetworkConnected() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		// There are no active networks.
		return ni != null;
	}

	public void ping1(String ipAddress) {
		String line;
		try {
			Process pro = Runtime.getRuntime().exec("ping " + ipAddress);
			BufferedReader buf = new BufferedReader(new InputStreamReader(
					pro.getInputStream()));
			while ((line = buf.readLine()) != null)
				Log.i("Poke Run", "ping ping ping ping ping!!!!!"+line);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
