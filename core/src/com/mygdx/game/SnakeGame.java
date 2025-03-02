package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.entities.*;

public class SnakeGame extends ApplicationAdapter {

	private SpriteBatch batch;
	private Grama grama;
	private Snake snake;
	private Rato comida;
	private Colisao colisao;
	private GameOverScreen gameOverScreen;

	@Override
	public void create () {
		batch = new SpriteBatch();
		grama = new Grama(batch);
		snake = new Snake(batch);
		comida = new Rato(batch);
		colisao = new Colisao(snake, comida);
		gameOverScreen = new GameOverScreen(batch);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 0);
		batch.begin();

		grama.init();
		snake.desenhaCorpo();
		comida.desenhaComida();

		if(colisao.isColidiu()){
			this.gameOverScreen.gameOverScreen();
			gameOverScreen.setIsGameOver(true);
		} else {
			snake.move();
			colisao.checaColisaoComCorpo();
			colisao.checaColisaoComRato();
			colisao.checaColisaoComParede();
		}

		restartGame();
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		grama.disope();
		snake.disope();
		comida.disope();
		gameOverScreen.dispose();
	}

	private void restartGame(){
		if(Gdx.input.isKeyPressed(Input.Keys.ENTER) && gameOverScreen.isGameOver()){
			snake = new Snake(batch);
			comida = new Rato(batch);
			colisao = new Colisao(snake, comida);
			gameOverScreen.setIsGameOver(false);
		}
	}

}
