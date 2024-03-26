package com.mygdx.game.entities;


public class Colisao {

    private final Snake snake;
    private final Rato rato;

    public Colisao(Snake snake, Rato comida) {
        this.snake = snake;
        this.rato = comida;
    }

    public void checaColisaoComRato(){
        snake.animacaoComer(rato);

        if(snake.getSpriteCabeca().getBoundingRectangle().overlaps(rato.getSpriteRato().getBoundingRectangle())){
            rato.novaPosicao();
            snake.adicionaParteCorpo();
        }
        snake.reset();
    }
}
