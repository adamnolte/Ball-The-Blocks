package com.redbeardstudios.balltheblocks.view;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * Created by Adam on 4/7/2015.
 */
public class BlankButton {
    private float startX;

    private float endX;

    private float startY;

    private float endY;

    private static long timePressed = 0;

    public BlankButton(float startX, float startY, float endX, float endY){
        this.startX = startX;
        this.startY = 1080-startY;
        this.endX = endX;
        this.endY = 1080-endY;
    }

    public boolean isPressed(Vector3 touchPos){

        if(touchPos.x > startX && touchPos.x < endX && touchPos.y > startY && touchPos.y < endY){
            long now = TimeUtils.millis();
            if(now - timePressed > 200){
                timePressed = TimeUtils.millis();
                return true;
            }
        }

        return false;
    }
}
