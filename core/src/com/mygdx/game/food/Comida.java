package com.mygdx.game.food;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.snake.ParteCorpo;

import java.util.LinkedList;
import java.util.Random;

public abstract class Comida {

    private final SpriteBatch spriteBatch;
    private final Texture texture;
    private final Sprite sprite;
    private boolean isPosicaoInicial = true;
    private final String pathTextura;

    protected Comida(SpriteBatch spriteBatch, String pathTextura) {
        this.spriteBatch = spriteBatch;
        this.texture = new Texture(pathTextura);
        this.sprite = new Sprite(texture);
        this.pathTextura = pathTextura;
    }

    public void desenhaComida(){
        if(isPosicaoInicial){
            sprite.setPosition(50, 50);
        }
        sprite.draw(spriteBatch);
    }

    public void novaPosicao(LinkedList<ParteCorpo> parteCorposSnake) {
        Random random = new Random();
        int x, y;
        int raio = 8;
        int larguraTela = Gdx.graphics.getWidth();
        int alturaTela = Gdx.graphics.getHeight();

        boolean posicaoValida;

        do {
            x = random.nextInt(larguraTela - (raio * 2)) + raio;
            y = random.nextInt(alturaTela - (raio * 2)) + raio;

            posicaoValida = (x > 10 && x < larguraTela - 10) && (y > 10 && y < alturaTela - 10);

            for (ParteCorpo parteCorpo : parteCorposSnake) {
                int parteCorpoX = parteCorpo.getX();
                int parteCorpoY = parteCorpo.getY();

                boolean emCimaDaSnakeX = Math.abs(parteCorpoX - x) < 15;
                boolean emCimaDaSnakeY = Math.abs(parteCorpoY - y) < 15;

                if (emCimaDaSnakeX && emCimaDaSnakeY) {
                    posicaoValida = false;
                    break;
                }
            }

        } while (!posicaoValida);

        sprite.setPosition(x, y);
        isPosicaoInicial = false;
    }

    public void disope(){
        texture.dispose();
    }

    public Sprite getSprite() {
        return this.sprite;
    }


}
