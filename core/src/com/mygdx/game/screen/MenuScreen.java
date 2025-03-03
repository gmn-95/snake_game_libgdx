package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.SnakeGame;

public class MenuScreen implements Screen {

    private static final String pathGameOver = "game_over2.png";
    private Texture gameOverTexture;
    private SnakeGame game;
    private Stage stage;
    private SpriteBatch batch;
    private BitmapFont font;
    private Skin skin;

    private static final int SCREEN_WIDTH = 500;
    private static final int SCREEN_HEIGHT = 500;


    public MenuScreen(SnakeGame game){
        this.game = game;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(2); // Deixa a fonte maior para ser visível

        // Criando um estilo simples para o botão
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = font;
        buttonStyle.fontColor = Color.WHITE;

        TextButton restartButton = new TextButton("Reiniciar", buttonStyle);
        restartButton.setSize(150, 50);
        restartButton.setPosition((SCREEN_WIDTH - restartButton.getWidth()) / 2f, 150); // Centralizado

        restartButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.restartGame();
            }
        });

        stage.addActor(restartButton);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        // Texto para testar se algo aparece na tela
        font.setColor(Color.WHITE);
        font.draw(batch, "GAME OVER", SCREEN_WIDTH / 2f - 60, SCREEN_HEIGHT / 2f + 50);

        batch.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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

    public void dispose(){
        stage.dispose();
        batch.dispose();
        font.dispose();
        skin.dispose();
    }
}
