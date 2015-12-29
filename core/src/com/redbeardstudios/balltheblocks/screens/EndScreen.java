package com.redbeardstudios.balltheblocks.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.redbeardstudios.balltheblocks.BallTheBlocks;
import com.redbeardstudios.balltheblocks.images.ImageProvider;
import com.redbeardstudios.balltheblocks.view.Button;

/**
 * Created by Adam on 3/3/2015.
 */
public class EndScreen implements Screen, InputProcessor{

    final BallTheBlocks game;
    private long score =0;

    private SpriteBatch batch;
    private ImageProvider imageProvider;

    private Button restartButton;
    private Button menuButton;
    private Button soundOn;
    private Button soundOff;
    private Button submitButton;

    private Texture gameOver;

    private int buttonX = 626;
    private int scoreX;
    private int scoreY = 1080- 260;

    OrthographicCamera camera;

    public EndScreen(final BallTheBlocks game){
        super();
        this.game = game;
        this.score = game.currScore;
    }

    @Override
    public void show() {
        batch = game.getBatch();
        imageProvider = game.getImageProvider();

        gameOver = imageProvider.getGameOver();
        gameOver.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        soundOff = new Button(imageProvider.getSoundOff());
        soundOn = new Button(imageProvider.getSoundOn());
        menuButton = new Button(imageProvider.getMenuButton());
        restartButton = new Button(imageProvider.getRestartButton());
        submitButton = new Button(imageProvider.getSubmitButton());

        camera = new OrthographicCamera();
        camera.setToOrtho(false,imageProvider.getScreenWidth(),imageProvider.getScreenHeight());

        if(score < 10){
            scoreX = buttonX + 12;
        }
        else if(score < 100){
            scoreX = buttonX;
        }
        else{
            scoreX = buttonX -15;
        }

        menuButton.setPos(buttonX,587);
        restartButton.setPos(buttonX,806);
        submitButton.setPos(buttonX,1025);
        soundOn.setPos(imageProvider.getScreenWidth() - soundOn.getWidth(),imageProvider.getScreenHeight());
        soundOff.setPos(imageProvider.getScreenWidth() - soundOn.getWidth(),imageProvider.getScreenHeight());

        Gdx.input.setInputProcessor(this);
        Gdx.input.setCatchBackKey(true);

        score = game.currScore;

        game.getiLeaderboard().submitScore(score);
        game.getScreenRotator().allowRotation();

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(gameOver,0,0);
        restartButton.draw(batch);
        menuButton.draw(batch);
        submitButton.draw(batch);
        game.scoreFont.draw(batch,"SCORE: " + score,scoreX,scoreY);
        if(game.getSoundManager().isSoundOn()){
            soundOn.draw(batch);
        }
        else{
            soundOff.draw(batch);
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
        game.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.BACK){
            if(game.getGameState().showAd()) {
                if (game.getaAdsController().isConnected()) {
                    game.getaAdsController().showInterstitialAd(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                }
                game.getiAdsController().showAd();
            }
            game.changeScreen("mainMenu");
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 touchPos = new Vector3();
        touchPos.set(screenX,screenY,0);
        camera.unproject(touchPos);
        if(this.restartButton.isPressed(touchPos)) {
            game.getSoundManager().playHitBlockSound();
            if(game.getGameState().showAd()) {
                if (game.getaAdsController().isConnected()) {
                    game.getaAdsController().showInterstitialAd(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                }
                game.getiAdsController().showAd();
            }
            game.changeScreen("game");
        }
        else if(this.menuButton.isPressed(touchPos)){
            game.getSoundManager().playHitBlockSound();
            if(game.getGameState().showAd()) {
                if (game.getaAdsController().isConnected()) {
                    game.getaAdsController().showInterstitialAd(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                }
                game.getiAdsController().showAd();
            }
            game.changeScreen("mainMenu");
        }
        else if(this.submitButton.isPressed(touchPos)){
            game.getSoundManager().playHitBlockSound();
            game.getaLeaderboard().submitScore(score);
            game.getiLeaderboard().showScores();
        }
        else if(this.soundOn.isPressed(touchPos)){
            if(game.getSoundManager().isSoundOn())
                game.getSoundManager().setSoundOn(false);
            else
                game.getSoundManager().setSoundOn(true);
        }

        return true;

    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
