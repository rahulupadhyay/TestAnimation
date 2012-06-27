package com.android.animation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.RotateAnimation;

class GraphicsView extends View {
    private static final String QUOTE =
        "I M Android";
    private Bitmap jobs;
    private int jobsXOffset;
    private int jobsYOffset;
    private Animation anim;

    public GraphicsView(Context context) {
        super(context);// create a path
        jobs = BitmapFactory
        .decodeResource(getResources(), R.drawable.i);
jobsXOffset = jobs.getWidth() / 2;
jobsYOffset = jobs.getHeight() / 2;
       /* Path circle = new Path();
        circle.addCircle(centerX, centerY, radius, Direction.CW);

        // set the color and font size
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setTextSize(30);
        paint.setAntiAlias(true);

        // draw the text along the circle
        canvas.drawTextOnPath(QUOTE, circle, 0, 30, paint);
        */
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // creates the animation the first time
        if (anim == null) {
            createAnim(canvas);
        }

        Path circle = new Path();

        int centerX = canvas.getWidth() / 2;
        int centerY = canvas.getHeight() / 2;
        int r = Math.min(centerX, centerY);

        circle.addCircle(centerX, centerY, r, Direction.CW);
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setTextSize(30);
        paint.setAntiAlias(true);
        canvas.drawBitmap(jobs, centerX - jobsXOffset,
                centerY - jobsYOffset, null);
        canvas.drawTextOnPath(QUOTE, circle, 0, 30, paint);
    }
    private void createAnim(Canvas canvas) {
        anim = new RotateAnimation(0, 360, canvas.getWidth() / 2, canvas
                .getHeight() / 2);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        anim.setDuration(10000L);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.setInterpolator(new BounceInterpolator());
        startAnimation(anim);
    }
}
