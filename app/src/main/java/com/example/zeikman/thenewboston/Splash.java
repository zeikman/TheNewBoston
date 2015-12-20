package com.example.zeikman.thenewboston;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;

/**
 * Created by zeikman on 12/18/15.
 */
public class Splash extends Activity {
    MediaPlayer appSong;

    @Override
    protected void onCreate(Bundle bostonInstanceState) {
        super.onCreate(bostonInstanceState);
        setContentView(R.layout.splash);

        appSong = MediaPlayer.create(Splash.this, R.raw.romantic_stroll);
        SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean music = getPrefs.getBoolean("splashmusic", true);
        if (music) appSong.start();

        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(1000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    Intent openStartingPoint = new Intent("com.example.zeikman.thenewboston.MENU");
                    startActivity(openStartingPoint);
                }
            }
        };
        timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        appSong.release();
        finish();
    }
}
