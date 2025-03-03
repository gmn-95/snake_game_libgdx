package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.SnakeGame;
import com.mygdx.game.entities.Grama;
import com.mygdx.game.info.GameInfo;

public class MenuScreen implements Screen {

    private final Texture playButtonActive;
    private final Texture playButtonInactive;
    private final Texture exitButtonActive;
    private final Texture exitButtonInactive;

    private static final String pathGameOver = "game_over2.png";
    private final Stage stage;
    private final ImageButton playButton;
    private final ImageButton exitButton;

    private boolean btInativo = true;

    public MenuScreen(SnakeGame game){

        playButtonActive = new Texture("play_button_active_2.png");
        playButtonInactive = new Texture("play_button_inactive_2.png");
        exitButtonActive = new Texture("exit_button_active_2.png");
        exitButtonInactive = new Texture("exit_button_inactive_2.png");

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        float x = (float) (GameInfo.SCREAM_WIDTH - 230) / 2;
        float yPlay = (float) (GameInfo.SCREAM_HEIGHT - 10) / 2;
        float yExit = (float) (GameInfo.SCREAM_HEIGHT - 200) / 2;

        // Criação de ImageButton com a textura ativa
        playButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(playButtonInactive)));
        exitButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(exitButtonInactive)));

        playButton.setPosition(x, yPlay);
        exitButton.setPosition(x, yExit);

        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.restartGame();
            }
        });

        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.exitGame();
            }
        });

        stage.addActor(playButton);
        stage.addActor(exitButton);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        setImagemBotao(playButton, playButtonActive, playButtonInactive);
        setImagemBotao(exitButton, exitButtonActive, exitButtonInactive);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f)); // Limita o deltaTime
        stage.draw();
    }

    protected void setImagemBotao(final ImageButton botao, Texture botaoAtivo, Texture botaoInativo){
        //posições do mouse x e y
        int inputMouseX = Gdx.input.getX();
        int inputMouseY = Gdx.input.getY();


        if (botao.getX() <= inputMouseX && inputMouseX <= botao.getX() + botao.getWidth()
                && GameInfo.SCREAM_HEIGHT - inputMouseY <= botao.getY() + botao.getHeight()
                && GameInfo.SCREAM_HEIGHT - inputMouseY >= botao.getY()
        ){
            if(btInativo){
                btInativo = false;
                botao.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(botaoAtivo));
            }
        } else {
            btInativo = true;
            botao.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(botaoInativo));
        }
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
        playButtonActive.dispose();
        playButtonInactive.dispose();
        exitButtonActive.dispose();
        exitButtonInactive.dispose();
    }
}
