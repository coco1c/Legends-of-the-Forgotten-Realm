package com.coco.xts.Scenes.Levels;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.coco.xts.Entities.Player;
import com.coco.xts.Scenes.GameScene;

public class Level_1 extends GameScene {
    public Level_1() {
        super("maps/map_level_2.tmx");
        addPlayer(new Player(new Sprite(new Texture("tile_0112.png")), this));
    }

    @Override
    public void onShow() {

    }

    @Override
    public void onRender(float v) {

    }

    @Override
    public void onDraw(SpriteBatch batch) {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onHide() {

    }

    @Override
    public void onDispose() {

    }
}
