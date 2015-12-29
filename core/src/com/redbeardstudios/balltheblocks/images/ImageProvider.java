package com.redbeardstudios.balltheblocks.images;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Adam on 3/3/2015.
 */
public class ImageProvider {

    private int SCREEN_WIDTH = 1920;
    private int SCREEN_HEIGHT = 1080;

    public AssetManager manager = new AssetManager();


    public void load(){
        manager.load("ball-assets.pack",TextureAtlas.class);
        manager.load("game-instructions.png",Texture.class);
        manager.load("balltheblocks.png",Texture.class);
        manager.load("gameover.png",Texture.class);
        manager.load("morebackground.png",Texture.class);
        manager.load("screen1words1.png",Texture.class);
        manager.load("screen1words2.png",Texture.class);
        manager.load("scene2.png",Texture.class);
        manager.load("scene3.png",Texture.class);
        manager.load("scene4.png",Texture.class);
        manager.load("screen1words3.png",Texture.class);
        manager.load("ads-removed.png",Texture.class);
    }

    public boolean update(){
        return manager.update();
    }

    public void dispose(){
        manager.unload("ball-assets.pack");
        manager.unload("game-instructions.png");
        manager.unload("balltheblocks.png");
        manager.unload("gameover.png");
        manager.unload("morebackground.png");
        manager.unload("scene2.png");
        manager.unload("scene3.png");
        manager.unload("scene4.png");
        manager.unload("screen1words1.png");
        manager.unload("screen1words2.png");
        manager.unload("ads-removed.png");
    }

    public int getScreenWidth() {
        return SCREEN_WIDTH;
    }

    public int getScreenHeight() {
        return SCREEN_HEIGHT;
    }

    public Texture getAdsRemoved(){ return manager.get("ads-removed.png",Texture.class); }
    public Texture getScene1Words1(){ return manager.get("screen1words1.png",Texture.class);}

    public Texture getScene1Words2(){ return manager.get("screen1words2.png",Texture.class);}

    public Texture getScene1Words3(){ return manager.get("screen1words3.png",Texture.class);}

    public Texture getScene2() { return manager.get("scene2.png",Texture.class);}

    public Texture getScene3() { return manager.get("scene3.png",Texture.class);}

    public Texture getScene4() { return manager.get("scene4.png",Texture.class);}

    public Texture getMoreBackground(){ return manager.get("morebackground.png", Texture.class);}

    public TextureRegion getMainMenuBall(){ return manager.get("ball-assets.pack", TextureAtlas.class).findRegion("menuscreenball");}

    public TextureRegion getScreenSkull(){ return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("screenskull");}

    public Texture getGameInstructions(){ return manager.get("game-instructions.png",Texture.class);}

    public TextureRegion getBackButton(){ return manager.get("ball-assets.pack", TextureAtlas.class).findRegion("back-button");}

    public TextureRegion getMoreGamesButton(){ return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("moregames-button");}

    public TextureRegion getStoryButton(){ return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("story-button");}

    public TextureRegion getRateButton(){ return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("rate-button");}

    public TextureRegion getRestoreButton(){ return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("restorepurchasesblock");}

    public TextureRegion getPurchaseButton(){ return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("removeadsblock");}

    public TextureRegion getContinueButton(){ return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("continue-button");}

    public TextureRegion getSkipButton(){ return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("skip-button");}

    public TextureRegion getSoundOn() {
        return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("sound_on");
    }

    public TextureRegion getSoundOff() {
        return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("sound_off");
    }

    public TextureRegion getStartButton() {
        return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("start-button");
    }

    public TextureRegion getScoreButton() {
        return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("scores-button");
    }

    public TextureRegion getMenuButton() {
        return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("menu-button");
    }

    public TextureRegion getSubmitButton() {
        return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("submit-button");
    }

    public TextureRegion getMoreButton() {
        return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("more-button");
    }
    public TextureRegion getRmAdsButton() {
        return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("remove-button");
    }

    public TextureRegion getRestartButton(){ return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("restart-button");}

    public Texture getGameOver() {
        return manager.get("gameover.png",Texture.class);
    }

    public TextureRegion getBall() {
        return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("ball");
    }

    public Texture getLogo() {
        return manager.get("balltheblocks.png",Texture.class);
    }

    public TextureRegion getbBlock1() { return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("Blueblock",1);}

    public TextureRegion getbBlock2(){ return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("Blueblock",2);}

    public TextureRegion getbBlock3(){ return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("Blueblock",3);}

    public TextureRegion getbBlock4(){ return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("Blueblock",4);}

    public TextureRegion getbBlock5(){ return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("Blueblock",5);}

    public TextureRegion getbBlock6(){ return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("Blueblock",6);}

    public TextureRegion getyBlock1(){ return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("Yellowblock",1);}

    public TextureRegion getyBlock2(){ return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("Yellowblock",2);}

    public TextureRegion getyBlock3(){ return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("Yellowblock",3);}

    public TextureRegion getyBlock4(){ return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("Yellowblock",4);}

    public TextureRegion getyBlock5(){ return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("Yellowblock",5);}

    public TextureRegion getyBlock6(){ return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("Yellowblock",6);}

    public TextureRegion getdbBlock1(){ return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("Darkblueblock",1);}

    public TextureRegion getdbBlock2(){ return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("Darkblueblock",2);}

    public TextureRegion getdbBlock3(){ return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("Darkblueblock",3);}

    public TextureRegion getdbBlock4(){ return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("Darkblueblock",4);}

    public TextureRegion getdbBlock5(){ return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("Darkblueblock",5);}

    public TextureRegion getdbBlock6(){ return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("Darkblueblock",6);}

    public TextureRegion getwBlock1(){ return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("whiteblock",1);}

    public TextureRegion getwBlock2(){ return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("whiteblock",2);}

    public TextureRegion getwBlock3(){ return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("whiteblock",3);}

    public TextureRegion getwBlock4(){ return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("whiteblock",4);}

    public TextureRegion getwBlock5(){ return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("whiteblock",5);}

    public TextureRegion getwBlock6(){ return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("whiteblock",6);}

    public TextureRegion getSkull(){ return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("skull");}

    public TextureRegion getCircle1(){ return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("circle",9);}

    public TextureRegion getCircle2(){ return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("circle",8);}

    public TextureRegion getCircle3(){ return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("circle",7);}

    public TextureRegion getCircle4(){ return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("circle",6);}

    public TextureRegion getCircle5(){ return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("circle",5);}

    public TextureRegion getCircle6(){ return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("circle",4);}

    public TextureRegion getCircle7(){ return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("circle",3);}

    public TextureRegion getCircle8(){ return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("circle",2);}

    public TextureRegion getCircle9(){ return manager.get("ball-assets.pack",TextureAtlas.class).findRegion("circle",1);}
}
