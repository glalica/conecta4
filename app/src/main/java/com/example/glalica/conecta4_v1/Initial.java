package com.example.glalica.conecta4_v1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

public class Initial extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);
    }

    public boolean onTouchEvent(MotionEvent event){
        if (event.getAction() == MotionEvent.ACTION_DOWN){
           Intent i= new Intent(this,MainActivity.class);
            //startActivity(new Intent("com.example.glalica.conecta4_v1.MAINACTIVITY"));
            startActivity(i);
        }
        return true;
    }

}
