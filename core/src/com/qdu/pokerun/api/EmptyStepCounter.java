package com.qdu.pokerun.api;

public class EmptyStepCounter implements IStepCounter {

    @Override
    public int getSteps() {
        return -1;
    }
}
