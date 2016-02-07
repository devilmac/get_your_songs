package com.colantoni.federico.projects.networkmodule.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.squareup.picasso.Transformation;

public class BorderedCircleTransform implements Transformation {

    private int x;

    private int y;

    private int border_color;

    public BorderedCircleTransform(int border_color) {

        this.border_color = border_color;
    }

    @Override
    public Bitmap transform(Bitmap source) {

        int size = Math.min(source.getWidth(), source.getHeight());

        x = (source.getWidth() - size) / 2;
        y = (source.getHeight() - size) / 2;

        Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
        if (squaredBitmap != source) {
            source.recycle();
        }

        Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        BitmapShader shader = new BitmapShader(squaredBitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setAntiAlias(true);

        float r = size / 2f;

        // Prepare the background
        Paint paintBg = new Paint();
        paintBg.setStyle(Paint.Style.STROKE);
        paintBg.setColor(border_color);
        paintBg.setAntiAlias(true);

        // Draw the background circle
        canvas.drawCircle(r, r, r, paintBg);

        // Draw the image smaller than the background so a little border will be seen
        int BORDER_RADIUS = 5;
        canvas.drawCircle(r, r, r - BORDER_RADIUS, paint);

        squaredBitmap.recycle();
        return bitmap;
    }

    @Override
    public String key() {

        return "circle(x=" + x + ",y=" + y + ")";
    }
}
