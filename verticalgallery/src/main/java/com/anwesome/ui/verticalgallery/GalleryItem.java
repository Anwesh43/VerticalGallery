package com.anwesome.ui.verticalgallery;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
/**
 * Created by anweshmishra on 27/04/17.
 */
public class GalleryItem {
    private Bitmap bitmap;
    private String title;
    private float x = 0,y,w,h; private OnClickListener onClickListener;
    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
    public void setDimension(float x,float y,float w,float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        bitmap = Bitmap.createScaledBitmap(bitmap,(int)(7*w/10),(int)(7*h/10),true);
    }
    public GalleryItem(Bitmap bitmap,String title) {
        this.bitmap = bitmap;
        this.title = title;
    }
    public boolean isVisible(float y) {
        return y+this.y<=0;
    }
    public void draw(Canvas canvas, Paint paint) {
        canvas.save();
        canvas.translate(x,y);
        canvas.save();
        Path path = new Path();
        path.addRoundRect(new RectF(0,0,7*w/10,7*h/10),Math.max(w,h)/10,Math.max(w,h)/10, Path.Direction.CCW);
        canvas.clipPath(path);
        canvas.drawBitmap(bitmap,0,0,paint);
        canvas.restore();
        paint.setTextSize(h/20);
        canvas.drawText(title,7*w/20-paint.measureText(title)/2,3*h/4+paint.getTextSize()/2,paint);
        canvas.restore();
    }
    public boolean handleTap(float x,float y) {
        boolean condition =  x>=this.x && x<=this.x+w && y>=this.y && y<=this.y+h;
        if(condition && onClickListener!=null) {
            onClickListener.onClick();
        }
        return condition;
    }
    public int hashCode() {
        return bitmap.hashCode()+title.hashCode();
    }
}
