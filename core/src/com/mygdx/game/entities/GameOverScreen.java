package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.info.GameInfo;

public class GameOverScreen {

    private static final String pathGameOver = "game_over2.png";
    private final Texture gameOverTexture;
    private final BitmapFont font;
    private boolean isGameOver;
    private final SpriteBatch spriteBatch;

    public GameOverScreen(SpriteBatch spriteBatch){
        this.font = new BitmapFont();
        this.gameOverTexture = new Texture(pathGameOver);
        this.isGameOver = false;
        this.spriteBatch = spriteBatch;
    }

    public void gameOverScreen(){
        spriteBatch.draw(gameOverTexture, (GameInfo.SCREAM_WIDTH - 150) / 2f, GameInfo.SCREAM_HEIGHT / 2f);
        font.setColor(Color.WHITE);
        font.draw(spriteBatch, "Pressione ENTER para reiniciar", Gdx.graphics.getWidth() / 2f - 100, Gdx.graphics.getHeight() / 2f - 40);
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setIsGameOver(boolean isGameOver){
        this.isGameOver = isGameOver;
    }

    public void dispose(){
        gameOverTexture.dispose();
        font.dispose();
    }
}
