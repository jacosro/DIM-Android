package com.jacosro.dim.exercise3;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Pair;

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

        Random rnd = new Random();

        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.paint.setStrokeWidth(6f);
        this.paint.setARGB(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeJoin(Paint.Join.ROUND);
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
    }
}
