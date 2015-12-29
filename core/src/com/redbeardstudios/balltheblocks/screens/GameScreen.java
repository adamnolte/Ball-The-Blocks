package com.redbeardstudios.balltheblocks.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.redbeardstudios.balltheblocks.BallTheBlocks;
import com.redbeardstudios.balltheblocks.images.ImageProvider;
import com.redbeardstudios.balltheblocks.sound.SoundManager;
import com.redbeardstudios.balltheblocks.view.Ball;
import com.redbeardstudios.balltheblocks.view.Block;
import com.redbeardstudios.balltheblocks.view.Button;
import com.redbeardstudios.balltheblocks.view.Skull;

import java.util.Random;

/**
 * Created by Adam on 3/3/2015.
 */
public class GameScreen implements Screen, InputProcessor{
    final BallTheBlocks game;
    private int score = 0;

    private TextureRegion timerImage;
    private float xClock;
    private float yClock;

    private int time = 45;
    private Timer timer;

    private int blockNum = 1;
    private long hitBlockTime = 0;
    private long breakTime = 50000000;

    private Button soundOn;
    private Button soundOff;
    private int soundX;

    private Texture gameInstructions;

    private SpriteBatch batch;
    private int screenWidth;
    ImageProvider imageProvider;
    SoundManager soundManager;
    Random rand;

    private Ball ball;
    private Block block;
    private TextureRegion bBlockImage;
    private TextureRegion dbBlockImage;
    private TextureRegion yBlockImage;
    private TextureRegion wBlockImage;
    private Skull skull;

    boolean gameStarted = false;

    OrthographicCamera camera;


    public GameScreen(final BallTheBlocks game){
        super();
        this.game = game;
    }

    private void getImages(){
        ball = new Ball(imageProvider.getScreenWidth(),imageProvider.getScreenHeight(),imageProvider.getBall());
        bBlockImage = imageProvider.getbBlock1();
        dbBlockImage = imageProvider.getdbBlock1();
        yBlockImage = imageProvider.getyBlock1();
        wBlockImage = imageProvider.getwBlock1();
        soundOff = new Button(imageProvider.getSoundOff());
        soundOn = new Button(imageProvider.getSoundOn());
        soundX = imageProvider.getScreenWidth() - soundOn.getWidth();
        soundOn.setPos(soundX,1080);
        soundOff.setPos(soundX,1080);
        skull = new Skull(imageProvider.getSkull() ,imageProvider.getScreenWidth(),imageProvider.getScreenHeight());
        gameInstructions = imageProvider.getGameInstructions();
        gameInstructions.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        xClock = imageProvider.getScreenWidth() - timerImage.getRegionWidth();
        yClock = imageProvider.getScreenHeight() - timerImage.getRegionHeight();

    }

    private void processInput(){
        float delta = Gdx.graphics.getDeltaTime();
        if(Gdx.input.getRotation() == 90) {
            ball.move(delta, Gdx.input.getAccelerometerY(), Gdx.input.getAccelerometerX() * -1);
        }
        else{
            ball.move(delta, Gdx.input.getAccelerometerY() * -1, Gdx.input.getAccelerometerX());
        }

    }

    private void endGame(){
        timer.instance().stop();
        timer.instance().clear();
        soundManager.stopBackgroundMusic();
        soundManager.playGameOverSound();
        game.currScore = score;
        game.changeScreen("end");
    }

    private void spawnBlock(){
        int ranNum = rand.nextInt(100);
        Rectangle randRect = new Rectangle();
        randRect.width = bBlockImage.getRegionWidth();
        randRect.height = bBlockImage.getRegionHeight();
        int xPadding = imageProvider.getScreenWidth() - (int)randRect.width;
        int yPadding = imageProvider.getScreenHeight() - (int)randRect.height;
        randRect.x = rand.nextInt(xPadding);
        randRect.y = rand.nextInt(yPadding);
        while(randRect.overlaps(ball.getPosition()) || randRect.overlaps(skull.getPosition())){
            randRect.x = rand.nextInt(xPadding);
            randRect.y = rand.nextInt(yPadding);
        }

        if(ranNum <= 25){
            block = new Block(bBlockImage,randRect.x,randRect.y,"blue");
        }
        else if(ranNum <= 50){
            block = new Block(dbBlockImage,randRect.x,randRect.y,"dark_blue");
        }
        else if(ranNum <= 75){
            block = new Block(yBlockImage,randRect.x,randRect.y,"yellow");
        }
        else{
            block = new Block(wBlockImage,randRect.x,randRect.y,"white");
        }
    }

