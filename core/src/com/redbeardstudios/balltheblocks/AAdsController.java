package com.redbeardstudios.balltheblocks;

/**
 * Created by Adam on 3/3/2015.
 */
public interface AAdsController {
    public boolean isConnected();
    public void showInterstitialAd(Runnable then);
}
