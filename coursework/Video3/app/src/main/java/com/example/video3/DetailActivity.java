package com.example.video3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.widget.ImageView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView iv = (ImageView) findViewById(R.id.imageView);

        int index = getIntent().getIntExtra("ITEM_INDEX", -1);

        if(index > -1){
            int pic = getImage(index);
            scaleImage(iv, pic);
        }
    }

    private int getImage(int index){
        switch (index){
            case 0: return R.drawable.nice;
            case 1: return R.drawable.wok;
            case 2: return R.drawable.johnxina;
            default: return -1;
        }
    }

    private void scaleImage(ImageView img, int pic){
        Display screen = getWindowManager().getDefaultDisplay();
        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), pic, options);

        int imgWidth = options.outWidth;
        int scrWidth = screen.getWidth();

        if(imgWidth > screen.getWidth()){
            int ratio = Math.round((float)imgWidth / (float)scrWidth);
            options.inSampleSize = ratio;
        }

        options.inJustDecodeBounds = false;
        Bitmap scaledImg = BitmapFactory.decodeResource(getResources(), pic, options);
        img.setImageBitmap(scaledImg);

    }
}