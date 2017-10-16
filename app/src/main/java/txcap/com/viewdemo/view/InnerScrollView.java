package txcap.com.viewdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * 文件名:txcap.com.viewdemo.view.InnerScrollView
 * 描 述:
 * 作 者:liuhe
 * 时 间:2017-09-01 09:38
 */

public class InnerScrollView extends ScrollView {
    public InnerScrollView(Context context) {
        super(context);
    }

    public InnerScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InnerScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private int x=0;
    private int y=0;
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.e("xxx","InnerScrollView super.onTouchEvent(ev)"+super.onTouchEvent(ev)+ev.getAction());
        return true;

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("xxx","InnerScrollView super.dispatchTouchEvent(ev)"+super.dispatchTouchEvent(ev)+ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e("xxx","InnerScrollView super.onInterceptTouchEvent(ev)"+super.onInterceptTouchEvent(ev)+ev.getAction());
        switch (ev.getAction()) {

            case MotionEvent.ACTION_DOWN:
                //手指按下的时候：初始化 x,y 值
                x=(int) ev.getX();
                y=(int) ev.getY();
                break;

            case MotionEvent.ACTION_MOVE:
                //移动就不说了，因为在这里判断手势，只和 按下的位置和 抬起来的位置 有关；
                break;
            case MotionEvent.ACTION_UP:
                /*
                 * 手指抬起来触发 ，所以判断在这里进行
                 * 1.获得结束的x,y
                 * 2.进行判断
                 */
                int upx=(int) ev.getX();
                int upy=(int) ev.getY();
                String result=drawTouch(upx,upy);
                if(result.equals("向下滑动")){
                    return true;
                }
                //提醒
                break;


        }
        return false;

    }

    private String drawTouch(int upx,int upy){

        String str="没有滑动";
        //水平滑动
        if(upx-x>100){
            str="向右滑动";
            //改变图片
        }else if(x-upx>100){
            str="向左滑动";
            //改变图片
        }else if(upy-y>100){
            str="向下滑动";
            //改变图片
        }else if(y-upy>100){
            str="向上滑动";
            //改变图片
        }
        Log.e("xxxx","InnerScrollView"+str);
        return str;
    }
}
