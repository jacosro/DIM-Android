package com.jacosro.dim.common;

import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

public class Paints {

    private static Paint basePaint() {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(6f);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);

        return paint;
    }

    public static Paint getRandomColorPaint() {
        Random random = new Random();
        Paint paint = basePaint();
        paint.setARGB(random.nextInt(), random.nextInt(), random.nextInt(), random.nextInt());

        return paint;
    }

    public static Paint getPaintWithColor(int color) {
        Paint paint = basePaint();
        paint.setColor(color);

        return paint;
    }

    public static Paint getErasePaint() {
        return getPaintWithColor(Color.TRANSPARENT);
    }


}
