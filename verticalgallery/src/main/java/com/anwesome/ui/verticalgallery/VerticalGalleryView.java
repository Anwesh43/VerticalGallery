package com.anwesome.ui.verticalgallery;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.GestureDetector;
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
    private int time = 0,index = 0;
    private Screen screen = new Screen();
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private AnimationHandler animationHandler = new AnimationHandler();
    private GestureDetector gestureDetector;
    public VerticalGalleryView(Context context,List<GalleryItem> galleryItems) {
        super(context);
        this.galleryItems = galleryItems;
        this.gestureDetector = new GestureDetector(context,new ScreenGestureListener());
    }
    public void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        int w = canvas.getWidth();
        h = canvas.getHeight();
        if(time == 0) {
            float y = h/10;
            for(GalleryItem galleryItem:galleryItems) {
                galleryItem.setDimension(w/8,y,w,h);
                y += h;
            }
            float indX = 17*w/20,indGap = Math.min(w,h)/60,indY = h/2 - (galleryItems.size()/2)*indGap;
            for(int i=0;i<galleryItems.size();i++) {
                Indicator indicator = new Indicator(indX+w/30,indY,indGap);
                indicators.add(indicator);
                indY += 3*indGap;
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
        animationHandler.animate();
    }
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
    private class Screen {
        private float y = 0;
    }
    private class ScreenGestureListener extends GestureDetector.SimpleOnGestureListener {
        public boolean onDown(MotionEvent event) {
            return true;
        }
        public boolean onSingleTapUp(MotionEvent event) {
            for(GalleryItem galleryItem:galleryItems) {
                if(galleryItem.handleTap(event.getX(),event.getY()-screen.y)) {
                    break;
                }
            }
            return true;
        }
        public boolean onFling(MotionEvent e1,MotionEvent e2,float velx,float vely) {
            if(vely != 0) {
                int dir = (int)(vely/Math.abs(vely));
                animationHandler.startAnimating(-dir);
            }
            return true;
        }
    }
    private class AnimationHandler {
        private boolean isAnimated = false;
        private int dir = 0;
        private float prevY = 0;
        public void animate() {
            screen.y-=dir*h/5;
            if(Math.abs(screen.y-prevY)>h) {
                if(index<indicators.size()) {
                    indicators.get(index).setSelected(false);
                }
                index+=dir;
                if(index>= 0 && index<indicators.size()) {
                    indicators.get(index).setSelected(true);
                }
                screen.y = prevY-dir*h;
                dir = 0;
                prevY = screen.y;

            }
            try {
                Thread.sleep(50);
                invalidate();
            }
            catch (Exception ex) {

            }
        }
        public void startAnimating(int dir) {
            if(!isAnimated && this.dir == 0) {
                if((dir == -1 && index>0) || (dir == 1 && index<indicators.size()-1)) {
                    this.dir = dir;
                    postInvalidate();
                }
            }
        }
    }
}
