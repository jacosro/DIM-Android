package com.jacosro.dim.exercise5;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.jacosro.dim.common.Paints;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Exercise5 extends View {

    private GestureDetector gestureDetector;
    private Paint paint;
    private Map<Point, Rect> pointRectMap;

    public Exercise5(Context context) {
        super(context);

        init();
    }

    public Exercise5(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public Exercise5(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        GestureDetector.OnGestureListener gestureListener = new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                Point center = new Point((int) e1.getX(), (int) e1.getY());
                Point newCenter = new Point((int) e2.getX(), (int) e2.getY());

                Rect rect = pointRectMap.get(center);

                if (rect == null)  // Should not happen
                    return true;

                rect.bottom += (newCenter.y - center.y);
                rect.left += (newCenter.x - center.x);
                rect.top += (newCenter.y - center.y);
                rect.right += (newCenter.x - center.x);

                pointRectMap.remove(center);
                pointRectMap.put(newCenter, rect);

                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return true;
            }
        };


        GestureDetector.OnDoubleTapListener doubleTapListener = new GestureDetector.OnDoubleTapListener() {
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                Point center = new Point((int) e.getX(), (int) e.getY());

                int left = center.x - 30;
                int top = center.y - 30;
                int right = center.x + 30;
                int bottom = center.y + 30;

                Rect newRectangle = new Rect(left, top, right, bottom);

                pointRectMap.put(center, newRectangle);

                invalidate();

                return true;
            }

            @Override
            public boolean onDoubleTapEvent(MotionEvent e) {
                return false;
            }
        };

        this.gestureDetector = new GestureDetector(getContext(), gestureListener);
        this.gestureDetector.setOnDoubleTapListener(doubleTapListener);

        this.paint = Paints.getPaintWithColor(Color.BLUE);

        this.pointRectMap = new HashMap<>();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.gestureDetector.onTouchEvent(event)) {
            return true;
        }

        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (Rect rect : pointRectMap.values()) {
            canvas.drawRect(rect, paint);
        }
    }


}
