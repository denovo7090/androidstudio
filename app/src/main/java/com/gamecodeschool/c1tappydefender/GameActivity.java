package com.gamecodeschool.c1tappydefender;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.graphics.Point;


public class GameActivity extends Activity {
    private TDView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get a display object to access screen
        Display display=getWindowManager().getDefaultDisplay();
        //Load the resolution into a Point object
        Point size=new Point();
        display.getSize(size);

// create instance of Tappy defender view


        gameView=new TDView(this, size.x, size.y);
        setContentView(gameView);

    }
    @Override
    protected void onPause(){
        super.onPause();
        gameView.pause();

    }
    @Override
    protected void onResume(){
        super.onResume();
        gameView.resume();

    }

}
