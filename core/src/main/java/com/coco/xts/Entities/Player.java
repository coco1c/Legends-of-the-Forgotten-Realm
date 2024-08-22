package com.coco.xts.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.coco.xts.Entities.behaviors.Damageable;
import com.coco.xts.Entities.behaviors.Movable;
import com.coco.xts.Scenes.GameScene;
import com.coco.xts.Util.UpdatePosition;

public class Player extends Entity implements Damageable, Movable {
    private Vector2 velocity = new Vector2();
    public float speed = 5f;
    private float diagonalSpeed = speed / (float) Math.sqrt(2);
    private Sprite playerSkin;
    private float playerHealth;
    private float maxPlayerHealth;
    private float healthRegenSpeed;
    private GameScene screen;

    public Player(Sprite sprite, GameScene screen) {
        super(sprite);
        this.location = new Vector2(sprite.getX(), sprite.getY());
        playerSkin = sprite;
        this.screen = screen;
        centerPlayer();
    }

    @Override
    public void draw(Batch batch){
        update(Gdx.graphics.getDeltaTime());
        super.draw(batch);
    }

    @Override
    public void update(float delta) {
        velocity.set(0, 0);
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            velocity.x = speed;
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            velocity.x = -speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            velocity.y = speed;
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            velocity.y = -speed;
        }
        if (velocity.x != 0 && velocity.y != 0) {
            velocity.scl(diagonalSpeed / speed);
        }
        Vector2 pos = UpdatePosition.updatedPosition(this, velocity);
        setPosition(pos.x, pos.y);
        screen.gameCam.moveTo(pos);
    }

    @Override
    public void damage(float i) {
        if (playerHealth - i < 0) {
            playerHealth = 0;
            return;
        }
        playerHealth -= i;
    }

    @Override
    public void heal(float i) {
        if (playerHealth + i > maxPlayerHealth){
            playerHealth = maxPlayerHealth;
            return;
        }
        playerHealth += i;
    }

    public void centerPlayer(){
        location.set(Gdx.graphics.getWidth() / 2 - this.getWidth() / 2, Gdx.graphics.getHeight() / 2 - this.getHeight() / 2);
        this.setPosition(location.x, location.y);
    }

    @Override
    public void moveEntity(Vector2 to) {
        float playerWidth = this.getWidth();
        float playerHeight = this.getHeight();

        if (to.x != 0 && to.y != 0) {
            to.scl((float) Math.sqrt(2) / 2);
        }

        float newX = this.getX() + to.x;
        float newY = this.getY() + to.y;

        float mapLowestX = screen.getMapLowestX() - screen.getMapSize().getFirstValue() / 2;
        float mapHighestX = screen.getMapHighestX() - playerWidth;
        float mapLowestY = screen.getMapLowestY() - screen.getMapSize().getSecondValue() / 2;
        float mapHighestY = screen.getMapHighestY() - playerHeight;

        if (newX < mapLowestX) {
            newX = mapLowestX;
        } else if (newX > mapHighestX) {
            newX = mapHighestX;
        }
        if (newY < mapLowestY) {
            newY = mapLowestY;
        } else if (newY > mapHighestY) {
            newY = mapHighestY;
        }

        setPosition(newX, newY);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        this.location.set(x, y);
    }
}
