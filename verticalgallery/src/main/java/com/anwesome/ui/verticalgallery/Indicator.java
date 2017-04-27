package com.anwesome.ui.verticalgallery;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by anweshmishra on 27/04/17.
 */
public class Indicator {
    private float x,y,r;
    private boolean selected = false;
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Indicator(float x,float y,float r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }
    public void draw(Canvas canvas, Paint paint) {
        if(!selected) {
            paint.setColor(Color.parseColor("#AABDBDBD"));
        }
        else {
            paint.setColor(Color.WHITE);
        }
        canvas.drawCircle(x,y,r,paint);
    }
}
