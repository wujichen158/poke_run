package com.qdu.pokerun;

import com.badlogic.gdx.backends.iosrobovm.IOSApplication;
import com.badlogic.gdx.backends.iosrobovm.IOSApplicationConfiguration;

import org.robovm.apple.coremotion.CMPedometer;
import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.foundation.NSDate;
import org.robovm.apple.uikit.UIApplication;
import org.robovm.apple.uikit.UIApplicationLaunchOptions;

public class IOSLauncher extends IOSApplication.Delegate {

    private CMPedometer pedometer;
    private IOSStepCounter stepCounter;
    @Override
    protected IOSApplication createApplication() {
        stepCounter = new IOSStepCounter();
        IOSApplicationConfiguration config = new IOSApplicationConfiguration();
        return new IOSApplication(new PokeRun(stepCounter), config);
    }

    public static void main(String[] argv) {
        NSAutoreleasePool pool = new NSAutoreleasePool();
        UIApplication.main(argv, null, IOSLauncher.class);
        pool.close();
    }

    @Override
    public boolean didFinishLaunching(UIApplication application, UIApplicationLaunchOptions launchOptions) {
        boolean result = super.didFinishLaunching(application, launchOptions);

        // Initialize the pedometer
        pedometer = new CMPedometer();
        pedometer.startPedometerUpdates(new NSDate(), (data, error) -> {
            if (error != null || stepCounter == null) {
                System.out.println("Error: " + error);
            } else {
                stepCounter.setSteps(data.getNumberOfSteps().intValue());
            }
        });

        return result;
    }

    @Override
    public void willTerminate(UIApplication application) {
        super.willTerminate(application);

        // Stop pedometer updates when the app is about to terminate
        pedometer.stopPedometerUpdates();
    }
}
