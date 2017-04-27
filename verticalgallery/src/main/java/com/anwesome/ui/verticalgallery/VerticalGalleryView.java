package com.anwesome.ui.verticalgallery;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anweshmishra on 27/04/17.
 */
public class VerticalGalleryView extends View {
    private List<GalleryItem> galleryItems = new ArrayList<>();
    private List<Indicator> indicators = new ArrayList<>();
    private int h ,maxH = 0;
    private int time = 0;
    private Screen screen = new Screen();
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public VerticalGalleryView(Context context,List<GalleryItem> galleryItems) {
        super(context);
        this.galleryItems = galleryItems;
    }
    public void onDraw(Canvas canvas) {
        int w = canvas.getWidth();
        h = canvas.getHeight();
        if(time == 0) {
            float y = 0;
            for(GalleryItem galleryItem:galleryItems) {
                galleryItem.setDimension(0,y,w,h);
                y += h;
            }
            float indX = 4*w/5,indGap = Math.min(w,h)/20,indY = h/2 - (galleryItems.size()/2)*indGap;
            for(int i=0;i<galleryItems.size();i++) {
                Indicator indicator = new Indicator(indX,indY,indGap);
                indicators.add(indicator);
                y += 2*indGap;
                if(i == 0) {
                    indicator.setSelected(true);
                }
            }
        }
        canvas.save();
        canvas.translate(0,screen.y);
        for(GalleryItem galleryItem:galleryItems) {
            galleryItem.draw(canvas,paint);
        }
        canvas.restore();
        for(Indicator indicator:indicators) {
            indicator.draw(canvas,paint);
        }
        time++;
    }
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
    private class Screen {
        private float y = 0;
    }
}