    private void updateTimer(){
        if(time > 40){
            timerImage = imageProvider.getCircle9();
        }
        else if(time > 35){
            timerImage = imageProvider.getCircle8();
        }
        else if(time > 30){
            timerImage = imageProvider.getCircle7();
        }
        else if(time > 25){
            timerImage = imageProvider.getCircle6();
        }
        else if(time > 20){
            timerImage = imageProvider.getCircle5();
        }
        else if(time > 15){
            timerImage = imageProvider.getCircle4();
        }
        else if(time > 10){
            timerImage = imageProvider.getCircle3();
        }
        else if(time > 5){
            timerImage = imageProvider.getCircle2();
        }
        else{
            timerImage = imageProvider.getCircle1();
        }
    }

    private void updateBlock(){
        if(blockNum == 1) return;

        else if(block.getColor().equalsIgnoreCase("blue")){
            if(blockNum == 2) {
                block.setImage(imageProvider.getbBlock2());
                hitBlockTime = TimeUtils.nanoTime();
                ++blockNum;
            }
            else if(blockNum == 3 && TimeUtils.nanoTime() - hitBlockTime > breakTime) {
                block.setImage(imageProvider.getbBlock3());
                hitBlockTime = TimeUtils.nanoTime();
                ++blockNum;
            }
            else if(blockNum == 4 && TimeUtils.nanoTime() - hitBlockTime > breakTime) {
                block.setImage(imageProvider.getbBlock4());
                hitBlockTime = TimeUtils.nanoTime();
                ++blockNum;
            }
            else if(blockNum == 5 && TimeUtils.nanoTime() - hitBlockTime > breakTime) {
                block.setImage(imageProvider.getbBlock5());
                hitBlockTime = TimeUtils.nanoTime();
                ++blockNum;
            }
            else if(blockNum == 6 && TimeUtils.nanoTime() - hitBlockTime > breakTime) {
                block.setImage(imageProvider.getbBlock6());
                hitBlockTime = TimeUtils.nanoTime();
            }
        }
        else if(block.getColor().equalsIgnoreCase("dark_blue")){
            if(blockNum == 2) {
                block.setImage(imageProvider.getdbBlock2());
                hitBlockTime = TimeUtils.nanoTime();
                ++blockNum;
            }
            else if(blockNum == 3 && TimeUtils.nanoTime() - hitBlockTime > breakTime) {
                block.setImage(imageProvider.getdbBlock3());
                hitBlockTime = TimeUtils.nanoTime();
                ++blockNum;
            }
            else if(blockNum == 4 && TimeUtils.nanoTime() - hitBlockTime > breakTime) {
                block.setImage(imageProvider.getdbBlock4());
                hitBlockTime = TimeUtils.nanoTime();
                ++blockNum;
            }
            else if(blockNum == 5 && TimeUtils.nanoTime() - hitBlockTime > breakTime) {
                block.setImage(imageProvider.getdbBlock5());
                hitBlockTime = TimeUtils.nanoTime();
                ++blockNum;
            }
            else if(blockNum == 6 && TimeUtils.nanoTime() - hitBlockTime > breakTime) {
                block.setImage(imageProvider.getdbBlock6());
                hitBlockTime = TimeUtils.nanoTime();
            }

        }
        else if(block.getColor().equalsIgnoreCase("yellow")){
            if(blockNum == 2) {
                block.setImage(imageProvider.getyBlock2());
                hitBlockTime = TimeUtils.nanoTime();
                ++blockNum;
            }
            else if(blockNum == 3 && TimeUtils.nanoTime() - hitBlockTime > breakTime) {
                block.setImage(imageProvider.getyBlock3());
                hitBlockTime = TimeUtils.nanoTime();
                ++blockNum;
            }
            else if(blockNum == 4 && TimeUtils.nanoTime() - hitBlockTime > breakTime) {
                block.setImage(imageProvider.getyBlock4());
                hitBlockTime = TimeUtils.nanoTime();
                ++blockNum;
            }
            else if(blockNum == 5 && TimeUtils.nanoTime() - hitBlockTime > breakTime) {
                block.setImage(imageProvider.getyBlock5());
                hitBlockTime = TimeUtils.nanoTime();
                ++blockNum;
            }
            else if(blockNum == 6 && TimeUtils.nanoTime() - hitBlockTime > breakTime) {
                block.setImage(imageProvider.getyBlock6());
                hitBlockTime = TimeUtils.nanoTime();
            }
        }
        else if(block.getColor().equalsIgnoreCase("white")) {
            if (blockNum == 2) {
                block.setImage(imageProvider.getwBlock2());
                hitBlockTime = TimeUtils.nanoTime();
                ++blockNum;
            } else if (blockNum == 3 && TimeUtils.nanoTime() - hitBlockTime > breakTime) {
                block.setImage(imageProvider.getwBlock3());
                hitBlockTime = TimeUtils.nanoTime();
                ++blockNum;
            } else if (blockNum == 4 && TimeUtils.nanoTime() - hitBlockTime > breakTime) {
                block.setImage(imageProvider.getwBlock4());
                hitBlockTime = TimeUtils.nanoTime();
                ++blockNum;
            } else if (blockNum == 5 && TimeUtils.nanoTime() - hitBlockTime > breakTime) {
                block.setImage(imageProvider.getwBlock5());
                hitBlockTime = TimeUtils.nanoTime();
                ++blockNum;
            } else if (blockNum == 6 && TimeUtils.nanoTime() - hitBlockTime > breakTime) {
                block.setImage(imageProvider.getwBlock6());
                hitBlockTime = TimeUtils.nanoTime();
            }
        }
    }

