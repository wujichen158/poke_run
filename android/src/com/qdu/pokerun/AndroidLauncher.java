package com.qdu.pokerun;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AndroidLauncher extends AndroidApplication implements SensorEventListener {
	@Override
	protected void onCreate (Bundle savedInstanceState) {

//		ping1("192.168.43.94");

//		Log.i("Poke Run", "!!!!!!!!!!!!!!!!!"+isNetworkConnected());
//		StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
//		StrictMode.VmPolicy old = StrictMode.getVmPolicy();
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().build());
		super.onCreate(savedInstanceState);
		requestPermissions(new String[]{Manifest.permission.ACTIVITY_RECOGNITION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
//		config.useAccelerometer=true;
//		config.useCompass=true;

//		startForegroundService(new Intent(this, AndroidStepService.class));
		startService(new Intent(this, AndroidStepService.class));
		initialize(new PokeRun(new AndroidStepCounter()), config);
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
			while ((line = buf.readLine()) != null) {
				Log.i("Poke Run", "ping ping ping ping ping!!!!!"+line);
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}
}
