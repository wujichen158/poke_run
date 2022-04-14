package com.qdu.pokerun.util;

public class Pair<A, B> {
    public A first;
    public B second;
    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }
}
