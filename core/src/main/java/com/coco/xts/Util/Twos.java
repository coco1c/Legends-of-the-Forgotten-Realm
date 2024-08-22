package com.coco.xts.Util;

import java.util.List;

public class Twos {
    private float i, i1;

    public Twos(float i, float i1) {
        this.i = i;
        this.i1 = i1;
    }

    public float getFirstValue() {
        return i;
    }

    public float getSecondValue() {
        return i1;
    }

    public List<Float> getValues() {
        return List.of(i, i1);
    }
    public static Twos from(float i, float i1){
        return new Twos(i, i1);
    }
}
