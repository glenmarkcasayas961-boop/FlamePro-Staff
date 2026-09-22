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
    private Paint linePaint;
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

        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(Color.WHITE);
        linePaint.setStyle(Paint.Style.STROKE);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextAlign(Paint.Align.CENTER);

        rectF = new RectF();

        // Exact colors matching screenshot: Red, Orange, Amber, Slate Grey
        colors = new int[]{
                Color.parseColor("#D0021B"), // Top Right Red
                Color.parseColor("#F56100"), // Bottom Right Orange
                Color.parseColor("#FFA726"), // Bottom Left Amber
                Color.parseColor("#60656F")  // Top Left Grey
        };

        // Angles for 25% label placement in 4 quadrants
        angles = new float[]{-45f, 45f, 135f, -135f};
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        int size = Math.min(width, height);

        float padding = size * 0.02f;
        rectF.set(padding, padding, size - padding, size - padding);

        float centerX = size / 2f;
        float centerY = size / 2f;
        float outerRadius = (size - 2 * padding) / 2f;

        // Draw 4 full 90-degree arcs
        paint.setColor(colors[0]);
        canvas.drawArc(rectF, -90f, 90f, true, paint);
        paint.setColor(colors[1]);
        canvas.drawArc(rectF, 0f, 90f, true, paint);
        paint.setColor(colors[2]);
        canvas.drawArc(rectF, 90f, 90f, true, paint);
        paint.setColor(colors[3]);
        canvas.drawArc(rectF, 180f, 90f, true, paint);

        // Draw white separator lines along horizontal and vertical axes
        linePaint.setStrokeWidth(size * 0.015f);
        canvas.drawLine(centerX, centerY - outerRadius, centerX, centerY + outerRadius, linePaint);
        canvas.drawLine(centerX - outerRadius, centerY, centerX + outerRadius, centerY, linePaint);

        // Draw center white circle
        float innerRadius = size * 0.28f;
        paint.setColor(Color.WHITE);
        canvas.drawCircle(centerX, centerY, innerRadius, paint);

        // Draw "25%" text inside each arc ring
        float textRadius = (outerRadius + innerRadius) / 2f;
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(size * 0.075f);
        textPaint.setFakeBoldText(true);

        for (float angle : angles) {
            double rad = Math.toRadians(angle);
            float tx = (float) (centerX + textRadius * Math.cos(rad));
            float ty = (float) (centerY + textRadius * Math.sin(rad) + (textPaint.getTextSize() / 3f));
            canvas.drawText("25%", tx, ty, textPaint);
        }

        // Draw center text: "4" (bold dark navy) and "Total Orders" (slate grey)
        textPaint.setColor(Color.parseColor("#0F172A"));
        textPaint.setTextSize(size * 0.20f);
        textPaint.setFakeBoldText(true);
        canvas.drawText("4", centerX, centerY - size * 0.01f, textPaint);

        textPaint.setColor(Color.parseColor("#64748B"));
        textPaint.setTextSize(size * 0.065f);
        textPaint.setFakeBoldText(false);
        canvas.drawText("Total Orders", centerX, centerY + size * 0.12f, textPaint);
    }
}