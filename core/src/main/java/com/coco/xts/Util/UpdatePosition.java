package com.coco.xts.Util;

import com.badlogic.gdx.math.Vector2;
import com.coco.xts.Entities.Entity;

public class UpdatePosition {
    public static Vector2 updatedPosition(Entity entity, Vector2 to) {
        Vector2 from = entity.location;
        float x = from.x + to.x;
        float y = from.y + to.y;
        return new Vector2(x, y);
    }
}
