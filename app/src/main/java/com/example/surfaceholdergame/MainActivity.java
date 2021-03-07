package com.example.surfaceholdergame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    DrawView drawView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        drawView = new DrawView(this, null);
        setContentView(drawView);
        drawView.setOnTouchListener(this);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        int curX = drawView.drawThread.curX;
        int curY = drawView.drawThread.curY;
        int circleRadius = drawView.drawThread.circleRadius;
        if (curX-circleRadius <= x && x <= curX+circleRadius && curY-circleRadius <= y && y <= curY+circleRadius){
            drawView.drawThread.score++;
            drawView.drawThread.isTouched = true;
        } else {
            drawView.drawThread.maxScore = Math.max(drawView.drawThread.score, drawView.drawThread.maxScore);
            drawView.drawThread.score = 0;
        }
        return false;
    }
}