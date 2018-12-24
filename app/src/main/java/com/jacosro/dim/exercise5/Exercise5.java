package com.jacosro.dim.exercise5;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Exercise5 extends View {

    private GestureDetector gestureDetector;
    private Paint paint;
    private List<Rect> rectanglesList;

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
                int left = (int) (e.getX() - 30);
                int top = (int) (e.getY() - 30);
                int right = (int) (e.getX() + 30);
                int bottom = (int) (e.getY() + 30);

                Rect newRectangle = new Rect(left, top, right, bottom);

                rectanglesList.add(newRectangle);

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

        this.rectanglesList = new ArrayList<>();

        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.paint.setStrokeWidth(5f);
        this.paint.setColor(Color.BLACK);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeJoin(Paint.Join.ROUND);
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
        for (Rect rect : rectanglesList) {
            canvas.drawRect(rect, paint);
        }
    }


}
