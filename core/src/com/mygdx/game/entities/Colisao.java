package com.mygdx.game.entities;


import com.mygdx.game.info.GameInfo;

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
            snake.isAdicionaParteCorpo();
        }
        snake.reset();
    }

    public void checaColisaoComParede(){
        boolean isBateuComParedeEsquerda = snake.getSpriteCabeca().getBoundingRectangle().contains(-3, snake.getSpriteCabeca().getY());
        boolean isBateuComParedeDireita = snake.getSpriteCabeca().getBoundingRectangle().contains(GameInfo.SCREAM_WIDTH + 3, snake.getSpriteCabeca().getY());
        boolean isBateuComParedeInferior = snake.getSpriteCabeca().getBoundingRectangle().contains(snake.getSpriteCabeca().getX(), -3);
        boolean isBateuComParedeSuperior = snake.getSpriteCabeca().getBoundingRectangle().contains(snake.getSpriteCabeca().getX(), GameInfo.SCREAM_HEIGHT + 3);

        if (isBateuComParedeEsquerda || isBateuComParedeDireita || isBateuComParedeInferior || isBateuComParedeSuperior) {
//            System.out.println("BATEU PAREDE");
        }
    }
}
