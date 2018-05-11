package com.zmsoft.widget.invoiceView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.zmsoft.ui.R;

import org.jetbrains.annotations.Nullable;


/**
 * 发票状态
 * Created by kafei on 2017/7/4.
 */

public class InvoiceStatusView extends View {

    private float circleRadius;

    private float textSize;

    private String text;

    private int textColor;

    private int circleColor;

    public InvoiceStatusView(Context context) {
        super(context);
        init();
    }

    public InvoiceStatusView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public InvoiceStatusView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        textColor = R.color.red;
        circleColor = R.color.red;
        circleRadius = dp2px(75);
        textSize = dp2px(25);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
        Rect rect = new Rect(0, 0, getWidth(), getHeight());
        Paint circlePaint = new Paint();
        Paint textPaint = new Paint();
        circlePaint.setColor(getResources().getColor(circleColor));
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeWidth(2);
        circlePaint.setAntiAlias(true);

        textPaint.setColor(getResources().getColor(textColor));
        textPaint.setTextSize(textSize);
        textPaint.setAntiAlias(true);
        textPaint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float top = fontMetrics.top;
        float bottom = fontMetrics.bottom;
        int baseLine = (int) (rect.centerY() - top / 2 - bottom / 2);
        int center = getWidth() / 2;
        canvas.drawCircle(center, center, circleRadius, circlePaint);
        canvas.save();
        canvas.rotate(-20, center, center);
        canvas.drawText(text, rect.centerX(), baseLine, textPaint);
        super.onDraw(canvas);
    }

    private int dp2px(float dpValue) {
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public void setCircleColor(int circleColor) {
        this.circleColor = circleColor;
    }
}
