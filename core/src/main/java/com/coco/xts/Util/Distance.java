package com.coco.xts.Util;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Distance {
    public static float distance(Vector2 from, Vector2 to){
        return from.dst(to);
    }

    public static float distance(Vector3 from, Vector2 to) {
        return new Vector2(from.x, from.y).dst(to);
    }

    public static float distance(Vector2 from, Vector3 to) {
        return from.dst(to.x, to.y);
    }

    public static float distance(Vector3 from, Vector3 to) {
        return new Vector2(from.x, from.y).dst(to.x, to.y);
    }
}
