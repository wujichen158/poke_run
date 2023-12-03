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

import java.util.Date;
import java.util.Objects;

public class AndroidStepService extends Service implements SensorEventListener {

    private final static long MICROSECONDS_IN_ONE_MINUTE = 60000000;
    private final static long SAVE_OFFSET_TIME = AlarmManager.INTERVAL_HOUR;
    private final static int SAVE_OFFSET_STEPS = 500;

    private static int steps;
    private static int lastSaveSteps;
    private static long lastSaveTime;

//    private final BroadcastReceiver shutdownReceiver = new ShutdownRecevier();

    public static int getSteps() {
        return steps;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.values[0] > Integer.MAX_VALUE) {
            if (BuildConfig.DEBUG) {
                System.out.println("probably not a real value: " + event.values[0]);
            }
        } else {
            steps = (int) event.values[0];
            updateIfNecessary();
        }
    }

    /**
     * @return true, if notification was updated
     */
    private boolean updateIfNecessary() {
        if (steps > lastSaveSteps + SAVE_OFFSET_STEPS ||
                (steps > 0 && System.currentTimeMillis() > lastSaveTime + SAVE_OFFSET_TIME)) {
            if (BuildConfig.DEBUG){
                System.out.println(
                        "saving steps: steps=" + steps + " lastSave=" + lastSaveSteps +
                                " lastSaveTime=" + new Date(lastSaveTime));
            }

            //Save db here...

            lastSaveSteps = steps;
            lastSaveTime = System.currentTimeMillis();
            showNotification(); // update notification
//            WidgetUpdateService.enqueueUpdate(this);
            return true;
        } else {
            return false;
        }
    }

    private void showNotification() {
//        startForeground(NOTIFICATION_ID, getNotification(this));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        if (BuildConfig.DEBUG) {
            System.out.println(sensor.getName() + " accuracy changed: " + accuracy);
        }
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        reRegisterSensor();
        registerBroadcastReceiver();
        if (!updateIfNecessary()) {
            showNotification();
        }

        // restart service every hour to save the current step count
//        long nextUpdate = Math.min(Util.getTomorrow(), System.currentTimeMillis() + AlarmManager.INTERVAL_HOUR);
//        if (BuildConfig.DEBUG) System.out.println("next update: " + new Date(nextUpdate).toLocaleString());
//        AlarmManager am = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
//        PendingIntent pi = PendingIntent.getService(getApplicationContext(), 2, new Intent(this, SensorListener.class),
//                        PendingIntent.FLAG_UPDATE_CURRENT);
//        .setAlarmWhileIdle(am, AlarmManager.RTC, nextUpdate, pi);

        return START_NOT_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            System.out.println("SensorListener onCreate");
        }
    }

    @Override
    public void onTaskRemoved(final Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        if (BuildConfig.DEBUG) {
            System.out.println("sensor service task removed");
        }
        // Restart service in 500 ms
//        ((AlarmManager) getSystemService(Context.ALARM_SERVICE))
//                .set(AlarmManager.RTC, System.currentTimeMillis() + 500, PendingIntent
//                        .getService(this, 3, new Intent(this, SensorListener.class), 0));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (BuildConfig.DEBUG) {
            System.out.println("SensorListener onDestroy");
        }
        try {
            SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
            sm.unregisterListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void registerBroadcastReceiver() {
        if (BuildConfig.DEBUG) {
            System.out.println("register broadcastreceiver");
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SHUTDOWN);
//        registerReceiver(shutdownReceiver, filter);
    }

    private void reRegisterSensor() {
        if (BuildConfig.DEBUG){
            System.out.println("re-register sensor listener");
        }
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        try {
            sm.unregisterListener(this);
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                System.out.println(e);
            }
            e.printStackTrace();
        }

        if (BuildConfig.DEBUG) {
            System.out.println("step sensors: " + sm.getSensorList(Sensor.TYPE_STEP_COUNTER).size());
            if (sm.getSensorList(Sensor.TYPE_STEP_COUNTER).size() < 1){
                return; // emulator
            }
            System.out.println("default: " + Objects.requireNonNull(sm.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)).getName());
        }

        // enable batching with delay of max 5 min
//        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_STEP_COUNTER),
//                SensorManager.SENSOR_DELAY_NORMAL, (int) (5 * MICROSECONDS_IN_ONE_MINUTE));
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_STEP_COUNTER),
                SensorManager.SENSOR_DELAY_UI);
    }

    class ShutdownRecevier extends BroadcastReceiver {

        @Override
        public void onReceive(final Context context, final Intent intent) {
            if (BuildConfig.DEBUG) {
                System.out.println("shutting down");
            }

            context.startService(new Intent(context, SensorEventListener.class));

            // if the user used a root script for shutdown, the DEVICE_SHUTDOWN
            // broadcast might not be send. Therefore, the app will check this
            // setting on the next boot and displays an error message if it's not
            // set to true
            context.getSharedPreferences("pedometer", Context.MODE_PRIVATE).edit()
                    .putBoolean("correctShutdown", true).apply();
        }

    }
}
