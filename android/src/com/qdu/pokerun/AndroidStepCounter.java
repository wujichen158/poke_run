package com.qdu.pokerun;

import android.app.AlarmManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;

import com.qdu.pokerun.api.StepCounter;

import java.util.Date;
import java.util.Objects;

public class AndroidStepCounter implements StepCounter {

    @Override
    public int getSteps() {
        return AndroidStepService.getSteps();
    }
}