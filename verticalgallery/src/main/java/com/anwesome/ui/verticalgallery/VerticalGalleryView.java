package com.anwesome.ui.verticalgallery;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 27/04/17.
 */
public class VerticalGalleryView extends View {
    public VerticalGalleryView(Context context) {
        super(context);
    }
    public void onDraw(Canvas canvas) {

    }
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}
