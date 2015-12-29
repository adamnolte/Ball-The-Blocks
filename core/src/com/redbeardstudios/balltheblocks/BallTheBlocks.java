package com.redbeardstudios.balltheblocks;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.redbeardstudios.balltheblocks.images.ImageProvider;
import com.redbeardstudios.balltheblocks.screens.EndScreen;
import com.redbeardstudios.balltheblocks.screens.GameScreen;
import com.redbeardstudios.balltheblocks.screens.MainMenuScreen;
import com.redbeardstudios.balltheblocks.screens.MoreScreen;
import com.redbeardstudios.balltheblocks.screens.PurchaseScreen;
import com.redbeardstudios.balltheblocks.screens.SplashScreen;
import com.redbeardstudios.balltheblocks.screens.StoryScreen;
import com.redbeardstudios.balltheblocks.sound.SoundManager;
import com.redbeardstudios.balltheblocks.state.GameState;
import com.redbeardstudios.balltheblocks.state.PurchaseHandler;

public class BallTheBlocks extends Game{
	SpriteBatch batch;
    public BitmapFont scoreFont;
    private ImageProvider imageProvider;
    private SoundManager soundManager;
    private AAdsController aAdsController;
    private IAdsController iAdsController;
    private ILeaderboard iLeaderboard;
    private ALeaderboard aLeaderboard;
    private GameState gameState;
    private PurchaseHandler purchaseHandler;
    private ScreenRotator screenRotator;

    public int currScore = 0;

    private Screen gameScreen;
    private Screen endScreen;
    private Screen moreScreen;
    private Screen mainMenuScreen;
    private Screen storyScreen;
    private Screen purchaseScreen;

    public PurchaseHandler getPurchaseHandler(){ return purchaseHandler;}

    public GameState getGameState(){ return gameState;}

    public SpriteBatch getBatch() {
        return batch;
    }

    public ImageProvider getImageProvider() {
        return imageProvider;
    }

    public SoundManager getSoundManager() {
        return soundManager;
    }

    public AAdsController getaAdsController() {
        return aAdsController;
    }

    public IAdsController getiAdsController() {
        return iAdsController;
    }

    public ILeaderboard getiLeaderboard() {
        return iLeaderboard;
    }

    public ALeaderboard getaLeaderboard() {
        return aLeaderboard;
    }

    public ScreenRotator getScreenRotator(){ return screenRotator;}

    public BallTheBlocks(ScreenRotator screenRotator, AAdsController aAdsController, ALeaderboard aLeaderboard, IAdsController iAdsController, ILeaderboard iLeaderboard){
        if(screenRotator != null) {
            this.screenRotator = screenRotator;
        }
        if(aAdsController != null){
            this.aAdsController = aAdsController;
            this.aLeaderboard = aLeaderboard;
            this.iAdsController = new IDummy();

            this.iLeaderboard = new IDummy();
        }
        else if(iAdsController != null){
            this.aAdsController = new ADummy();
            this.aLeaderboard = new ADummy();
            this.iAdsController = iAdsController;
            this.iLeaderboard = iLeaderboard;
        }
        else{
            this.aAdsController = new ADummy();
            this.aLeaderboard = new ADummy();
            this.iAdsController = new IDummy();
            this.iLeaderboard = new IDummy();
            this.screenRotator = new FakeScreenRotator();
        }
    }
	
	@Override
	public void create () {
        batch = new SpriteBatch();

        imageProvider = new ImageProvider();

        soundManager = new SoundManager();

        purchaseHandler = new PurchaseHandler(this);

        this.gameState = new GameState();

        scoreFont = new BitmapFont(Gdx.files.internal("score-font.fnt"));

        this.setScreen(new SplashScreen(batch,this));

	}

	@Override
	public void render () {
        super.render();
	}

    public void dispose(){
        soundManager.dispose();
        imageProvider.dispose();
    }

    public void changeScreen(String screenName){

        if( screenName.equalsIgnoreCase("game")){
            if(gameScreen !=null){
                gameScreen.dispose();
            }
            gameScreen = new GameScreen(this);
            this.setScreen(gameScreen);
        }
        else if( screenName.equalsIgnoreCase("mainMenu"))
        {
            if (mainMenuScreen != null) {
                this.setScreen(mainMenuScreen);
            } else {
                mainMenuScreen = new MainMenuScreen(this);
                this.setScreen(mainMenuScreen);
            }
        }
        else if( screenName.equalsIgnoreCase("end"))
        {
            if (endScreen != null) {
                this.setScreen(endScreen);
            } else {
                endScreen = new EndScreen(this);
                this.setScreen(endScreen);
            }
        }
        else if( screenName.equalsIgnoreCase("more"))
        {
            if (moreScreen != null) {
                this.setScreen(moreScreen);
            } else {
                moreScreen = new MoreScreen(this);
                this.setScreen(moreScreen);
            }
        }
        else if( screenName.equalsIgnoreCase("story"))
        {
            if (storyScreen != null) {
                this.setScreen(storyScreen);
            } else {
                storyScreen = new StoryScreen(this);
                this.setScreen(storyScreen);
            }
        }
        else if( screenName.equalsIgnoreCase("purchase")){
            if(purchaseScreen !=null){
                this.setScreen(purchaseScreen);
            }
            else{
                purchaseScreen = new PurchaseScreen(this);
                this.setScreen(purchaseScreen);
            }
        }
    }
}
