package com.example.glalica.conecta4_v1;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by glalica on 21/11/2015.
 * No es imprescindible extender ésta clase, pero Android lo recomienda
 */
public class CCCPreferenceFragment extends PreferenceFragment{
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }

}
