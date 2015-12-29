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
import com.redbeardstudios.balltheblocks.view.BlankButton;
import com.redbeardstudios.balltheblocks.view.Button;

/**
 * Created by Adam on 4/6/2015.
 */
public class MoreScreen implements Screen, InputProcessor {

    final BallTheBlocks game;
    private ImageProvider imageProvider;
    private SpriteBatch batch;

    private Texture background;

    private Button storyButton;
    private Button moreGamesButton;
    private Button rateButton;
    private BlankButton emailButton;
    private BlankButton webButton;
    private BlankButton facebookButton;
    private BlankButton twitterButton;
    private BlankButton backButton;

    private OrthographicCamera camera;

    public MoreScreen(BallTheBlocks game){
        super();
        this.game = game;
    }

    @Override
    public void show() {

        imageProvider = game.getImageProvider();
        batch = game.getBatch();

        background = imageProvider.getMoreBackground();
        background.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        storyButton = new Button(imageProvider.getStoryButton());
        TextureRegion img = imageProvider.getMoreGamesButton();
        img.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        moreGamesButton = new Button(img);
        rateButton = new Button(imageProvider.getRateButton());
        emailButton = new BlankButton(856,372,1865,273);
        webButton = new BlankButton(856,219,1864,124);
        facebookButton = new BlankButton(1046,637,1248,443);
        twitterButton = new BlankButton(1472,637,1674,443);
        backButton = new BlankButton(0,1080,300,915);

        storyButton.setPos(43,314);
        rateButton.setPos(43,562);
        moreGamesButton.setPos(43,810);


        camera = new OrthographicCamera();
        camera.setToOrtho(false,imageProvider.getScreenWidth(),imageProvider.getScreenHeight());

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
        batch.draw(background, 0, 0);
        storyButton.draw(batch);
        rateButton.draw(batch);
        moreGamesButton.draw(batch);
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
        if(this.storyButton.isPressed(touchPos)){
            game.changeScreen("story");
        }
        else if(rateButton.isPressed(touchPos)){
            Gdx.net.openURI("https://play.google.com/store/apps/details?id=com.redbeardstudios.balltheblocks.android");
        }
        else if(moreGamesButton.isPressed(touchPos)){
            Gdx.net.openURI("https://play.google.com/store/apps/developer?id=Redbeard%20Studios%20LLC&hl=en");
        }
        else if(webButton.isPressed(touchPos)){
            Gdx.net.openURI("http://redbeardstudios.org");
        }
        else if(emailButton.isPressed(touchPos)){
            Gdx.net.openURI("mailto:support@redbeardstudios.org");
        }
        else if(facebookButton.isPressed(touchPos)){
            Gdx.net.openURI("fb://profile/403046519857080");
        }
        else if(twitterButton.isPressed(touchPos)){
            Gdx.net.openURI("http://twitter.com/RB__Studios");
        }
        else if(backButton.isPressed(touchPos)){
            game.changeScreen("mainMenu");
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
