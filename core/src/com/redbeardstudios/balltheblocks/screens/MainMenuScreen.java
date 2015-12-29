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
import com.redbeardstudios.balltheblocks.BallTheBlocks;
import com.redbeardstudios.balltheblocks.images.ImageProvider;
import com.redbeardstudios.balltheblocks.view.Button;

/**
 * Created by Adam on 3/3/2015.
 */
public class MainMenuScreen implements Screen, InputProcessor{

    final BallTheBlocks game;

    private Button startButton;
    private Button scoreButton;
    private Button moreButton;
    private Button rmAdsButton;
    private Button soundOff;
    private Button soundOn;

    private ImageProvider imageProvider;
    private SpriteBatch batch;

    private Texture logo;
    private TextureRegion skull;
    private TextureRegion ball;

    OrthographicCamera camera;

    public MainMenuScreen(final BallTheBlocks game){
        super();
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();

        imageProvider = game.getImageProvider();
        TextureRegion img = imageProvider.getStartButton();
        img.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        startButton = new Button(img);
        scoreButton = new Button(imageProvider.getScoreButton());
        logo = imageProvider.getLogo();
        logo.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        skull = imageProvider.getScreenSkull();
        ball = imageProvider.getMainMenuBall();
        soundOff = new Button(imageProvider.getSoundOff());
        soundOn = new Button(imageProvider.getSoundOn());
        moreButton = new Button(imageProvider.getMoreButton());
        rmAdsButton = new Button(imageProvider.getRmAdsButton());

        camera = new OrthographicCamera();
        camera.setToOrtho(false,imageProvider.getScreenWidth(),imageProvider.getScreenHeight());

        //Given in email
        startButton.setPos(255,638);
        scoreButton.setPos(973,638);
        moreButton.setPos(255,860);
        rmAdsButton.setPos(973,860);

        soundOn.setPos(imageProvider.getScreenWidth() - soundOn.getWidth(),1080);
        soundOff.setPos(imageProvider.getScreenWidth() - soundOn.getWidth(),1080);

        Gdx.input.setInputProcessor(this);
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void render(float delta) {

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(logo, 0,0);
        batch.draw(skull,0,0);
        batch.draw(ball,1575,516);
        startButton.draw(batch);
        scoreButton.draw(batch);
        moreButton.draw(batch);
        rmAdsButton.draw(batch);
        if(game.getSoundManager().isSoundOn()){
            soundOn.draw(batch);
        }
        else {
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
    	game.getScreenRotator().allowRotation();
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
            Gdx.app.exit();
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
        if(this.startButton.isPressed(touchPos)) {
            game.changeScreen("game");
            return true;
        }
        else if(this.scoreButton.isPressed(touchPos)){
            game.getaLeaderboard().showScores();
            game.getiLeaderboard().showScores();
        }
        else if(this.soundOn.isPressed(touchPos)){
            if(game.getSoundManager().isSoundOn())
                game.getSoundManager().setSoundOn(false);
            else
                game.getSoundManager().setSoundOn(true);
        }
        else if(this.rmAdsButton.isPressed(touchPos)){
            game.changeScreen("purchase");
        }
        else if(this.moreButton.isPressed(touchPos)){
            game.changeScreen("more");
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
