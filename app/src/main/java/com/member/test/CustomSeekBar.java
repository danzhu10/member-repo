package com.member.test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.SeekBar;


public class CustomSeekBar extends android.support.v7.widget.AppCompatSeekBar {
    /**
     * 文本画笔
     */
    private TextPaint mTextPaint;
    /**
     * 文本
     */
    private String mText;

    private Rect mTextBound;
    private int mWidth;
    private int mHeight;
    public static final int MONEY = 0;
    public static final int WEEK = 1;
    public int type = -1;
    /**
     * 设置金额区间范围
     */
    public int MONEY_MIN = 0;
    public int MONEY_MAX = 0;

    /**
     * 设置周期区间范围
     */
    public int PERIOD_MIN = 0;
    public int PERIOD_MIN_UNIT = 0; // 最小周期单位 1:天,2:周
    public int PERIOD_MAX = 0;
    private Context mContext;
    float x1 = 0;
    float x2 = 0;
    private float offsetX;

    public CustomSeekBar(Context context) {
        this(context, null);
    }

    public CustomSeekBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        setOnSeekBarChangeListener(mOnSeekBarChangeListener);

        init();
    }

    private OnSeekBarChangeListener mOnSeekBarChangeListener = new OnSeekBarChangeListener() {

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        }
    };

    private void init() {
        mTextPaint = new TextPaint(Paint.FAKE_BOLD_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.parseColor("#333333"));
        mTextPaint.setTextSize(sp2px(12, mContext));
        mTextBound = new Rect();

    }

    private int sp2px(float sp, Context context) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP,
                sp,
                context.getResources().getDisplayMetrics());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (type == MONEY) {
            mText = "";
        } else if (type == WEEK) {
            if (PERIOD_MIN_UNIT == 1) {
                if (getProgress() <= 1) {
                    mText = PERIOD_MIN + "天";
                }
            }
            mText = Arithmetic.progressToWeek(getProgress(), PERIOD_MIN, PERIOD_MAX, PERIOD_MIN_UNIT);
        } else {
            mText = "";
        }
        mTextPaint.getTextBounds(mText, 0, mText.length(), mTextBound);
        Rect bounds = this.getProgressDrawable().getBounds();
//        LogUtils.e("矩形:", bounds.left + "---" + bounds.top + "\n" + bounds.right + "---" + bounds.bottom);
//        LogUtils.e("宽度:", bounds.width() + "");
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.mipmap.button_slide, opts);
        opts.inSampleSize = 1;
        opts.inJustDecodeBounds = false;
        Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.button_slide, opts);
        int width = mBitmap.getWidth();

        float xText = (bounds.width() - width / 2) * getProgress() / getMax() + width / 2 - mTextBound.width() / 2 - 15;
        canvas.drawText(mText, xText, mHeight / 2 + mTextBound.height() / 3, mTextPaint);
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getMONEY_MIN() {
        return MONEY_MIN;
    }

    public void setMONEY_MIN(int MONEY_MIN) {
        this.MONEY_MIN = MONEY_MIN;
    }

    public int getMONEY_MAX() {
        return MONEY_MAX;
    }

    public void setMONEY_MAX(int MONEY_MAX) {
        this.MONEY_MAX = MONEY_MAX;
    }

    public int getPERIOD_MIN_UNIT() {
        return PERIOD_MIN_UNIT;
    }

    public void setPERIOD_MIN_UNIT(int PERIOD_MIN_UNIT) {
        this.PERIOD_MIN_UNIT = PERIOD_MIN_UNIT;
    }

    public int getPERIOD_MIN() {
        return PERIOD_MIN;
    }

    public void setPERIOD_MIN(int PERIOD_MIN) {
        this.PERIOD_MIN = PERIOD_MIN;
    }

    public int getPERIOD_MAX() {
        return PERIOD_MAX;
    }

    public void setPERIOD_MAX(int PERIOD_MAX) {
        this.PERIOD_MAX = PERIOD_MAX;
    }
}