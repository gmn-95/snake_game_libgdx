package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class Rato {

    private final SpriteBatch spriteBatch;
    private static final String pathTexturaRato = "rat.png";
    private final Texture texture;
    private final Sprite spriteRato;
    private boolean isPosicaoInicial = true;

    public Rato(SpriteBatch spriteBatch) {
        this.spriteBatch = spriteBatch;
        this.texture = new Texture(pathTexturaRato);
        this.spriteRato = new Sprite(texture);
    }

    public void desenhaComida(){
        if(isPosicaoInicial){
            spriteRato.setPosition(50, 50);
        }
        spriteRato.draw(spriteBatch);
    }

    public void novaPosicao(){
        Random random = new Random();
        int x = Math.abs(random.nextInt(Gdx.graphics.getWidth()) - 10);
        int y = Math.abs(random.nextInt(Gdx.graphics.getHeight()) - 10);

        spriteRato.setPosition(x, y);
        isPosicaoInicial = false;
    }

    public void disope(){
        texture.dispose();
    }

    public Sprite getSpriteRato() {
        return spriteRato;
    }
}