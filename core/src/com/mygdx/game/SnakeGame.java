package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.screen.GameScreen;
import com.mygdx.game.screen.MenuScreen;

public class SnakeGame extends Game {

	private boolean gameOver = false;

	@Override
	public void create () {
		setScreen(new GameScreen(this)); // Começa na tela do jogo
	}

	@Override
	public void render () {
		super.render();
	}

	public void setGameOver() {
		gameOver = true;
		setScreen(new MenuScreen(this));
	}

	public void restartGame() {
		gameOver = false;
		setScreen(new GameScreen(this));
	}

}
