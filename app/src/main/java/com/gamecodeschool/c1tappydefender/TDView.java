package com.gamecodeschool.c1tappydefender;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.graphics.Paint;
import android.view.MotionEvent;

public class TDView extends SurfaceView implements Runnable {



    // For drawing

    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder ourHolder;

    volatile boolean playing;
    Thread gameThread=null;

    // game object

    private PlayerShip player;
    public EnemyShip enemy1;
    public EnemyShip enemy2;
    public EnemyShip enemy3;


    public TDView(Context context, int x, int y) {
        super(context);

        // intiallize drawing objects
        ourHolder = getHolder();
        paint = new Paint();

        // intialize player ship
        player = new PlayerShip(context,x,y);
        enemy1=new EnemyShip(context, x,y);
        enemy2=new EnemyShip(context,x,y);
        enemy3=new EnemyShip(context,x,y);




    }
        @Override
                public void run(){
            while (playing){
                update();
                draw();
                control();
            }
        }


    private void update(){
        // update the player
        player.update();
        // update enemies
        enemy1.update(player.getSpeed());
        enemy2.update(player.getSpeed());
        enemy3.update(player.getSpeed());
    }
    private void draw(){
        if (ourHolder.getSurface().isValid()){
            // first we lock the area of memory will be draing to
            canvas=ourHolder.lockCanvas();
            //Rub out the last frame
            canvas.drawColor(Color.argb(255,0,0,0));
           // Draw the player

            canvas.drawBitmap(
                   player.getBitmap(),player.getX(), player.getY(),paint);
            canvas.drawBitmap(enemy1.getBitmap(),enemy1.getX(),enemy1.getY(),paint);
            canvas.drawBitmap(enemy2.getBitmap(),enemy2.getX(),enemy2.getY(),paint);
            canvas.drawBitmap(enemy3.getBitmap(),enemy3.getX(),enemy3.getY(),paint);


            // unlock and draw the scene
            ourHolder.unlockCanvasAndPost(canvas);


        }

    }
    private void control() {
        try {
            gameThread.sleep(17);
        } catch (InterruptedException e) {

        }
    }
    public void pause() {
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {


        }
    }
        public  void resume(){
            playing=true;
            gameThread=new Thread(this);
            gameThread.start();
        }

        @Override
    public boolean onTouchEvent(MotionEvent motionEvent){
        switch(motionEvent.getAction() & MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_UP:
            player.stopBoosting();
                break;
            case MotionEvent.ACTION_DOWN:
                player.setBoosting();
                break;
                // has the player lifted finger up?


        }
        return true;
        }

    }



