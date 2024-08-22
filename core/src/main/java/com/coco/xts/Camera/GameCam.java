package com.coco.xts.Camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.coco.xts.Util.Twos;

import javax.swing.text.View;
import java.util.Vector;

public class GameCam {
    private Viewport viewport;
    private Twos mapSize;
    private OrthographicCamera camera;

    public GameCam(Twos twos){
        this.mapSize = twos;
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(mapSize.getFirstValue(), mapSize.getSecondValue(), camera);
        camera.position.set(mapSize.getFirstValue() / 2f, mapSize.getSecondValue() / 2f, 0);
        update();
    }

    public void update() {
        viewport.apply();
    }

    public void resize(int width, int height){
        viewport.update(width, height);
    }

    public void moveTo(Vector2 to){
        camera.position.set(to.x, to.y, 0);
    }

    public void setZoom(float zoom){
        camera.zoom = zoom;
    }

    public void moveBy(Vector2 by){
        camera.translate(by.x, by.y, 0);
    }

    public void resetZoom() {
        camera.zoom = 1.0f;
    }

    public OrthographicCamera getCamera(){
        return camera;
    }

    public Viewport getViewport(){
        return viewport;
    }
}
