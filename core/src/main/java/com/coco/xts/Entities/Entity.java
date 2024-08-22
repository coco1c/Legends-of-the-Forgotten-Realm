package com.coco.xts.Entities;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity extends Sprite {
    public Vector2 location;
    public Vector2 spawnLocation;

    public Entity(Sprite sprite){
        super(sprite);
    }

    public void setSpawnLocation(Vector2 location){
        spawnLocation = location;
    }

    public abstract void update(float delta);
}
