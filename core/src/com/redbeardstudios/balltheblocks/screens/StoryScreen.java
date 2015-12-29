package com.redbeardstudios.balltheblocks.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.redbeardstudios.balltheblocks.BallTheBlocks;
import com.redbeardstudios.balltheblocks.images.ImageProvider;
import com.redbeardstudios.balltheblocks.view.Button;

/**
 * Created by Adam on 4/2/2015.
 */
public class StoryScreen implements Screen, InputProcessor {

    private BallTheBlocks game;
    private ImageProvider imageProvider;
    private Texture image;
    private int screenNum = 1;

    private Texture tImage1;
    private Image textImage1;
    private Texture tImage2;
    private Image textImage2;
    private Texture tImage3;
    private Image textImage3;
    private Stage stage;
    private boolean textDone = false;
    private boolean canCont = false;

    SpriteBatch batch;
    OrthographicCamera camera;

    public StoryScreen(BallTheBlocks game){
        this.game = game;
    }

    @Override
    public void show() {
        imageProvider = game.getImageProvider();
        batch = game.getBatch();

        textDone = false;
        canCont = false;
        screenNum = 1;

        camera = new OrthographicCamera();
        camera.setToOrtho(false,imageProvider.getScreenWidth(),imageProvider.getScreenHeight());

        image = imageProvider.getScene2();
        image.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        tImage1 = imageProvider.getScene1Words1();
        tImage1.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        tImage2 = imageProvider.getScene1Words2();
        tImage2.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        tImage3 = imageProvider.getScene1Words3();
        tImage3.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        textImage2 = new Image(tImage2);
        textImage1 = new Image(tImage1);
        textImage3 = new Image(tImage3);
        textImage1.setPosition(210,540);
        textImage2.setPosition(210,240);
        textImage2.getColor().a=0;
        textImage3.setPosition(920,0);
        textImage3.getColor().a=0;

        stage = new Stage(new FitViewport(imageProvider.getScreenWidth(),imageProvider.getScreenHeight(),camera));
        stage.addActor(textImage1);
        textImage1.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(1f), Actions.delay(1f), Actions.run(new Runnable() {
            @Override
            public void run() {
                stage.addActor(textImage2);
            }
        })));
        textImage2.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(1f), Actions.delay(1f) ,Actions.run(new Runnable() {
            @Override
            public void run() {
                stage.addActor(textImage3);
            }
        })));
        textImage3.addAction(Actions.sequence(Actions.alpha(1), Actions.run(new Runnable() {
            @Override
            public void run() {
                canCont = true;
            }
        })));

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        if(!textDone) {
            stage.act();
            stage.draw();
        }
        batch.setProjectionMatrix(camera.combined);
        if(textDone) {
            batch.begin();
            batch.draw(image, 0, 0);
            batch.end();
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

    }

    @Override
    public void dispose() {
        game.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {

        return false;
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
        if(screenNum == 1 && canCont){
            textDone = true;
            screenNum++;
        }
        else if(screenNum == 2){
            image = imageProvider.getScene3();
            image.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            screenNum++;
        }
        else if(screenNum == 3){
            image = imageProvider.getScene4();
            image.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            screenNum++;
        }
        else if(screenNum == 4){
            game.getGameState().setStory(false);
            game.setScreen(new MainMenuScreen(game));
        }
        return false;
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
