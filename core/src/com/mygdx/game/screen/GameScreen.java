package com.mygdx.game.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.SnakeGame;
import com.mygdx.game.entities.Colisao;
import com.mygdx.game.entities.Grama;
import com.mygdx.game.entities.Rato;
import com.mygdx.game.entities.Snake;

public class GameScreen implements Screen {

    private final SnakeGame game;
    private final SpriteBatch batch;
    private final Grama grama;
    private final Snake snake;
    private final Rato comida;
    private final Colisao colisao;

    public GameScreen(SnakeGame game){
        this.game = game;
        batch = new SpriteBatch();
        grama = new Grama(batch);
        snake = new Snake(batch);
        comida = new Rato(batch);
        colisao = new Colisao(snake, comida);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);
        batch.begin();

        grama.init();
        snake.desenhaCorpo();
        comida.desenhaComida();

        // se game over, mostra menu
        if (colisao.isColidiu()) {
            game.setGameOver();
        } else {
            snake.move();
            colisao.checaColisaoComCorpo();
            colisao.checaColisaoComRato();
            colisao.checaColisaoComParede();
        }

        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
