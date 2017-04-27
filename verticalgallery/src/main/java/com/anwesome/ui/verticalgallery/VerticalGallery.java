package com.anwesome.ui.verticalgallery;

import android.app.Activity;
import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anweshmishra on 27/04/17.
 */
public class VerticalGallery {
    private Activity activity;
    private VerticalGalleryView verticalGalleryView;
    private List<GalleryItem> galleryItems = new ArrayList<>();
    public VerticalGallery(Activity activity) {
        this.activity = activity;
    }
    public void addGalleryItem(Bitmap bitmap,String title) {
        if(verticalGalleryView == null) {
            galleryItems.add(new GalleryItem(bitmap,title));
        }
    }
    public void show() {
        if(verticalGalleryView == null) {
            verticalGalleryView = new VerticalGalleryView(activity,galleryItems);
            activity.setContentView(verticalGalleryView);
        }
    }
}
