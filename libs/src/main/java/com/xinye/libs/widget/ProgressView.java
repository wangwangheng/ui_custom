package com.xinye.libs.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.xinye.libs.R;

/**
 *
 * @author wangheng
 */
public class ProgressView extends View {

    /**
     * 最大进度
     **/
    private int maxProgress;

    /**
     * 当前进度
     **/
    private int progress;

    /**
     * 当前显示的文字
     **/
    private String text;

    /**
     * 进度和没有交叉的时候的文字的颜色
     **/
    private int backgroundColor;

    /**
     * 文字和进度交叉的时候的文字的颜色
     **/
    private int progressColor;

    /**
     * 画进度和没有交叉的时候的文字的Paint
     **/
    private Paint backgroundPaint;

    /** 文字和进度交叉的Paint **/
    private Paint progressPaint;

    /**
     * 表示进度的Rect
     **/
    private Rect rect;

    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ProgressView(Context context) {
        super(context);
        init(context, null);
    }

    private void init(Context context, AttributeSet attrs) {
        /** 得到XML属性 **/
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ProgressView);

        maxProgress = ta.getInt(R.styleable.ProgressView_maxProgress, 100);
        progress = ta.getInt(R.styleable.ProgressView_progress, 0);
        text = ta.getString(R.styleable.ProgressView_text);
        backgroundColor = ta.getColor(R.styleable.ProgressView_backgroundColor, Color.WHITE);
        progressColor = ta.getColor(R.styleable.ProgressView_progressColor, Color.GREEN);
        float textSize = ta.getDimensionPixelOffset(R.styleable.ProgressView_textSize, 20);
        ta.recycle();

        /** 设置默认的Paint属性 **/
        backgroundPaint = new Paint();
        backgroundPaint.setAntiAlias(true);
        backgroundPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        backgroundPaint.setStyle(Paint.Style.FILL);
        backgroundPaint.setTextSize(textSize);
        backgroundPaint.setColor(backgroundColor);

        progressPaint = new Paint(backgroundPaint);
        progressPaint.setColor(progressColor);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        /** 背景 **/
        canvas.drawColor(backgroundColor);


        /** 得到画文字的左上角顶点 **/
        int offsetX = (int) ((getWidth() - text.length() * backgroundPaint.getTextSize()) / 2);
        int offsetY = (int) ((getHeight() - backgroundPaint.getTextSize()) / 2);

        /** 画默认文字 **/
        canvas.drawText(text, offsetX, offsetY, progressPaint);

        /** 画进度 **/
        if (rect == null) {
            rect = new Rect();
            rect.left = 0;
            rect.top = 0;
            rect.bottom = getHeight();
        }
        rect.right = (int) (getWidth() * progress / (float) maxProgress);
        canvas.drawRect(rect, progressPaint);

        /** 画交叉的时候的文字 **/
        canvas.save();

        canvas.clipRect(rect);
        canvas.drawText(text, offsetX, offsetY, backgroundPaint);

        canvas.restore();
    }

    /**
     * 设置最大进度
     *
     * @return
     */
    public int getMaxProgress() {
        return maxProgress;
    }

    /**
     * 得到最大进度
     *
     * @param maxProgress
     */
    public void setMaxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
        invalidate();
    }

    /**
     * 得到当前进度
     *
     * @return
     */
    public int getProgress() {
        return progress;
    }

    /**
     * 设置当前进度
     *
     * @param progress
     */
    public void setProgress(int progress) {
        this.progress = progress;
        invalidate();
    }

    /**
     * 得到显示的文字
     *
     * @return
     */
    public String getText() {
        return text;
    }

    /**
     * 设置显示的文字
     *
     * @param text
     */
    public void setText(String text) {
        this.text = text;
        invalidate();
    }

    /***
     * 设置提示文字的大小
     *
     * @param textSize
     */
    public void setTextSize(int textSize) {
        backgroundPaint.setTextSize(textSize);
        progressPaint.setTextSize(textSize);
        invalidate();
    }

    /***
     * 设置进度和没有交叉的时候的文字的颜色
     *
     * @param backgroundColor
     */
    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        backgroundPaint.setColor(backgroundColor);
        invalidate();
    }

    /**
     * 设置进度和文字交叉之后的文字颜色
     *
     * @param progressColor
     */
    public void setProgressColor(int progressColor) {
        this.progressColor = progressColor;
        progressPaint.setColor(progressColor);
        invalidate();
    }

}