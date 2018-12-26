package com.jacosro.dim.exercise5;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import com.jacosro.dim.common.Paints;

import java.util.ArrayList;
import java.util.List;

public class Exercise5 extends View {

    private static final String TAG = "Exercise5";

    private ScaleGestureDetector scaleGestureDetector;
    private GestureDetector gestureDetector;
    private Paint paint;
    private List<Rect> rects;
    private int rectSize = 100;

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
                if (scaleGestureDetector.isInProgress())
                    return false;

                for (Rect rect : rects) {
                    if (rect.contains((int) e2.getX(), (int) e2.getY())
                            || rect.contains((int) e1.getX(), (int) e1.getY())) {

                        rect.offset((int) distanceX * -1, (int) distanceY * -1);

                        break;
                    }
                }

                invalidate();

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

                int left = center.x - rectSize;
                int top = center.y - rectSize;
                int right = center.x + rectSize;
                int bottom = center.y + rectSize;

                Rect newRectangle = new Rect(left, top, right, bottom);

                rects.add(newRectangle);

                invalidate();

                return true;
            }

            @Override
            public boolean onDoubleTapEvent(MotionEvent e) {
                return false;
            }
        };

        this.scaleGestureDetector = new ScaleGestureDetector(getContext(), new ScaleGestureDetector.SimpleOnScaleGestureListener() {

            Rect rect;

            @Override
            public boolean onScaleBegin(ScaleGestureDetector detector) {
                for (Rect rect : rects) {
                    int x = (int) detector.getFocusX();
                    int y = (int) detector.getFocusY();

                    double diagonal = rect.width() * Math.sqrt(2); // Pitágoras

                    if (rect.contains(x, y)) {
//                            && detector.getCurrentSpan() <= diagonal) {

                        this.rect = rect;

                        return true;
                    }
                }

                return false;
            }

            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                int centerX = this.rect.centerX();
                int centerY = this.rect.centerY();

                int side = (int) (this.rect.width() * detector.getScaleFactor());
                int distanceFromCenter = side / 2;

                int left = centerX - distanceFromCenter;
                int top = centerY - distanceFromCenter;
                int right = centerX + distanceFromCenter;
                int bottom = centerY + distanceFromCenter;

                this.rect.set(
                        left,
                        top,
                        right,
                        bottom
                );

                invalidate();

                return true;
            }
        });

        this.gestureDetector = new GestureDetector(getContext(), gestureListener);
        this.gestureDetector.setOnDoubleTapListener(doubleTapListener);

        this.paint = Paints.getPaintWithColor(Color.BLUE);

        this.rects = new ArrayList<>();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean retVal = scaleGestureDetector.onTouchEvent(event);
        retVal = gestureDetector.onTouchEvent(event) || retVal;
        return retVal || super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (Rect rect : rects) {
            canvas.drawRect(rect, paint);
        }
    }


}
