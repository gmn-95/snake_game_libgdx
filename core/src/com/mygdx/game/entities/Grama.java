package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.info.GameInfo;

public class Grama {

    private final SpriteBatch spriteBatch;

    private static final String pathTexturaGrama = "tileGrass9.png";

    private final Texture texture;

    public Grama(SpriteBatch spriteBatch) {
        this.spriteBatch = spriteBatch;
        texture = new Texture(pathTexturaGrama);
    }

    /**
     * Adiciona a grama no cenário
     * */
    public void init(){
        for (int x = 0; x < GameInfo.SCREAM_WIDTH; x+= GameInfo.SCREAM_WIDTH / 8){
            for (int y = 0; y < GameInfo.SCREAM_HEIGHT; y+= GameInfo.SCREAM_WIDTH / 8){
                spriteBatch.draw(texture, x, y);
            }
        }
    }

    public void disope(){
        texture.dispose();
    }


}
