package com.anwesome.ui.verticalgalleryviewdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.anwesome.ui.verticalgallery.VerticalGallery;

public class MainActivity extends AppCompatActivity {
    private int images[] = {R.drawable.back1,R.drawable.back2,R.drawable.back3,R.drawable.back4};
    private String titles[] = {"back1","back2","back3","back4"};
    private Bitmap bitmaps[] = new Bitmap[4];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VerticalGallery verticalGallery = new VerticalGallery(this);
        for(int i=0;i<titles.length;i++) {
            bitmaps[i] = BitmapFactory.decodeResource(getResources(),images[i]);
        }
        for(int i=0;i<images.length;i++) {
            final String title = titles[i];
            verticalGallery.addGalleryItem(bitmaps[i],title);
        }
        verticalGallery.show();
    }
}
