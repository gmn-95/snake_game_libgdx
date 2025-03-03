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
    private static final String PATH_TEX_CABECA = "snakehead.png";
    private static final String PATH_TEX_CABECA_COMENDO = "snakehead3.png";
    private static final String PATH_TEX_CORPO = "snakebody2.png";
    private static final int TAMANHO_CORPO = 20; // Tamanho de cada parte do corpo
    private static final int VELOCIDADE = 4; //
    private static final int TAMANHO_CORPO_INICIAL = 1; //quantidade de partes iniciais
    private static final int VELOCIDADE_ATUALIZACAO_RENDER_CORPO = 4;

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

    //pequeno inputlag
    private final float delay = 0.08F; // 200 milissegundos de delay
    private float tempoPassado = 0;

    private boolean posicaoInicial;

    public Snake(SpriteBatch spriteBatch) {
        this.spriteBatch = spriteBatch;
        partesCorpo = new LinkedList<>();
        texturaCabeca = new Texture(PATH_TEX_CABECA);
        texturaCorpo = new Texture(PATH_TEX_CORPO);
        spriteCabeca = new Sprite(texturaCabeca);
        spriteCorpo = new Sprite(texturaCorpo);
        posicaoInicial = true;
    }

    public void desenhaCorpo() {
        if (partesCorpo.isEmpty()) {
            inicializaCorpo();
        }

        // Desenha o restante do corpo
        for (int i = 1; i < partesCorpo.size(); i++) {  // Começa do índice 1, pois a cabeça já foi desenhada
            ParteCorpo parteCorpo = partesCorpo.get(i);
            spriteCorpo.setPosition(parteCorpo.getX(), parteCorpo.getY());
            spriteCorpo.draw(spriteBatch);
        }

        desenhaCabeca();
    }

    protected void inicializaCorpo() {
        // Inicializa a cabeça na primeira posição
        ParteCorpo cabeca = new ParteCorpo(x, y, spriteCabeca, true);
        partesCorpo.addFirst(cabeca);

        // Inicializa o restante do corpo
        for (int i = 1; i <= TAMANHO_CORPO_INICIAL; i++) {
            ParteCorpo corpo = new ParteCorpo(x - (TAMANHO_CORPO * i), y, spriteCorpo, false);
            partesCorpo.add(corpo);
        }
    }

    public void disope() {
        texturaCabeca.dispose();
        texturaCorpo.dispose();
    }

    public void desenhaCabeca() {
        spriteCabeca.setPosition(x, y);
        spriteCabeca.draw(spriteBatch);
    }

    public void adicionaCorpoTeste(){
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            ParteCorpo corpo = new ParteCorpo(x - (TAMANHO_CORPO * partesCorpo.getFirst().getX()), y, new Sprite(texturaCorpo), false);
            partesCorpo.add(corpo);
        }
    }

    public void move() {
        int xAnterior = x;
        int yAnterior = y;

        defineDirecaoERotacao();
        moveNaDirecaoEscolhida();
        adicionaCorpoTeste();
        if (isAdiciona){
            adicionaParteCorpo();
            isAdiciona = false;
        }

        if (direcao == 0 && !partesCorpo.isEmpty()) {
            reposicionarParteCorpo(xAnterior, yAnterior);
        } else {
            posicaoInicial = false;
        }

        spriteCabeca.setPosition(x, y);
        spriteCabeca.setRotation(rotacao);
        atualizaMovimentacaoDaCobra(xAnterior, yAnterior);
    }

    private void reposicionarParteCorpo(int xAnterior, int yAnterior) {
        if (partesCorpo.isEmpty()) return; // Evita erro caso não haja corpo

        int xAtual = xAnterior;
        int yAtual = yAnterior;

        // Percorre todas as partes do corpo e faz o "efeito de arrastar"
        for (ParteCorpo parte : partesCorpo) {
            int tempX = parte.getX();
            int tempY = parte.getY();

            parte.setX(xAtual);
            parte.setY(yAtual);
            parte.getSprite().setPosition(xAtual, yAtual);

            xAtual = tempX;
            yAtual = tempY;
        }
    }

    protected void adicionaParteCorpo() {
        // Pega a última parte do corpo
        ParteCorpo ultimaParte = partesCorpo.getLast();

        // Calcula a nova posição da parte do corpo com base na última parte
        int novaPosicaoX = ultimaParte.getX();
        int novaPosicaoY = ultimaParte.getY();

        // Cria uma nova parte do corpo na posição calculada
        ParteCorpo corpo = new ParteCorpo(novaPosicaoX, novaPosicaoY, new Sprite(texturaCorpo), false);

        // Adiciona a nova parte do corpo à lista
        partesCorpo.add(corpo);
    }

    public void isAdicionaParteCorpo(){
        isAdiciona = true;
    }

    protected void atualizaMovimentacaoDaCobra(int xAnterior, int yAnterior){
          if (Gdx.graphics.getFrameId() % VELOCIDADE_ATUALIZACAO_RENDER_CORPO == 0 && !partesCorpo.isEmpty()){// Ajusta a frequência de movimento das partes do corpo
              reposicionarParteCorpo(xAnterior, yAnterior);
          }
    }

    public void animacaoComer(Rato comida){

        //Calcula a distancia da cobra e a comida
        double distancia = Math.hypot(
                comida.getSpriteRato().getX() - spriteCabeca.getX(),
                comida.getSpriteRato().getY() - spriteCabeca.getY()
        );

        if (distancia <= 50.0) {
            spriteCabeca.setTexture(new Texture(PATH_TEX_CABECA_COMENDO));
        }
    }

    /**
     * reseta imagem cabeça cobra
     * */
    public void resetarTexturaCabeca(){
        if (Gdx.graphics.getFrameId() % 15 == 0){// Ajusta a frequência de movimento das partes do corpo
            spriteCabeca.setTexture(new Texture(PATH_TEX_CABECA));
        }
    }

    protected void defineDirecaoERotacao() {
        // Incrementa o tempo passado desde a última atualização
        tempoPassado += Gdx.graphics.getDeltaTime();

        // Verifica se o delay foi alcançado
        if (tempoPassado < delay) return;

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

    protected boolean naoEhDirecaoAtual(int keyOption1, int keyOption2){
        return this.direcaoAtual != keyOption1 && this.direcaoAtual != keyOption2;
    }

    protected void setDirecao(int keyOption1, int keyOption2){
        direcao = Gdx.input.isKeyPressed(keyOption1) ? keyOption1 : keyOption2;
    }

    private boolean isKeyPressed(int... keys) {
        for (int key : keys) {
            if (Gdx.input.isKeyPressed(key)) return true;
        }
        return false;
    }

    protected void moveNaDirecaoEscolhida() {

        switch (direcao){
            case RIGHT:
            case D:
                x += VELOCIDADE;
                break;
            case LEFT:
            case A:
                x -= VELOCIDADE;
                break;
            case UP:
            case W:
                y += VELOCIDADE;
                break;
            case DOWN:
            case S:
                y -= VELOCIDADE;
                break;
        }

        direcaoAtual = direcao;
    }

    public Sprite getSpriteCabeca() {
        return spriteCabeca;
    }

    public LinkedList<ParteCorpo> getPartesCorpo() {
        return partesCorpo;
    }

    public boolean isPosicaoInicial() {
        return posicaoInicial;
    }
}