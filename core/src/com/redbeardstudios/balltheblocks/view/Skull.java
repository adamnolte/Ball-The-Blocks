package com.redbeardstudios.balltheblocks.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

/**
 * Created by Adam on 4/1/2015.
 */
public class Skull {


    private TextureRegion image;
    private float width;
    private float height;
    private Rectangle rect;
    private Random rand;
    private int xDir = 1;
    private int yDir = 1;
    private int speed = 200;
    private int screenWidth;
    private int screenHeight;

    public Rectangle getPosition(){ return rect;}

    public Skull(TextureRegion image, int screenWidth, int screenHeight){
        this.image = image;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        rect = new Rectangle();
        width = image.getRegionWidth();
        height = image.getRegionHeight();
        rect.width = width;
        rect.height = height;
        rect.x = screenWidth/2;
        rect.y = 0;
        rand = new Random();
    }

    public void draw(SpriteBatch batch){
        batch.draw(image,rect.x,rect.y);

    }

    public void setPos(float x, float y){
        rect.x = x;
        rect.y = y;
    }

    public void move(float delta){
        if(rand.nextInt(100) < 2){
            xDir = xDir * -1;
        }
        if(rand.nextInt(100) > 98){
            yDir = yDir * -1;
        }
        rect.x += delta * speed * xDir;
        rect.y += delta * speed * yDir;
        keepOnScreen();
    }

    public void addSpeed(){
        speed += 35;
    }

    private void keepOnScreen(){
        if(rect.x < 0){
            rect.x = 0;
            xDir = 1;
        }
        else if((rect.x + width) > screenWidth){
            rect.x = screenWidth - width;
            xDir = -1;
        }
        if(rect.y < 0){
            rect.y = 0;
            yDir = 1;
        }
        else if((rect.y + height) > screenHeight){
            rect.y = screenHeight - height;
            yDir = -1;
        }
    }

    public void setImage(TextureRegion image){
        this.image = image;
    }

    public boolean isOverlapping(Rectangle otherRect){ return rect.overlaps(otherRect);}
}
