package com.gamecodeschool.c1tappydefender;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;


public class EnemyShip {
    private Bitmap bitmap;
    private int x, y;
    private int speed = 1;

    // Detect enemy's leaving the screen
    private int minX;
    private int maxX;

    // spawn enemy screen bound

    private int minY;
    private int maxY;


// getter and setters

    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

//Constructor
    public EnemyShip(Context context,int screenX, int screenY) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy);

        maxX = screenX;
        maxY = screenY;
        minX = 0;
        minY = 0;

        Random generator = new Random();
        speed = generator.nextInt(6) + 10;
        x = screenX;
        y = generator.nextInt(maxY) - bitmap.getHeight();
    }
        public void update ( int playerSpeed){

            // Move to the left
            x -= playerSpeed;
            x -= speed;

            // respawn when off screen
            if (x < minX - bitmap.getWidth()){
                Random generator=new Random();
                speed=generator.nextInt(10)+10;
                x=maxX;
                y=generator.nextInt(maxY)-bitmap.getHeight();
            }
        }

}