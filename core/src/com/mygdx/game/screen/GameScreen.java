package com.mygdx.game.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.SnakeGame;
import com.mygdx.game.background.Grama;
import com.mygdx.game.colisao.*;
import com.mygdx.game.food.Comida;
import com.mygdx.game.food.Maca;
import com.mygdx.game.food.Rato;
import com.mygdx.game.snake.Snake;

public class GameScreen implements Screen {

    private final SnakeGame game;
    private final SpriteBatch batch;
    private final Grama grama;
    private final Snake snake;
    private final Comida comida;
    private final Colisao colisao;

    public GameScreen(SnakeGame game){
        this.game = game;
        batch = new SpriteBatch();
        grama = new Grama(batch);
        snake = new Snake(batch);

        comida = new Rato(batch);
//        comida = new Maca(batch);
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
            game.setGameOver(snake);
        } else {
            snake.move();
            colisao.checaColisaoComCorpo();
            colisao.checaColisaoComComida();
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
        snake.disope();
        comida.disope();
        grama.disope();
        comida.disope();
    }
}
