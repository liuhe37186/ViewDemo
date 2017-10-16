package txcap.com.viewdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 文件名:txcap.com.viewdemo.view.TrainingView
 * 描 述:
 * 作 者:liuhe
 * 时 间:2017-09-27 15:50
 */

public class TrainingView extends View {
    int mWidth , mHeight;
    Paint paint = new Paint();
    public TrainingView(Context context) {
        super(context);
    }

    public TrainingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint(){
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10f);
//        paint.setStyle(Paint.Style.FILL);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);//画笔的抗锯齿（用于线条等）设置

        /**
         * 图片线条（通用）的抗锯齿需要另外设置：
         canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
         */
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mWidth = w;
        this.mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawPoint(700,700,paint);
//        canvas.drawPoints(new float[]{500,500,500,600,500,700},paint);
//
//        canvas.drawLine(300,300,600,800,paint);
//        canvas.drawLines(new float[]{               // 绘制一组线 每四数字(两个点的坐标)确定一条线
//                100,200,200,200,
//                100,300,200,300
//        },paint);

//        Rect rect = new Rect(200,800,800,1200);
//        canvas.drawRect(rect,paint);
//        RectF rectF = new RectF(200,800,800,1200);
//        canvas.drawRoundRect(rectF,30,30,paint);//圆角半径为30的矩形 rx为距离左边或右边 ry为距离上边或下边
//
//        paint.setColor(Color.BLUE);
//        canvas.drawOval(rectF,paint);
//
//        canvas.drawCircle(500,500,400,paint);  // 绘制一个圆心坐标在(500,500)，半径为400 的圆
        int minLen = Math.min(mHeight,mWidth);
        canvas.translate(minLen / 2, minLen / 2);
        float r = (float) (minLen/2*0.8);
        RectF rectF = new RectF(-r,-r,r,r);
//        paint.setColor(Color.GRAY);
//        canvas.drawRect(rectF,paint);

        paint.setStrokeWidth(30f);
        paint.setColor(Color.BLUE);
//        设置线冒样式，取值有Cap.ROUND(圆形线冒)、Cap.SQUARE(方形线冒)、Paint.Cap.BUTT(无线冒)
        paint.setStrokeCap(Paint.Cap.ROUND);
        /**
         * startAngle  // 开始角度
         * sweepAngle  // 扫过角度
         * useCenter   // 是否使用中心
         */
        canvas.drawArc(rectF,180,180,false,paint);






        canvas.translate(0, minLen / 2);
        RectF rectF2 = new RectF(-r,-r,r,r);
//        RectF rectF2 = new RectF(100,800,700,1400);
        // 绘制背景矩形
        paint.setColor(Color.GRAY);
        canvas.drawRect(rectF2,paint);

        // 绘制圆弧
        paint.setColor(Color.BLUE);
        canvas.drawArc(rectF2,0,180,true,paint);

    }
}
