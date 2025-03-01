package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.LinkedList;

import static com.badlogic.gdx.Input.Keys.*;

public class Snake {

    private int x = 70;
    private int y = 450;
    private static final String pathTexturaCabeca = "snakehead.png";
    private static final String pathTexturaCabeca2 = "snakehead3.png";
    private static final String pathTexturaCorpo = "snakebody2.png";
    private static final int TAMANHO_CORPO = 20; // Tamanho de cada parte do corpo
    private static final int VELOCIDADE = 2; //
    private static final int TAMANHO_CORPO_INICIAL = 2; //
    private static final int VELOCIDADE_ATUALIZACAO_RENDER_CORPO = 15;
    private final Texture texturaCabeca;
    private final Texture texturaCorpo;
    private final LinkedList<ParteCorpo> partesCorpo;
    private final Sprite spriteCabeca;
    private final Sprite spriteCorpo;
    private final SpriteBatch spriteBatch;
    private int direcao = 0;
    private float rotacao = 0;
    private int direcaoAtual = 0;
    private boolean isAdiciona = false;
    private final float delay; // 200 milissegundos de delay
    private float tempoPassado = 0;

    public Snake(SpriteBatch spriteBatch) {
        this.spriteBatch = spriteBatch;
        partesCorpo = new LinkedList<>();
        texturaCabeca = new Texture(pathTexturaCabeca);
        texturaCorpo = new Texture(pathTexturaCorpo);
        spriteCabeca = new Sprite(texturaCabeca);
        spriteCorpo = new Sprite(texturaCorpo);
        delay = 0.10f;
    }

    public void desenhaCorpo() {
        corpoInicial();

        for (ParteCorpo parteCorpo : partesCorpo) {
            spriteCorpo.setPosition(parteCorpo.getX(), parteCorpo.getY());
            spriteCorpo.draw(spriteBatch);
        }
    }

    protected void corpoInicial(){
        if (partesCorpo.isEmpty()) {
            for (int i = 1; i <= TAMANHO_CORPO_INICIAL; i++) {
                ParteCorpo corpo = new ParteCorpo(x - (TAMANHO_CORPO * i), y, spriteCorpo); // Adiciona partes do corpo com um espaço fixo entre elas
                partesCorpo.add(corpo);
            }
        }
    }

    public void disope() {
        texturaCabeca.dispose();
        texturaCorpo.dispose();
    }

    public void desenhaCabeca() {
        spriteCabeca.draw(spriteBatch);
    }

    public void adicionaCorpoTeste(){
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            ParteCorpo corpo = new ParteCorpo(x - (TAMANHO_CORPO * partesCorpo.getFirst().getX()), y, new Sprite(texturaCorpo));
            partesCorpo.add(corpo);
        }
    }

    public void move() {
        int xAntesDeAtualizar = x;
        int yAntesDeAtualizar = y;

        defineDirecaoERotacao();
//        adicionaCorpoTeste();
        if (isAdiciona) adicionaParteCorpo();

        if (direcao == 0) {
            ParteCorpo parteCorpo = partesCorpo.remove(0);
            parteCorpo.setX(xAntesDeAtualizar);
            parteCorpo.setY(yAntesDeAtualizar);
            partesCorpo.add(parteCorpo);
        }

        spriteCabeca.setPosition(x, y);
        spriteCabeca.setRotation(rotacao);
        atualizaMovimentacaoDaCobra(xAntesDeAtualizar, yAntesDeAtualizar);
    }


    protected void adicionaParteCorpo(){
        ParteCorpo corpo = new ParteCorpo(x - (TAMANHO_CORPO * partesCorpo.getFirst().getX()), y, new Sprite(texturaCorpo));
        partesCorpo.add(corpo);
    }

    public void isAdicionaParteCorpo(){
        isAdiciona = true;
    }

    protected void atualizaMovimentacaoDaCobra(int xAntesDeAtualizar, int yAntesDeAtualizar){
        if(isAdiciona){
            isAdiciona = false;
        }

        if (Gdx.graphics.getFrameId() % VELOCIDADE_ATUALIZACAO_RENDER_CORPO == 0){// Ajusta a frequência de movimento das partes do corpo
            if(!partesCorpo.isEmpty()){
                ParteCorpo parteCorpo = partesCorpo.remove(0);
                parteCorpo.setX(xAntesDeAtualizar);
                parteCorpo.setY(yAntesDeAtualizar);
//                    parteCorpo.getSprite().setRotation(rotacao);
                partesCorpo.add(parteCorpo);
            }
        }
    }


    public void animacaoComer(Rato comida){

        //Calcula a distancia da cobra e a comida
        double xA = getSpriteCabeca().getX();
        double xB = comida.getSpriteRato().getX();

        double yA = getSpriteCabeca().getY();
        double yB = comida.getSpriteRato().getY();

        double result = Math.abs(Math.pow(xB - xA, 2) + Math.pow(yB - yA, 2));
        double distancia = Math.sqrt(result);

        if(distancia <= 50.0f){
            spriteCabeca.setTexture(new Texture(pathTexturaCabeca2));
        }
    }

    /**
     * reseta imagem cabeça cobra
     * */
    public void reset(){
        if (Gdx.graphics.getFrameId() % 15 == 0){// Ajusta a frequência de movimento das partes do corpo
            spriteCabeca.setTexture(new Texture(pathTexturaCabeca));
        }
    }

    protected void defineDirecaoERotacao() {
        // Incrementa o tempo passado desde a última atualização
        tempoPassado += Gdx.graphics.getDeltaTime();

        // Verifica se o delay foi alcançado
        if (tempoPassado >= delay) {
            // Atualiza a direção baseada nas teclas pressionadas, com o delay aplicado
            if (isKeyPressed(RIGHT, D) && naoEhDirecaoAtual(LEFT, A)) {
                rotacao = 90;
                setDirecao(RIGHT, D);
            } else if (isKeyPressed(LEFT, A) && naoEhDirecaoAtual(RIGHT, D)) {
                rotacao = -90;
                setDirecao(LEFT, A);
            } else if (isKeyPressed(UP, W) &&  naoEhDirecaoAtual(DOWN, S)) {
                rotacao = 180;
                setDirecao(UP, W);
            } else if (isKeyPressed(DOWN, S) && naoEhDirecaoAtual(UP, W)) {
                rotacao = 0;
                setDirecao(DOWN, S);
            }

            // Reseta o tempo passado para aplicar o próximo delay
            tempoPassado = 0;
        }
        moveNaDirecaoEscolhida();

    }

    protected boolean naoEhDirecaoAtual(int keyOption1, int keyOption2){
        return this.direcaoAtual != keyOption1 && this.direcaoAtual != keyOption2;
    }

    protected void setDirecao(int keyOption1, int keyOption2){
        direcao = Gdx.input.isKeyPressed(keyOption1) ? keyOption1 : keyOption2;
    }

    protected boolean isKeyPressed(int keyOption1, int keyOption2){
        return Gdx.input.isKeyPressed(keyOption1) || Gdx.input.isKeyPressed(keyOption2);
    }

    protected void moveNaDirecaoEscolhida() {
        if (direcao == RIGHT || direcao == D) {
            x += VELOCIDADE;
        } else if (direcao == LEFT || direcao == A) {
            x -= VELOCIDADE;
        } else if (direcao == UP || direcao == W) {
            y += VELOCIDADE;
        } else if (direcao == DOWN || direcao == S) {
            y -= VELOCIDADE;
        }
        direcaoAtual = direcao;
    }

    public Sprite getSpriteCabeca() {
        return spriteCabeca;
    }

}
