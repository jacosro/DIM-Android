package com.jacosro.dim.exercise2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.jacosro.dim.common.Line;

import java.util.ArrayList;
import java.util.List;

public class Exercise2 extends View {

    private List<Line> lines;
    private Line currentLine;

    public Exercise2(Context context) {
        super(context);

        init();
    }

    public Exercise2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Exercise2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        currentLine = new Line();
        lines = new ArrayList<>();
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            currentLine.setStart(new Point((int) event.getX(), (int) event.getY()));

        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            currentLine.setEnd(new Point((int) event.getX(), (int) event.getY()));

        } else if(event.getAction() == MotionEvent.ACTION_UP) {
            lines.add(currentLine);
            currentLine = new Line();
        }

        invalidate();

        return true;
    }

    @Override
    public void onDraw(Canvas canvas) {
        for (Line line : lines) {
            canvas.drawLine(
                    line.getStart().x, line.getStart().y,
                    line.getEnd().x, line.getEnd().y,
                    line.getPaint()
            );
        }

        if (currentLine.isDrawable()) {
            canvas.drawLine(
                    currentLine.getStart().x, currentLine.getStart().y,
                    currentLine.getEnd().x, currentLine.getEnd().y,
                    currentLine.getPaint()
            );
        }
    }


}
