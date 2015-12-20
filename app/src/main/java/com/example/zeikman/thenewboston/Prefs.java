package com.example.zeikman.thenewboston;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by zeikman on 12/20/15.
 */
public class Prefs extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs);
    }
}
