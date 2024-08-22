package com.coco.xts;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.coco.xts.Scenes.Levels.Level_1;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    public static Main instance;

    public SpriteBatch batch;

    @Override
    public void create() {
        instance = this;
        batch = new SpriteBatch();
        setScreen(new Level_1());
    }
    public void render() {
        super.render();
    }
    public void dispose(){
        batch.dispose();
    }
    public static Main getInstance() {
        return instance;
    }
}
