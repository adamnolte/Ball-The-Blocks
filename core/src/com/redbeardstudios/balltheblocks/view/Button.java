package com.redbeardstudios.balltheblocks.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * Created by Adam on 3/3/2015.
 */
public class Button {

    private TextureRegion image;

    private float startX;

    private float endX;

    private float startY;

    private float endY;

    private static long timePressed = 0;


    public float getStartX() {
        return startX;
    }

    public float getStartY() {
        return startY;
    }

    public TextureRegion getImage(){return image;}

    public Button(TextureRegion image){
        this.image = image;
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

    public void setPos(float x, float y){
        startX = x;
        startY = 1080-y;

        endX = x + getWidth();
        endY = 1080- y + getHeight();
    }

    public void draw(SpriteBatch batch){
        batch.draw(image,startX,startY);
    }

    public int getWidth(){return image.getRegionWidth();}
    public int getHeight(){return image.getRegionHeight();}
}
