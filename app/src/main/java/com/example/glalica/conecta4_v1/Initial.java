package com.example.glalica.conecta4_v1;

import android.app.Activity;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;

public class Initial extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);
        //Cargamos las preferencias por omisión
        /* false:indica que éstos parámetros solo se deben
        * cargar la primera vez que se llama a la función */
        //PreferenceManager.setDefaultValues(this,R.xml.settings, false);
    }

    public boolean onTouchEvent(MotionEvent event){
        if (event.getAction() == MotionEvent.ACTION_DOWN){
           Intent i= new Intent(this,MainActivity.class);
            startActivity(i);
        }
        return true;
    }

}
