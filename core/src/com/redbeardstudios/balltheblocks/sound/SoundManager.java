package com.redbeardstudios.balltheblocks.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.redbeardstudios.balltheblocks.state.GameState;

/**
 * Created by Adam on 3/3/2015.
 */
public class SoundManager {

    private GameState gameState;
    public AssetManager manager;

    public void setSoundOn(boolean isSoundOn){
        gameState.setSoundOn(isSoundOn);
    }

    public boolean isSoundOn(){return gameState.isSoundOn();}

    public void load(){
        manager = new AssetManager();
        gameState = new GameState();
        manager.load("hit-block.wav",Sound.class);
        manager.load("game-over.wav",Sound.class);
        manager.load("background-music.wav",Music.class);
    }

    public boolean update(){ return manager.update();}

    public void dispose(){
        manager.unload("hit-block.wav");
        manager.unload("game-over.wav");
        manager.unload("background-music.wav");
    }

    public void playHitBlockSound(){
        if(isSoundOn()){
            manager.get("hit-block.wav",Sound.class).play();
        }
    }
    public void playGameOverSound(){
        if(isSoundOn()){
            manager.get("game-over.wav",Sound.class).play();
        }
    }
    public void startBackgroundMusic(){
        if(isSoundOn()){
            manager.get("background-music.wav",Music.class).setLooping(true);
            manager.get("background-music.wav",Music.class).play();
        }
    }
    public void stopBackgroundMusic(){
        manager.get("background-music.wav",Music.class).stop();
    }
}
