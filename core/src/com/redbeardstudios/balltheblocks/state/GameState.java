package com.redbeardstudios.balltheblocks.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.redbeardstudios.balltheblocks.BallTheBlocks;

/**
 * Created by Adam on 4/6/2015.
 */
public class GameState {

    private Preferences prefs;

    public boolean showAd(){
        return prefs.getBoolean("showAds",true);
    }
    public boolean showStory(){
        return prefs.getBoolean("showStory",true);
    }
    public boolean isSoundOn(){ return prefs.getBoolean("isSoundOn",true);}
    public void setAds(boolean status){
        prefs.putBoolean("showAds",status);
        prefs.flush();
    }
    public void setStory(boolean status){
        prefs.putBoolean("showStory",status);
        prefs.flush();
    }
    public void setSoundOn(boolean status){
        prefs.putBoolean("isSoundOn",status);
        prefs.flush();
    }

    public GameState(){
        prefs = Gdx.app.getPreferences("My Preferences");
    }
}