    @Override
    public void show() {
        System.out.println("Show in Game Screen");
        rand = new Random();

        soundManager = game.getSoundManager();

        //Get the SpriteBatch for game
        batch = game.getBatch();

        imageProvider = game.getImageProvider();
        screenWidth = imageProvider.getScreenWidth();
        updateTimer();
        getImages();

        camera = new OrthographicCamera();
        camera.setToOrtho(false,screenWidth,imageProvider.getScreenHeight());

        spawnBlock();

        timer = new Timer();
        System.out.println("New Timer");
        time = 45;

        timer.schedule(new Timer.Task(){
            @Override
            public void run(){
                --time;
                System.out.println("Time is " + time);
                if(time == 0) {
                    endGame();
                    System.out.println("Timer Up");
                }
                if(time%5 == 0){
                    skull.addSpeed();
                    System.out.println("Adding Speed to Skull");
                }
            }
        },1,1,time);
        timer.instance().stop();
        updateTimer();

        Gdx.input.setInputProcessor(this);
        Gdx.input.setCatchBackKey(true);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        if(gameStarted) {
            block.draw(batch);
            ball.draw(batch);
            skull.draw(batch);
        }
        game.scoreFont.draw(batch,"" + score,3,1080);
        batch.draw(timerImage,xClock,yClock);
        if(!gameStarted) {
            if (soundManager.isSoundOn()) {
                soundOn.draw(batch);
            } else {
                soundOff.draw(batch);
            }
            batch.draw(gameInstructions,0,0);
        }
        batch.end();

        if(gameStarted) {
            processInput();
            updateTimer();
            if(blockNum == 6 && TimeUtils.nanoTime() - hitBlockTime > breakTime){
                spawnBlock();
                blockNum = 1;
            }
            updateBlock();

            if (block.isOverlapping(ball.getPosition()) && blockNum == 1) {
                ++score;
                ++blockNum;
                soundManager.playHitBlockSound();
            }
            else if(skull.isOverlapping(ball.getPosition())){
                endGame();
                System.out.println("Hit Skull");
            }
            skull.move(delta);
        }

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        gameStarted = false;
        timer.instance().stop();
        timer.instance().clear();
        game.getScreenRotator().allowRotation();
        System.out.println("Pause Game");
    }

    @Override
    public void resume() {
        System.out.println("Resume Game");
        timer = new Timer();
        timer.schedule(new Timer.Task(){
            @Override
            public void run(){
                --time;
                System.out.println("Time is " + time);
                if(time == 0) {
                    endGame();
                    System.out.println("Timer Up");
                }
                if(time%5 == 0){
                    skull.addSpeed();
                    System.out.println("Adding Speed to Skull");
                }
            }
        },1,1,time);
        timer.instance().stop();
        game.getScreenRotator().allowRotation();
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.BACK){
            gameStarted = false;
            timer.instance().stop();
            timer.instance().clear();
            game.getScreenRotator().allowRotation();
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
        if(this.soundOn.isPressed(touchPos) && !gameStarted){
            if(soundManager.isSoundOn())
                soundManager.setSoundOn(false);
            else
                soundManager.setSoundOn(true);
        }
        else if(!gameStarted && !this.soundOn.isPressed(touchPos)){
            game.getScreenRotator().lockRotation();
            gameStarted = true;
            timer.instance().start();
            soundManager.startBackgroundMusic();
            System.out.println("Timer Started");
        }
        else{
            game.getScreenRotator().allowRotation();
            gameStarted = false;
            timer.instance().stop();
            soundManager.stopBackgroundMusic();
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
