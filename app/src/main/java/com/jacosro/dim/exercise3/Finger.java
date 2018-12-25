package com.jacosro.dim.exercise3;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Pair;

import com.jacosro.dim.common.Paints;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Finger {

    private int id;
    private List<Point> points;
    private Paint paint;
    private boolean toRemove;

    public Finger(int id) {
        this.id = id;
        this.points = new ArrayList<>();
        this.paint = Paints.getRandomColorPaint();
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public List<Point> getPoints() {
        return points;
    }

    public Paint getPaint() {
        return paint;
    }

    public int getId() {
        return id;
    }

    public boolean isToRemove() {
        return toRemove;
    }

    public void setToRemove() {
        this.toRemove = true;
        this.paint = Paints.getErasePaint();
    }
}
