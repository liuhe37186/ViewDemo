package txcap.com.viewdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

/**
 * 文件名:txcap.com.viewdemo.view.CustomPieView
 * 描 述:
 * 作 者:liuhe
 * 时 间:2017-09-29 20:11
 */

public class CustomPieView extends View {

    private int mWidth, mHeight;
    private int mStartAngle = 0;
    private ArrayList<PieData> mData = new ArrayList<>();
    Paint mPaint = new Paint();
    // 颜色表
    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};

    public CustomPieView(Context context) {
        this(context, null);
    }

    public CustomPieView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
//        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
//        mPaint.setStrokeWidth(10f);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);
        float r = (float) (Math.min(mWidth, mHeight) / 2 * 0.8);
        RectF rectF = new RectF(-r, -r, r, r);
        float currentStartAngle = mStartAngle;
        for (PieData pieData : mData) {
            mPaint.setColor(pieData.getColor());
            canvas.drawArc(rectF, currentStartAngle, pieData.getAngle(), true, mPaint);
            Log.e("xxx","currentStartAngle"+currentStartAngle+"getAngle:"+pieData.getAngle());
            currentStartAngle += pieData.getAngle();
        }
    }


    public void setStartAngle(int mStartAngle) {
        this.mStartAngle = mStartAngle;
        invalidate();
    }

    public void setData(ArrayList<PieData> mData) {
        this.mData = mData;
        initData(mData);
        invalidate();
    }

    private void initData(ArrayList<PieData> mData) {
        if (mData == null || mData.size() == 0) {
            return;
        }

        float sumValue = 0;

        for (int i = 0; i < mData.size(); i++) {
            PieData pieData = mData.get(i);
            sumValue += pieData.getValue();
            int j = i % mColors.length;
            pieData.setColor(mColors[j]);
        }

        float sumAngle = 0;
        for (PieData pieData : mData) {
            float percentage = pieData.getValue() / sumValue;
            float angle = percentage * 360;

            pieData.setPercentage(percentage);
            pieData.setAngle(angle);

            sumAngle += angle;
        }
    }
}
