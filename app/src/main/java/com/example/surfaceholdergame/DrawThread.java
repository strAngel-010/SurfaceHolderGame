package com.example.surfaceholdergame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;

import java.util.Random;

public class DrawThread extends Thread{
    private SurfaceHolder surfaceHolder;
    Paint p = new Paint();
    Paint backgroundPaint = new Paint();
    Paint textPaint = new Paint();
    int score = 0;
    int maxScore = 0;
    int curX, curY;
    int width, height;
    int circleRadius = 60;
    int counter = 0;
    Canvas canvas;
    boolean isTouched = false;
    private volatile boolean running = true;

    public DrawThread(Context context, SurfaceHolder surfaceHolder){
        this.surfaceHolder = surfaceHolder;
        p.setColor(Color.RED);
        backgroundPaint.setColor(Color.WHITE);
        textPaint.setColor(Color.BLACK);
        p.setStyle(Paint.Style.FILL);
        textPaint.setTextSize(50);
    }

    public void requestStop(){running = false;}

    @Override
    public void run() {
        while (running){
            Random random = new Random();
            random.setSeed(System.currentTimeMillis());
            canvas = surfaceHolder.lockCanvas();
            if (canvas != null){
                try {
                    canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), backgroundPaint);

                    height = canvas.getHeight()-300;
                    width = canvas.getWidth()-150;
                    if (counter == 0 || counter == 40){
                        curX = random.nextInt(width-circleRadius)+circleRadius;
                        curY = random.nextInt(height-(circleRadius+90))+circleRadius+90;
                        counter = 1;
                    }

                    canvas.drawText("Score: "+score, 25, 90, textPaint);
                    canvas.drawText("Max score: "+maxScore, 325, 90, textPaint);
                    canvas.drawCircle(curX, curY, circleRadius, p);
                    counter++;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

}
