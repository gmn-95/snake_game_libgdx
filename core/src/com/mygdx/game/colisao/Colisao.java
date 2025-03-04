package com.mygdx.game.colisao;


import com.mygdx.game.food.Comida;
import com.mygdx.game.utils.GameInfo;
import com.mygdx.game.snake.ParteCorpo;
import com.mygdx.game.snake.Snake;

import java.util.LinkedList;

public class Colisao {

    private final Snake snake;
    private final Comida comida;
    private boolean colidiu = false;

    /**
     * às vezes a cobra pode encostar em seu próprio corpo, mas não significa fim de jogo.
     * Então temos uma pequena tolerância
     */
    private final static int TAMANHO_TOLERANCIA_COLISAO = 20;

    public Colisao(Snake snake, Comida comida) {
        this.snake = snake;
        this.comida = comida;
    }

    public void checaColisaoComComida(){
        snake.animacaoComer(comida);

        //codigo que também funciona, mas qualquer contato mínimo já entende q a snake comeu o rato
//        if(snake.getSpriteCabeca().getBoundingRectangle().overlaps(rato.getSpriteRato().getBoundingRectangle())){
        ParteCorpo cabeca = snake.getPartesCorpo().getFirst();
        int headX = cabeca.getX();
        int headY = cabeca.getY();

        float comidaX = comida.getSprite().getX();
        float comidaY = comida.getSprite().getY();

        //nem sempre a cobra passa 100% por cima do comida
        //nesse caso, temos uma tolerancia para ela nao 'comer'
        boolean colisaoX = Math.abs(headX - comidaX) < TAMANHO_TOLERANCIA_COLISAO;
        boolean colisaoY = Math.abs(headY - comidaY) < TAMANHO_TOLERANCIA_COLISAO;

        if(colisaoX && colisaoY){
            comida.novaPosicao(snake.getPartesCorpo());
            snake.isAdicionaParteCorpo();
        }
        snake.resetarTexturaCabeca();
    }

    public void checaColisaoComParede(){
        boolean isBateuComParedeEsquerda = snake.getSpriteCabeca().getBoundingRectangle().contains(-3, snake.getSpriteCabeca().getY());
        boolean isBateuComParedeDireita = snake.getSpriteCabeca().getBoundingRectangle().contains(GameInfo.SCREAM_WIDTH + 3, snake.getSpriteCabeca().getY());
        boolean isBateuComParedeInferior = snake.getSpriteCabeca().getBoundingRectangle().contains(snake.getSpriteCabeca().getX(), -3);
        boolean isBateuComParedeSuperior = snake.getSpriteCabeca().getBoundingRectangle().contains(snake.getSpriteCabeca().getX(), GameInfo.SCREAM_HEIGHT + 3);

        if (isBateuComParedeEsquerda || isBateuComParedeDireita || isBateuComParedeInferior || isBateuComParedeSuperior) {
            System.out.println("BATEU PAREDE");
            this.colidiu = true;
        }
    }

    public void checaColisaoComCorpo() {
        if (snake.isPosicaoInicial()) return;

        LinkedList<ParteCorpo> parteCorpos = snake.getPartesCorpo();

        ParteCorpo cabeca = parteCorpos.getFirst();
        int headX = cabeca.getX();
        int headY = cabeca.getY();

        // Iterando pelas partes do corpo (evitando a cabeça) - Iniciando pela quarta parte.
        for (int i = 4; i < parteCorpos.size(); i++) {
            ParteCorpo parte = parteCorpos.get(i);

            int parteX = parte.getX();
            int parteY = parte.getY();

            boolean colisaoX = Math.abs(headX - parteX) < TAMANHO_TOLERANCIA_COLISAO;
            boolean colisaoY = Math.abs(headY - parteY) < TAMANHO_TOLERANCIA_COLISAO;

            if (colisaoX && colisaoY) {
                System.out.println("Colisão detectada com o corpo!");
                this.colidiu = true;
            }
        }
    }

    public boolean isColidiu(){return this.colidiu;}
}
