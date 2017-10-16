package txcap.com.viewdemo.CustomView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * @author yuanxia
 * @version V1.0
 * @Description: 嵌套能够全部显示的gridview
 * @date 16/11/01.
 */
public class MeasureListview extends ListView {

    public MeasureListview(Context context) {
        super(context);
    }

    public MeasureListview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MeasureListview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int  expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        // TODO Auto-generated method stub
//        getParent().requestDisallowInterceptTouchEvent(true);
//
//        return  super.dispatchTouchEvent(ev);
//    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.onTouchEvent(ev);
    }
}
