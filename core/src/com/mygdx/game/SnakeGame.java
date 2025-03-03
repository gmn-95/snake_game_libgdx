package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.entities.Snake;
import com.mygdx.game.screen.GameScreen;
import com.mygdx.game.screen.MenuScreen;

public class SnakeGame extends Game {

	private boolean gameOver = false;
	GameScreen gameScreen;

	@Override
	public void create () {
		setScreen(new GameScreen(this)); // Começa na tela do jogo
	}

	@Override
	public void render () {
		super.render();
	}

	public void setGameOver(Snake snake) {
		gameOver = true;
		setScreen(new MenuScreen(this, snake));
	}

	public void restartGame() {
		gameOver = false;
		gameScreen = new GameScreen(this);
		setScreen(gameScreen);
	}

	public void exitGame(){
		Gdx.app.exit();
	}

	public GameScreen getGameScreen() {
		return gameScreen;
	}
}
