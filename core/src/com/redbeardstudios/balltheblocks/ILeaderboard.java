package com.redbeardstudios.balltheblocks;

/**
 * Created by Adam on 3/3/2015.
 */
public interface ILeaderboard {
    public void showScores();
    public void submitScore(long score);
    public void initialize();
}
