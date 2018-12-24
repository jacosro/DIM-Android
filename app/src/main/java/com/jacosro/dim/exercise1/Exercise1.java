package com.jacosro.dim.exercise1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.jacosro.dim.common.Line;
import com.jacosro.dim.common.Paints;

import java.util.Random;

public class Exercise1 extends View {

    private Line line;

    public Exercise1(Context context) {
        super(context);

        init();
    }

    public Exercise1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public Exercise1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        line = new Line();
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            line.setStart(new Point((int) event.getX(), (int) event.getY()));

        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            line.setEnd(new Point((int) event.getX(), (int) event.getY()));

        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            line.setRemove(true);
        }

        invalidate();

        return true;
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (line.isDrawable()) {
            canvas.drawLine(
                    line.getStart().x, line.getStart().y,
                    line.getEnd().x, line.getEnd().y,
                    line.getPaint()
            );

            if (line.isToRemove()) {
                line.clear();
            }
        }
    }

}
