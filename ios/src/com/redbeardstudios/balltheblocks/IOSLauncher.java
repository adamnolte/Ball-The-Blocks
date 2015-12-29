package com.redbeardstudios.balltheblocks;

import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.foundation.NSError;
import org.robovm.apple.foundation.NSNotificationCenter;
import org.robovm.apple.foundation.NSString;
import org.robovm.apple.gamekit.GKAchievement;
import org.robovm.apple.gamekit.GKLeaderboard;
import org.robovm.apple.uikit.UIApplication;
import org.robovm.apple.uikit.UIDevice;
import org.robovm.apple.uikit.UIDeviceOrientation;
import org.robovm.apple.uikit.UIScreen;
import org.robovm.apple.uikit.UIViewController;
import org.robovm.apple.uikit.UIWindow;

import com.badlogic.gdx.backends.iosrobovm.IOSApplication;
import com.badlogic.gdx.backends.iosrobovm.IOSApplicationConfiguration;
import com.redbeardstudios.balltheblocks.BallTheBlocks;

import java.util.ArrayList;

public class IOSLauncher extends IOSApplication.Delegate implements IAdsController, ILeaderboard, ScreenRotator{

    private IOSApplication iOSApplication;
    private GameCenterManager gcManager;

    private UIViewController rootViewController;
    private UIWindow window;
    private GADInterstitial interstitial;
    private static final String AD_ID = "ca-app-pub-9107232888348693/8512943140";

    @Override
    protected IOSApplication createApplication() {
        IOSApplicationConfiguration config = new IOSApplicationConfiguration();
        config.orientationLandscape = true;
        config.orientationPortrait = false;
        config.allowIpod = true;
        config.preventScreenDimming = true;
        return new IOSApplication(new BallTheBlocks(this,null,null,this,this), config);
    }

    public static void main(String[] argv) {
        NSAutoreleasePool pool = new NSAutoreleasePool();
        UIApplication.main(argv, null, IOSLauncher.class);
        pool.close();
    }


    public void lockRotation(){
            UIApplication.getSharedApplication().getKeyWindow().
    }
    public void allowRotation(){

    }

    public void showAd(){
        if(window != null && rootViewController != null && interstitial.isReady()){
            System.out.println("Showing Ad");
            window = new UIWindow(UIScreen.getMainScreen().getBounds());
            window.setRootViewController(rootViewController);
            window.addSubview(rootViewController.getView());
            window.makeKeyAndVisible();

            interstitial.present(rootViewController);
        }
    }
    public void initializeAds(){
        rootViewController = new UIViewController();
        window = new UIWindow(UIScreen.getMainScreen().getBounds());
        System.out.println("Initializing Ads");

        loadAd();
    }

    public void loadAd(){
        interstitial = new GADInterstitial();
        interstitial.setAdUnitID(AD_ID);

        interstitial.setDelegate(new GADInterstitialDelegateAdapter() {
            @Override
            public void didReceiveAd (GADInterstitial ad) {
                System.out.println("Did receive ad.");
            }

            @Override
            public void didFailToReceiveAd (GADInterstitial ad, GADRequestError error) {
                System.out.println(error.description());
                System.out.println(error.getErrorCode());
            }
            @Override
            public void didDismissScreen(GADInterstitial ad){
                window.setHidden(true);
                loadAd();
            }
        });

        interstitial.loadRequest(GADRequest.create());

    }

    @Override
    public void showScores() {
        gcManager.showLeaderboardView("ball_leaderboard");

    }

    @Override
    public void submitScore(long score) {
        System.out.println("Submit Score");
        gcManager.reportScore("ball_leaderboard", score);

    }

    @Override
    public void initialize() {
        System.out.println("Initializing");
        gcManager = new GameCenterManager(UIApplication.getSharedApplication().getKeyWindow(),new GameCenterListener(){

            @Override
            public void achievementReportCompleted() {
                // TODO Auto-generated method stub

            }

            @Override
            public void achievementReportFailed(NSError arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void achievementViewDismissed() {
                // TODO Auto-generated method stub

            }

            @Override
            public void achievementsLoadCompleted(ArrayList<GKAchievement> arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void achievementsLoadFailed(NSError arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void achievementsResetCompleted() {
                // TODO Auto-generated method stub

            }

            @Override
            public void achievementsResetFailed(NSError arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void leaderboardViewDismissed() {
                // TODO Auto-generated method stub

            }

            @Override
            public void leaderboardsLoadCompleted(ArrayList<GKLeaderboard> arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void leaderboardsLoadFailed(NSError arg0) {
                System.out.println("scoresLoadFailed. error: " + arg0);

            }

            @Override
            public void playerLoginCompleted() {
                System.out.println("playerLoginCompleted");

            }

            @Override
            public void playerLoginFailed(NSError arg0) {
                System.out.println("playerLoginFailed. error: " + arg0);

            }

            @Override
            public void scoreReportCompleted() {
                System.out.println("Score Report completed");

            }

            @Override
            public void scoreReportFailed(NSError arg0) {
                System.out.println("Score Report Failed" + arg0);

            }

        });

        gcManager.login();

    }
}