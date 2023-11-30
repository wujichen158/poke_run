package com.qdu.pokerun;

import com.qdu.pokerun.api.IStepCounter;

public class AndroidStepCounter implements IStepCounter {


    @Override
    public int getSteps() {
        return AndroidStepService.getSteps();
    }
}