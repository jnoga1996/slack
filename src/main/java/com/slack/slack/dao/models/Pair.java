package com.slack.slack.dao.models;

public class Pair <Type1, Type2> {

    private Type1 firstValue;
    private Type2 secondValue;

    public Pair() {

    }

    public Pair(Type1 firstValue, Type2 secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public Type1 getFirstValue() {
        return firstValue;
    }

    public Type2 getSecondValue() {
        return secondValue;
    }
}
