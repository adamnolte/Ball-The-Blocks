package com.redbeardstudios.balltheblocks.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

/**
 * Created by Adam on 3/3/2015.
 */
public class Block {

    private TextureRegion image;
    private int width;
    private int height;
    private Rectangle rect;
    private Random rand;
    private int xDir = 1;
    private int yDir = 1;
    private String color;

    public Rectangle getPosition(){ return rect;}

    public String getColor(){ return color;}

    public Block(TextureRegion image, float x, float y, String color){
        rect = new Rectangle();
        width = image.getRegionWidth();
        height = image.getRegionHeight();
        rect.width = width;
        rect.height = height;
        rect.x = x;
        rect.y = y;
        rand = new Random();
        this.image = image;
        this.color = color;
    }

    public void draw(SpriteBatch batch){
        batch.draw(image,rect.x,rect.y);

    }

    public void setPos(float x, float y){
        rect.x = x;
        rect.y = y;
    }

    public void move(float delta){
        if(rand.nextInt(100) < 3){
            xDir = xDir * -1;
        }
        if(rand.nextInt(100) > 97){
            yDir = yDir * -1;
        }
        rect.x += delta * 250 * xDir;
        rect.y += delta * 250 * yDir;
        keepOnScreen();
    }

    private void keepOnScreen(){
        if(rect.x < 0){
            rect.x = 0;
            xDir = 1;
        }
        else if((rect.x + width) > 800){
            rect.x = 800 - width;
            xDir = -1;
        }
        if(rect.y < 0){
            rect.y = 0;
            yDir = 1;
        }
        else if((rect.y + height) > 480){
            rect.y = 480 - height;
            yDir = -1;
        }
    }

    public void setImage(TextureRegion image){
        this.image = image;
    }

    public boolean isOverlapping(Rectangle otherRect){ return rect.overlaps(otherRect);}
}
