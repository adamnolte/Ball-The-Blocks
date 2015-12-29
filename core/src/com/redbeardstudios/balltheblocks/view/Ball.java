package com.redbeardstudios.balltheblocks.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Adam on 3/3/2015.
 */
public class Ball {

    private int xSPEED = 400;
    private int ySPEED = 400;
    private int width;
    private int height;
    private int screenWidth;
    private int screenHeight;
    private TextureRegion image;
    private Rectangle rect;

    public Rectangle getPosition(){return rect;}

    public Ball(int screenWidth, int screenHeight, TextureRegion image){
        this.screenWidth = screenWidth;
        this.image = image;
        this.screenHeight = screenHeight;

        rect = new Rectangle();
        rect.x = 0;
        rect.y = 0;
        width = image.getRegionWidth();
        height = image.getRegionHeight();
        rect.width = width;
        rect.height = height;
    }

    public void move(float delta,float x, float y){
        rect.x += delta * xSPEED * x;
        rect.y += delta * ySPEED * y;

        keepOnScreen();
    }

    private void keepOnScreen(){
        if(rect.x < 0){
            rect.x = 0;
        }
        else if((rect.x + width) > screenWidth){
            rect.x = screenWidth - width;
        }
        if(rect.y < 0){
            rect.y = 0;
        }
        else if((rect.y + height) > screenHeight){
            rect.y = screenHeight - height;
        }
    }

    public void draw(SpriteBatch batch){
        batch.draw(image,rect.x,rect.y);
    }
}
