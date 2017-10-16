package txcap.com.viewdemo.CustomView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * 文件名:txcap.com.viewdemo.CustomView.NoScrollListView
 * 描 述:
 * 作 者:liuhe
 * 时 间:2017-08-29 16:10
 */

public class NoScrollListView extends ListView {
    ListViewTouchListener listener;

    public void setListViewTounchListener(ListViewTouchListener listener){
        this.listener = listener;
    }

    public NoScrollListView(Context context) {
        super(context);
    }

    public NoScrollListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoScrollListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        if(ev.getAction() == MotionEvent.ACTION_DOWN){
            listener.tounchDown();
        }

        if(ev.getAction() == MotionEvent.ACTION_UP){
            listener.tounchUp();
        }

        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }
}
