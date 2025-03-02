package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.Objects;

public class ParteCorpo {

    private int x;
    private int y;
    private final Sprite sprite;
    private boolean isCabeca;

    public ParteCorpo(int x, int y, Sprite sprite, boolean isCabeca) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.sprite.setPosition(x, y);
        this.isCabeca = isCabeca;
    }

    public boolean isCabeca() {
        return isCabeca;
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
        return sprite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParteCorpo that = (ParteCorpo) o;
        return getX() == that.getX() && getY() == that.getY() && isCabeca() == that.isCabeca() && Objects.equals(getSprite(), that.getSprite());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY(), getSprite(), isCabeca());
    }

    @Override
    public String toString() {
        return "ParteCorpo{" +
                "x=" + x +
                ", y=" + y +
                ", sprite=" + sprite +
                ", isCabeca=" + isCabeca +
                '}';
    }
}
