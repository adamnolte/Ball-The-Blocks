package com.redbeardstudios.balltheblocks.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.redbeardstudios.balltheblocks.BallTheBlocks;
import com.redbeardstudios.balltheblocks.images.ImageProvider;
import com.redbeardstudios.balltheblocks.state.PurchaseHandler;
import com.redbeardstudios.balltheblocks.view.Button;

import javax.xml.soap.Text;

/**
 * Created by Adam on 4/6/2015.
 */
public class PurchaseScreen implements Screen, InputProcessor {

    final BallTheBlocks game;
    private PurchaseHandler purchaseHandler;
    private ImageProvider imageProvider;
    private SpriteBatch batch;
    private Texture logo;
    private Texture adsRemoved;

    private Button purchaseButton;
    private Button restoreButton;
    private Button backButton;

    private OrthographicCamera camera;

    public PurchaseScreen(BallTheBlocks game){
        super();
        this.game = game;
    }

    @Override
    public void show() {

        imageProvider = game.getImageProvider();
        batch = game.getBatch();
        purchaseHandler = game.getPurchaseHandler();

        camera = new OrthographicCamera();
        camera.setToOrtho(false,imageProvider.getScreenWidth(),imageProvider.getScreenHeight());

        purchaseButton = new Button(imageProvider.getPurchaseButton());
        restoreButton = new Button(imageProvider.getRestoreButton());
        backButton = new Button(imageProvider.getBackButton());
        logo = imageProvider.getLogo();
        logo.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        adsRemoved = imageProvider.getAdsRemoved();
        adsRemoved.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        purchaseButton.setPos(140,960);
        restoreButton.setPos(940,960);
        backButton.setPos(0,1080);
        Gdx.input.setInputProcessor(this);
        Gdx.input.setCatchBackKey(true);

    }

    @Override
    public void render(float delta) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(game.getGameState().showAd()){
        	batch.begin();
        	batch.draw(logo, 0, 0);
        	backButton.draw(batch);
        	purchaseButton.draw(batch);
        	restoreButton.draw(batch);
        	batch.end();
        }
        else{
        	batch.begin();
        	batch.draw(logo, 0, 0);
        	batch.draw(adsRemoved,210 ,390);
        	backButton.draw(batch);
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
        if(this.purchaseButton.isPressed(touchPos)){
            if(game.getGameState().showAd()){
                purchaseHandler.purchaseRemoveAds();
            }
        }
        else if(this.restoreButton.isPressed(touchPos)){
            if(game.getGameState().showAd()){
                purchaseHandler.restorePurchases();
            }
        }
        else if(this.backButton.isPressed(touchPos)){
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
