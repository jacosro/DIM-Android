package com.jacosro.dim.exercise4;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.jacosro.dim.common.Paints;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Exercise4 extends View {

    private Map<Integer, Point> points;

    public Exercise4(Context context) {
        super(context);

        init();
    }

    public Exercise4(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public Exercise4(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        points = new HashMap<>();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int index = event.getActionIndex();
        int id = event.getPointerId(index);

        if (event.getAction() == MotionEvent.ACTION_DOWN
                || event.getActionMasked() == MotionEvent.ACTION_POINTER_DOWN) {

            points.put(id, new Point((int) event.getX(index), (int) event.getY(index)));

        } else if (event.getAction() == MotionEvent.ACTION_UP
                || event.getActionMasked() == MotionEvent.ACTION_POINTER_UP) {

            points.remove(id);
        }

        if (event.getActionMasked() == MotionEvent.ACTION_MOVE) {
            int pointers = event.getPointerCount();

            for (int i = 0; i < pointers; i++) {
                Point point = points.get(event.getPointerId(i));

                if (point != null) { // should never be null
                    point.set((int) event.getX(i), (int) event.getY(i));
                }
            }
        }

        invalidate();

        return true;
    }

    @SuppressLint("DrawAllocation")
    @Override
    public void onDraw(Canvas canvas) {
        if (points.size() != 3)
            return;

        Iterator<Point> iterator = points.values().iterator();

        Point pointA = iterator.next();
        Point pointB = iterator.next();
        Point pointC = iterator.next();

        double distanceAB = Math.sqrt(
                Math.pow(pointA.x - pointB.x, 2) +
                Math.pow(pointA.y - pointB.y, 2)
        );

        double distanceAC = Math.sqrt(
                Math.pow(pointA.x - pointC.x, 2) +
                Math.pow(pointA.y - pointC.y, 2)
        );

        double distanceBC = Math.sqrt(
                Math.pow(pointB.x - pointC.x, 2) +
                Math.pow(pointB.y - pointC.y, 2)
        );

        Point middle;
        Point top;

        int delta = 10;

        if (Math.abs(distanceAB - distanceBC) < delta && distanceBC != distanceAC ) {
            middle = new Point(
                    (pointA.x + pointC.x) / 2,
                    (pointA.y + pointC.y) / 2
            );

            top = new Point(
                    pointB.x + (pointB.x - middle.x),
                    pointB.y + (pointB.y - middle.y)
            );

        } else if (Math.abs(distanceAB - distanceAC) < delta && distanceAB != distanceBC) {
            middle = new Point(
                    (pointB.x + pointC.x) / 2,
                    (pointB.y + pointC.y) / 2
            );

            top = new Point(
                    pointA.x + (pointA.x - middle.x),
                    pointA.y + (pointA.y - middle.y)
            );

        } else if (Math.abs(distanceAC - distanceBC) < delta && distanceAC != distanceAB) {
            middle = new Point(
                    (pointA.x + pointB.x) / 2,
                    (pointA.y + pointB.y) / 2
            );

            top = new Point(
                    pointC.x + (pointC.x - middle.x),
                    pointC.y + (pointC.y - middle.y)
            );

        } else {
            return;
        }

        canvas.drawLine(pointA.x, pointA.y, pointB.x, pointB.y, Paints.getPaintWithColor(Color.BLUE));
        canvas.drawLine(pointA.x, pointA.y, pointC.x, pointC.y, Paints.getPaintWithColor(Color.BLUE));
        canvas.drawLine(pointC.x, pointC.y, pointB.x, pointB.y, Paints.getPaintWithColor(Color.BLUE));
        canvas.drawLine(middle.x, middle.y, top.x, top.y, Paints.getPaintWithColor(Color.RED));
    }
}
