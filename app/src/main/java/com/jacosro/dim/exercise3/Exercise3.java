package com.jacosro.dim.exercise3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.jacosro.dim.common.Paints;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Exercise3 extends View {

    private Map<Integer, Finger> fingers;
    private Paint erasePaint;

    public Exercise3(Context context) {
        super(context);

        init();
    }

    public Exercise3(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public Exercise3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }


    private void init() {
        this.fingers = new ConcurrentHashMap<>();
        this.erasePaint = Paints.getErasePaint();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int index = event.getActionIndex();
        int id = event.getPointerId(index);

        if (event.getAction() == MotionEvent.ACTION_DOWN
                || event.getActionMasked() == MotionEvent.ACTION_POINTER_DOWN) {

            fingers.put(id, new Finger(id));

        } else if (event.getAction() == MotionEvent.ACTION_UP
                || event.getActionMasked() == MotionEvent.ACTION_POINTER_UP) {

            Finger finger = fingers.get(id);

            if (finger != null) { // finger should never be null
                finger.setToRemove();
            }
        }

        if (event.getActionMasked() == MotionEvent.ACTION_MOVE) {
            int pointers = event.getPointerCount();

            for (int i = 0; i < pointers; i++) {
                Finger finger = fingers.get(event.getPointerId(i));

                if (finger != null) { // finger should never be null
                    finger.addPoint(new Point((int) event.getX(i), (int) event.getY(i)));
                }
            }
        }

        invalidate();

        return true;
    }

    @Override
    public void onDraw(Canvas canvas) {
        for (Finger finger : fingers.values()) {
            List<Point> fingerPoints = finger.getPoints();

            int numPoints = fingerPoints.size();

            for (int i = 0; i < numPoints - 2; i++) {
                Point from = fingerPoints.get(i);
                Point to = fingerPoints.get(i + 1);
                
                canvas.drawLine(
                        from.x, from.y,
                        to.x, to.y,
                        finger.getPaint()
                );
            }

            if (finger.isToRemove()) {
                fingers.remove(finger.getId());
            }
        }
    }
}
