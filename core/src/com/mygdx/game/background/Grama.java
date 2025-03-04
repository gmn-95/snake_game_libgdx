package com.mygdx.game.background;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.utils.GameInfo;

public class Grama {

    private final SpriteBatch spriteBatch;

    private static final String pathTexturaGrama = "tileGrass12_redimen.png";

    private final Texture texture;

    public Grama(SpriteBatch spriteBatch) {
        this.spriteBatch = spriteBatch;
        texture = new Texture(pathTexturaGrama);
    }

    /**
     * Adiciona a grama no cenário
     * */
    public void init(){
        for (int x = 0; x < GameInfo.SCREAM_WIDTH; x += GameInfo.SCREAM_WIDTH / 3){
            for (int y = 0; y < GameInfo.SCREAM_HEIGHT; y += GameInfo.SCREAM_WIDTH / 3){
                spriteBatch.draw(texture, x, y);
            }
        }
    }

    public void disope(){
        texture.dispose();
    }
}
