package com.example.flameprostaff;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DonutChartView extends View {

    private Paint paint;
    private Paint textPaint;
    private RectF rectF;
    private int[] colors;
    private float[] angles;

    public DonutChartView(Context context) {
        super(context);
        init();
    }

    public DonutChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DonutChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextAlign(Paint.Align.CENTER);

        rectF = new RectF();

        colors = new int[]{
                Color.parseColor("#D32F2F"), // Red (Top Right)
                Color.parseColor("#F57C00"), // Orange (Bottom Right)
                Color.parseColor("#FFB300"), // Yellow (Bottom Left)
                Color.parseColor("#757575")  // Grey (Top Left)
        };

        angles = new float[]{-45f, 45f, 135f, -135f};
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        int size = Math.min(width, height);

        float padding = size * 0.05f;
        rectF.set(padding, padding, size - padding, size - padding);

        float centerX = size / 2f;
        float centerY = size / 2f;

        float sweepAngle = 88f;

        for (int i = 0; i < 4; i++) {
            paint.setColor(colors[i]);
            canvas.drawArc(rectF, -90f + (i * 90f) + 1f, sweepAngle, true, paint);
        }

        // Draw center white circle
        float innerRadius = size * 0.33f;
        paint.setColor(Color.WHITE);
        canvas.drawCircle(centerX, centerY, innerRadius, paint);

        // Draw "25%" text on each arc segment
        float textRadius = size * 0.39f;
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(size * 0.075f);
        textPaint.setFakeBoldText(true);

        for (float angle : angles) {
            double rad = Math.toRadians(angle);
            float tx = (float) (centerX + textRadius * Math.cos(rad));
            float ty = (float) (centerY + textRadius * Math.sin(rad) + (textPaint.getTextSize() / 3f));
            canvas.drawText("25%", tx, ty, textPaint);
        }

        // Draw center text: "4" and "Total Orders"
        textPaint.setColor(Color.parseColor("#111111"));
        textPaint.setTextSize(size * 0.18f);
        textPaint.setFakeBoldText(true);
        canvas.drawText("4", centerX, centerY - size * 0.02f, textPaint);

        textPaint.setColor(Color.parseColor("#777777"));
        textPaint.setTextSize(size * 0.065f);
        textPaint.setFakeBoldText(false);
        canvas.drawText("Total Orders", centerX, centerY + size * 0.11f, textPaint);
    }
}