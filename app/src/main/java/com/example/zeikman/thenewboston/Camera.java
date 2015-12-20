package com.example.zeikman.thenewboston;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zeikman on 12/19/15.
 */
public class Camera extends Activity implements View.OnClickListener {
    ImageButton ib;
    Button b;
    ImageView iv;
    Intent i;
    final static int cameraData = 0;
    Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo);

        initialize();

        InputStream is = getResources().openRawResource(R.drawable.icon);
        bmp = BitmapFactory.decodeStream(is);
    }

    private void initialize() {
        iv = (ImageView) findViewById(R.id.ivReturnedPic);
        ib = (ImageButton) findViewById(R.id.ibTakePic);
        b = (Button) findViewById(R.id.bSetWall);

        b.setOnClickListener(this);
        ib.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibTakePic:
                i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i, cameraData);
                break;
            case R.id.bSetWall:
                WallpaperManager wpManager = WallpaperManager.getInstance(this);
                try {
                    wpManager.setBitmap(bmp);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                /*try {
                    getApplicationContext().setWallpaper(bmp);
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            bmp = (Bitmap) extras.get("data");
            iv.setImageBitmap(bmp);
        }
    }
}
