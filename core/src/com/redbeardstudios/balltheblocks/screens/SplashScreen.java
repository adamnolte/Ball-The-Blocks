package com.redbeardstudios.balltheblocks.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.redbeardstudios.balltheblocks.BallTheBlocks;
import com.redbeardstudios.balltheblocks.images.ImageProvider;
import com.redbeardstudios.balltheblocks.sound.SoundManager;

/**
 * Created by Adam on 3/30/2015.
 */
public class SplashScreen implements Screen{
    private Stage stage;
    SpriteBatch batch;
    private Timer timer;
    private int time = 2;
    private boolean splashDone = false;
    private Image splashImage;

    private BallTheBlocks game;
    private ImageProvider imageProvider;
    private SoundManager soundManager;
    private OrthographicCamera camera;

    public SplashScreen(SpriteBatch batch, BallTheBlocks game){
        super();
        this.batch = batch;
        this.game = game;
        timer = new Timer();
    }

    @Override
    public void show() {
        imageProvider = game.getImageProvider();
        soundManager = game.getSoundManager();
        imageProvider.load();
        soundManager.load();

        camera = new OrthographicCamera();
        camera.setToOrtho(false,imageProvider.getScreenWidth(),imageProvider.getScreenHeight());

        Texture img = new Texture(Gdx.files.internal("RBLogo.png"));
        img.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        splashImage = new Image(img);

        stage = new Stage(new FitViewport(imageProvider.getScreenWidth(),imageProvider.getScreenHeight(),camera));
        stage.addActor(splashImage);
        splashImage.addAction(Actions.sequence(Actions.alpha(0),Actions.fadeIn(1f),Actions.delay(2f),Actions.run(new Runnable() {
            @Override
            public void run() {
                splashDone = true;
            }
        })));
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        stage.act();
        stage.draw();
        if(soundManager.update() && imageProvider.update() && splashDone){
            stage.clear();
            game.getiLeaderboard().initialize();
            if(game.getGameState().showAd()) {
                game.getiAdsController().initializeAds();
            }
            if(game.getGameState().showStory()) {
                game.changeScreen("story");
            }
            else{
                game.changeScreen("mainMenu");
            }
        }
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
        dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
