package com.jacosro.dim.common;

import android.graphics.Paint;
import android.graphics.Point;

public class Line {

    private Point start;
    private Point end;
    private boolean remove;
    private Paint paint;


    public Line() {
        this.paint = Paints.getRandomColorPaint();
    }

    public Line(int startX, int startY, int endX, int endY) {
        this();

        start = new Point(startX, startY);
        end = new Point(endX, endY);
    }

    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    public void clear() {
        setStart(null);
        setEnd(null);
        setRemove(false);
        this.paint = Paints.getRandomColorPaint();
    }

    public boolean isDrawable() {
        return start != null && end != null;
    }

    public boolean isToRemove() {
        return remove;
    }

    public void setRemove(boolean remove) {
        this.remove = remove;

        if (remove) {
            this.paint = Paints.getErasePaint();
        }
    }

    public Paint getPaint() {
        return paint;
    }
}
