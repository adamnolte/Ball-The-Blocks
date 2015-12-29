package com.redbeardstudios.balltheblocks.state;

import com.badlogic.gdx.pay.Offer;
import com.badlogic.gdx.pay.OfferType;
import com.badlogic.gdx.pay.PurchaseManagerConfig;
import com.badlogic.gdx.pay.PurchaseObserver;
import com.badlogic.gdx.pay.PurchaseSystem;
import com.badlogic.gdx.pay.Transaction;
import com.redbeardstudios.balltheblocks.BallTheBlocks;

/**
 * Created by Adam on 4/6/2015.
 */
public class PurchaseHandler {

    final BallTheBlocks game;
    private final String GOOGLE_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiQfRB7qNP1c27T2ccivgRq6PKOK3Q51nZImAN0Ys+3Ew5Cz06dcCoCCOUcA7xHc47nqzvw+irw6LVC0mP8Amh68VroXlvDglmI+qZryfYYyqUi3lqrwISVIWpaYJx0LVd2vyLKrvgT09OmWuIRNrxmTZoD4zEX+/46q1CaI9fNm1aY2/dYoo5koRkNv3J1v1jquEwm4mb3jTruwktblzDh2Ni9K916uRq9fkqKTn8IUoXL0NhIMbNRGZQvDID7k/RV3YGpTDzaOJbKDzbQ3fX+15LSKPzvAv1D1o0H8oN23w/huR2VLKqwj82871jBerlauPDbk25cOlRCJk8GO/iQIDAQAB";
    private final String itemID = "item_000001";

    public PurchaseHandler(BallTheBlocks game){
        this.loadPurchaseManager();
        this.game = game;
    }

    public void purchaseRemoveAds(){
        if(PurchaseSystem.installed()){
            PurchaseSystem.purchase(itemID);
        }
    }

    public void restorePurchases(){
        if(PurchaseSystem.installed()){
            PurchaseSystem.purchaseRestore();
        }
    }

    public void loadPurchaseManager(){
        if(PurchaseSystem.hasManager()) {
            System.out.println("Has Purchase Manager");

            PurchaseManagerConfig config = new PurchaseManagerConfig();
            config.addStoreParam(PurchaseManagerConfig.STORE_NAME_ANDROID_GOOGLE, GOOGLE_KEY);

            config.addOffer(new Offer().setType(OfferType.CONSUMABLE).setIdentifier(itemID));

            PurchaseSystem.install(new PurchaseObserver() {
                @Override
                public void handleInstall() {

                }

                @Override
                public void handleInstallError(Throwable e) {

                }

                @Override
                public void handleRestore(Transaction[] transactions) {
                    for(Transaction transaction : transactions) {
                        if(transaction.getIdentifier().equalsIgnoreCase(itemID) && transaction.isPurchased()) {
                            game.getGameState().setAds(false);
                        }
                    }
                }

                @Override
                public void handleRestoreError(Throwable e) {

                }

                @Override
                public void handlePurchase(Transaction transaction) {
                    if(transaction.getIdentifier().equalsIgnoreCase(itemID) && transaction.isPurchased()){
                        game.getGameState().setAds(false);
                    }
                    System.out.println("Purchase");
                }

                @Override
                public void handlePurchaseError(Throwable e) {

                }

                @Override
                public void handlePurchaseCanceled() {

                }
            }, config);
        }
    }
}
