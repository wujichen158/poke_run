package com.qdu.pokerun;

import com.qdu.pokerun.api.IStepCounter;

public class IOSStepCounter implements IStepCounter {

    private int step;
    @Override
    public int getSteps() {
        return this.step;
    }

    public void setSteps(int step) {
        this.step = step;
    }
}
