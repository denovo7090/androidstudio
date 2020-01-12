package com.gamecodeschool.c1tappydefender;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class PlayerShip {

    private Bitmap bitmap;
    private int x, y;
    private int speed;
    private boolean boosting;
    private final int GRAVITY=-12;
    //STOP SHIP LEAVING THE SCREEN
    private int maxY;
    private int minY;
    //Limit the bounds of ship's speed
    private final int MIN_SPEED=1;
    private final int MAX_SPEED=20;


    public PlayerShip(Context context, int screenX, int screenY){
        boosting=false;
        x=50;
        y=50;
        speed=1;
        bitmap= BitmapFactory.decodeResource(context.getResources(),R.drawable.ship);
        maxY = screenY - bitmap.getHeight();
        minY = 0;
        speed=1;

    }
    public void update(){
     // ARE WE BOOSTING?
        if(boosting){
            //speed up
            speed+=2;

        }else{
            speed-=5;
        }
        //Constrain top speed
        if (speed>MAX_SPEED){
            speed=MAX_SPEED;
        }
        // NEVER STOP COMPLETELY
        if (speed<MIN_SPEED){
            speed=MIN_SPEED;
        }
        // MOVE THE SHIP UP OR DOWN
        y-= speed+GRAVITY;
        //but don't let ship stray off screen
        if(y<minY){
            y=minY;
        }
        if (y>maxY){
            y=maxY;
        }
    }

    //Getters
    public  Bitmap getBitmap(){
        return bitmap;

    }
    public int getSpeed(){
        return speed;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setBoosting(){
        boosting=true;
    }
    public void stopBoosting(){
        boosting=false;
    }
}
