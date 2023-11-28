package com.qdu.pokerun.api;

public class EmptyStepCounter implements StepCounter {

    @Override
    public int getSteps() {
        return -1;
    }
}
