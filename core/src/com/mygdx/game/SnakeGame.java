package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.entities.Colisao;
import com.mygdx.game.entities.Rato;
import com.mygdx.game.entities.Grama;
import com.mygdx.game.entities.Snake;

public class SnakeGame extends ApplicationAdapter {

	private SpriteBatch batch;
	private Grama grama;
	private Snake snake;
	private Rato comida;
	private Colisao colisao;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		grama = new Grama(batch);
		snake = new Snake(batch);
		comida = new Rato(batch);
		colisao = new Colisao(snake, comida);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 0);
		batch.begin();

		grama.init();
		snake.desenhaCorpo();
		snake.desenhaCabeca();
		comida.desenhaComida();
		snake.move();
		colisao.checaColisaoComRato();
		colisao.checaColisaoComParede();

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		grama.disope();
		snake.disope();
		comida.disope();
	}
}
