package com.redbeardstudios.balltheblocks;

/**
 * Created by Adam on 3/3/2015.
 */
public interface ALeaderboard {
    public void signIn();
    public void signOut();
    public void rateGame();
    public void submitScore(long score);
    public void showScores();
    public boolean isSignedIn();
}
