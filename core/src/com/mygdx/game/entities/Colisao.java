package com.mygdx.game.entities;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.info.GameInfo;

public class Colisao {

    private final Snake snake;
    private final Rato rato;
    private boolean colidiu = false;

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
        // Ignora a verificação de colisão no início, quando a cobra ainda está na posição inicial
        if (snake.isPosicaoInicial()) return;

        // Iterando pelas partes do corpo, começando do índice 6 (evitando a cabeça)
        for (int i = 6; i < snake.getPartesCorpo().size(); i++) {
            ParteCorpo parteCorpo = snake.getPartesCorpo().get(i); // Parte do corpo

            // Obtendo o Sprite da cabeça e da parte do corpo
            Sprite cabecaSprite = snake.getSpriteCabeca();
            Sprite parteSprite = parteCorpo.getSprite();

            // Verificando as coordenadas e tamanhos dos retângulos
            Rectangle cabecaRectangle = cabecaSprite.getBoundingRectangle();
            Rectangle parteRectangle = parteSprite.getBoundingRectangle();

            // Adicionando logs para depuração
//            System.out.println("Cabeça: " + cabecaRectangle + " | Parte: " + parteRectangle);

            // Verificando se os retângulos se sobrepõem (colidem)
            if (cabecaRectangle.overlaps(parteRectangle)) {
                // Aqui podemos adicionar uma verificação para garantir que a cabeça realmente passou por cima da parte
                if (cabecaRectangle.x > parteRectangle.x && cabecaRectangle.y > parteRectangle.y) {
                    System.out.println("Colisão detectada com parte do corpo " + i);
                    break; // Colisão detectada, então interrompe a verificação
                }
            }
        }
    }






    public boolean isColidiu(){return this.colidiu;}
}
