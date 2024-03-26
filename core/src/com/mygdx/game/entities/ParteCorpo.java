package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class ParteCorpo {

    private int x;
    private int y;
    private final Sprite sprite;

    public ParteCorpo(Sprite sprite) {
        this.sprite = sprite;
    }

    public ParteCorpo(int x, int y, Sprite sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Sprite getSprite() {
        sprite.setRotation(70);
        return sprite;
    }
}
