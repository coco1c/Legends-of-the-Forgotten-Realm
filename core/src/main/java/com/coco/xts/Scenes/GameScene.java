package com.coco.xts.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.coco.xts.Camera.GameCam;
import com.coco.xts.Entities.Player;
import com.coco.xts.Main;
import com.coco.xts.Util.Distance;
import com.coco.xts.Util.Twos;
import com.coco.xts.Util.UpdatePosition;

import javax.swing.plaf.PanelUI;

public abstract class GameScene implements Screen {
    private Twos mapSize;
    public GameCam gameCam;
    private TiledMap tiledMap;
    private TmxMapLoader mapLoader;
    private OrthogonalTiledMapRenderer mapRenderer;
    private Player player;
    public GameScene (String mapPath) {
        mapLoader = new TmxMapLoader();
        tiledMap = mapLoader.load(mapPath);
    }
    @Override
    public void show(){
        mapRenderer = new OrthogonalTiledMapRenderer(tiledMap, 3);
        mapSize = Twos.from(tiledMap.getProperties().get("width", Integer.class) * tiledMap.getProperties().get("tilewidth", Integer.class),
            tiledMap.getProperties().get("height", Integer.class) * tiledMap.getProperties().get("tileheight", Integer.class));
        gameCam = new GameCam(mapSize);
        gameCam.getCamera().position.set(gameCam.getCamera().viewportWidth / 2f, gameCam.getCamera().viewportHeight / 2f, 0);
    }

    @Override
    public void render(float v){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameCam.update();
        mapRenderer.setView(gameCam.getCamera());
        mapRenderer.render();
        onRender(v);

        Main.getInstance().batch.begin();

        if (player != null)
            player.draw(Main.getInstance().batch);
        onDraw(Main.getInstance().batch);

        Main.getInstance().batch.end();
    }

    @Override
    public void resize(int i, int i1) {
        gameCam.resize(i, i1);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}

    public abstract void onShow();
    public abstract void onRender(float v);
    public abstract void onDraw(SpriteBatch batch);
    public abstract void onPause();
    public abstract void onResume();
    public abstract void onHide();
    public abstract void onDispose();

    public void addPlayer(Player player){
        this.player = player;
        if (player != null) {
            Vector2 pos = new Vector2(tiledMap.getProperties().get("width", Integer.class) * tiledMap.getProperties().get("tilewidth", Integer.class) / 2f,
                tiledMap.getProperties().get("height", Integer.class) * tiledMap.getProperties().get("tileheight", Integer.class) / 2f);
            player.setSpawnLocation(pos);
        }
    }


    public float getMapLowestY() {
        var mapObjects = tiledMap.getLayers().get("border").getObjects();
        float lowestY = Float.MAX_VALUE;
        for (var object : mapObjects) {
            if (object.getName().equals("border_down")) {
                float y = object.getProperties().get("y", Float.class) + (float) gameCam.getViewport().getWorldHeight() / 2;
                return y;
            }
        }
        return lowestY;
    }

    public float getMapLowestX() {
        var mapObjects = tiledMap.getLayers().get("border").getObjects();
        float lowestX = Float.MAX_VALUE;
        for (var object : mapObjects) {
            if (object.getName().equals("border_left")) {
                float x = object.getProperties().get("x", Float.class) + gameCam.getViewport().getWorldWidth() / 2f;
                return x;
            }
        }
        return lowestX;
    }
    public float getMapHighestY() {
        var mapObjects = tiledMap.getLayers().get("border").getObjects();
        float highestY = 0;
        for (var object : mapObjects) {
            if (object.getName().equals("border_up")) {
                float y = object.getProperties().get("y", Float.class) + gameCam.getViewport().getWorldHeight() * 1.633f;
                return y;
            }
        }
        return highestY;
    }

    public float getMapHighestX() {
        var mapObjects = tiledMap.getLayers().get("border").getObjects();
        float highestX = 0;
        for (var object : mapObjects) {
            if (object.getName().equals("border_right")) {
                float x = object.getProperties().get("x", Float.class) + gameCam.getViewport().getWorldWidth() * 1.1f;
                return x;
            }
        }
        return highestX;
    }
    public GameCam getGameCam() {
        return gameCam;
    }
    public OrthogonalTiledMapRenderer getMapRenderer() {
        return mapRenderer;
    }
    public TiledMap getTiledMap() {
        return tiledMap;
    }
    public Twos getMapSize() {
        return mapSize;
    }

/*    public void moveGameCam(Vector2 pos) {
        float mapLowestX = getMapLowestX();
        float mapHighestX = getMapHighestX();
        float mapLowestY = getMapLowestY();
        float mapHighestY = getMapHighestY();


        if (pos.x < mapLowestX) {
            pos.x = mapLowestX;
            player.moveEntity(UpdatePosition.updatedPosition(player, new Vector2(-player.speed, 0)));
        } else if (pos.x > mapHighestX) {
            pos.x = mapHighestX;
            player.movePlayer(new Vector2(player.speed, 0));
        }

        if (pos.y < mapLowestY) {
            pos.y = mapLowestY;
            player.movePlayer(new Vector2(0, -player.speed));
        } else if (pos.y > mapHighestY) {
            pos.y = mapHighestY;
            player.movePlayer(new Vector2(0, player.speed));
        }


        gameCam.moveTo(pos);
    }*/

}
