package com.demo.wondersdaili.mvp.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.demo.wondersdaili.mvp.R;
import com.demo.wondersdaili.mvp.utils.CommonUtil;
import com.demo.wondersdaili.mvp.utils.ImageUtils;

/**
 * Created by daili on 2017/5/17.
 */

public class ShakeMaps extends View {

    private final float mWeaLineWidth = 1.5f;
    private final int mWeaTextColor = Color.BLACK;
    private Paint mDotPaint;
    private Paint mLinePaint;
    private TextPaint mTextPaint;
    private Paint mBitMapPaint;
    private float mTemperTextSize = 40;
    private Paint.FontMetrics mTextFontMetrics;
    private int mTextDotDistance = 20;
    private int mCycleRadius = 10;
    private float mDiffValue;
    private float[] mHighTemp = new float[3];
    private float[] mLowTemp = new float[3];
    private float mHigh;
    private float mLow;
    private Bitmap mBitmap1;
    private Bitmap mBitmap2;
    private float defaultMinWidth = 50;
    private float defaultMinHeight = 80;


    public ShakeMaps(Context context) {
        this(context, null);
    }

    public ShakeMaps(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShakeMaps(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width = getSize(widthMode, widthSize, 0);
        int height = getSize(heightMode, heightSize, 1);
        setMeasuredDimension(width, height);
    }

    private int getSize(int mode, int size, int type) {
        // 默认
        int result;
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            if (type == 0) {
                // 最小不能低于最小的宽度
                result = CommonUtil.dp2px(defaultMinWidth, getContext()) + getPaddingLeft() + getPaddingRight();
            } else {
                // 最小不能小于最小的宽度加上一些数据
                int textHeight = (int) (mTextFontMetrics.bottom - mTextFontMetrics.top);
                // 加上2个文字的高度
                result = CommonUtil.dp2px(defaultMinHeight, getContext()) + 2 * textHeight +
                        // 需要加上两个文字和圆点的间距
                        getPaddingTop() + getPaddingBottom() + 2 * mTextDotDistance;
            }
            if (mode == MeasureSpec.AT_MOST) {
                result = Math.min(result, size);
            }
        }
        return result;
    }

    private void initData() {
        mBitmap1 = BitmapFactory.decodeResource(getResources(), R.mipmap.unkonw);
        mBitmap2 = BitmapFactory.decodeResource(getResources(), R.mipmap.unkonw);
        mDiffValue = 20;
    }

    private void init() {
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(mTemperTextSize);
        mTextPaint.setColor(mWeaTextColor);
        mTextFontMetrics = mTextPaint.getFontMetrics();

        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setStrokeWidth(mWeaLineWidth);
        mLinePaint.setColor(mWeaTextColor);

        mDotPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDotPaint.setStyle(Paint.Style.FILL);
        mDotPaint.setColor(mWeaTextColor);

        mBitMapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    public void setData(float[] highTemp, float[] lowTemp, int high, int low) {
        //温度最高值和最低温
        mHigh = high;
        mLow = low;
        mHighTemp = highTemp;
        mLowTemp = lowTemp;
        mDiffValue = mHigh - mLow;
        postInvalidate();
    }

    public void setBitmap(Drawable drawable1, Drawable drawable2) {
        BitmapDrawable bd1 = (BitmapDrawable) drawable1;
        mBitmap1 = bd1.getBitmap();

        BitmapDrawable bd2 = (BitmapDrawable) drawable2;
        mBitmap2 = bd2.getBitmap();
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float ratio = (getHeight() * 2) / (mDiffValue * 5);
        float top = (getHeight() * 3) / 10;
        float leftHighTemp = ((mHigh - (mHighTemp[0] + mHighTemp[1]) / 2) * ratio) + top;
        float middleHighTemp = ((mHigh - mHighTemp[1]) * ratio) + top;
        float rightHighTemp = ((mHigh - (mHighTemp[1] + mHighTemp[2]) / 2) * ratio) + top;
        float leftLowTemp = ((mHigh - (mLowTemp[0] + mLowTemp[1]) / 2) * ratio) + top;
        float middleLowTemp = ((mHigh - mLowTemp[1]) * ratio) + top;
        float rightLowTemp = ((mHigh - (mLowTemp[1] + mLowTemp[2]) / 2) * ratio) + top;
        //折线,-100表示没有前一天数据,100表示没有后一天数据
        if (mHighTemp[0] != 100) {
            canvas.drawLine(0, leftHighTemp, getWidth() / 2, middleHighTemp, mLinePaint);
        }
        if (mLowTemp[2] != -100) {
            canvas.drawLine(getWidth() / 2, middleHighTemp, getWidth(), rightHighTemp, mLinePaint);
        }
        //圆点
        canvas.drawCircle(getWidth() / 2, middleHighTemp, mCycleRadius, mDotPaint);
        //文字
        String textHigh = (int) mHighTemp[1] + "°";
        float baseHighX = (canvas.getWidth() / 2 - mTextPaint.measureText(textHigh) / 2);
        float baseHighY = (middleHighTemp - mTextFontMetrics.bottom - mTextDotDistance);
        canvas.drawText(textHigh, baseHighX, baseHighY, mTextPaint);

        //折线
        if (mHighTemp[0] != 100) {
            canvas.drawLine(0, leftLowTemp, getWidth() / 2, middleLowTemp, mLinePaint);
        }
        if (mLowTemp[2] != -100) {
            canvas.drawLine(getWidth() / 2, middleLowTemp, getWidth(), rightLowTemp, mLinePaint);
        }
        //圆点
        canvas.drawCircle(getWidth() / 2, middleLowTemp, mCycleRadius, mDotPaint);
        //文字
        String textLow = (int) mLowTemp[1] + "°";
        float baseLowX = (canvas.getWidth() / 2 - mTextPaint.measureText(textLow) / 2);
        float baseLowY = (middleLowTemp - mTextFontMetrics.top + mTextDotDistance);
        canvas.drawText(textLow, baseLowX, baseLowY, mTextPaint);

        //天气图标上午
        mBitmap1 = ImageUtils.resizeImage(mBitmap1, (int) (getMeasuredHeight() * 0.15),
                (int) (getMeasuredHeight() * 0.15));
        canvas.drawBitmap(mBitmap1, (getWidth() - mBitmap1.getWidth()) / 2, 0, mBitMapPaint);
        //天气图标下午
        mBitmap2 = ImageUtils.resizeImage(mBitmap2, (int) (getMeasuredHeight() * 0.15),
                (int) (getMeasuredHeight() * 0.15));
        canvas.drawBitmap(mBitmap2, (getWidth() - mBitmap2.getWidth()) / 2, getHeight() - mBitmap2.getHeight(), mBitMapPaint);
    }
}
