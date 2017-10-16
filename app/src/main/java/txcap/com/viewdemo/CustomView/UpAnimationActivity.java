package txcap.com.viewdemo.CustomView;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import txcap.com.viewdemo.R;
import txcap.com.viewdemo.view.DirectionalViewPager;

public class UpAnimationActivity extends AppCompatActivity implements ListViewTouchListener{

    DirectionalViewPager dViewPage;
    List<View> mList = new ArrayList<>();
    RelativeLayout container;
    View view;
    View childView;
    NoScrollListView lv;
    private int mPositon = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_animation);
        dViewPage = (DirectionalViewPager) findViewById(R.id.dvp);
        dViewPage.setOrientation(DirectionalViewPager.VERTICAL);
        dViewPage.setVisibility(View.GONE);
        view = LayoutInflater.from(this).inflate(R.layout.view_item,null);
//        view1 = LayoutInflater.from(this).inflate(R.layout.view_item_1,null);
        lv = (NoScrollListView) findViewById(R.id.listview);
        lv.setAdapter(new LoopAdapter());
        lv.setSelection(0);
//        handler.sendEmptyMessageDelayed(0,3*1000);
        handler.postDelayed(runnable,3*1000);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(UpAnimationActivity.this,"点击了"+position%4,Toast.LENGTH_LONG).show();
                lv.setSelection(position%4);
            }
        });
        lv.setListViewTounchListener(this);

    }

    @Override
    public void tounchDown() {
        isCanScroll = false;
        handler.removeCallbacks(runnable);
        lv.setSelection(mPositon%4);
    }

    @Override
    public void tounchUp() {
        isCanScroll = true;
//        handler.sendEmptyMessageDelayed(0,3*1000);
        handler.postDelayed(runnable,3*1000);
    }


    class LoopAdapter extends BaseAdapter {

        final String[] str = new String[]{"Apple","Boy","Cat","Dog"};

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public Object getItem(int position) {
            return str[position % str.length];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            mPositon = position;
            View view1 = LayoutInflater.from(UpAnimationActivity.this).inflate(R.layout.view_item_1,null);
            TextView textView = (TextView) view1.findViewById(R.id.tv);
            textView.setText( (String) getItem(position));
            ImageView bannerImg = (ImageView) view1.findViewById(R.id.banner_img);
            if(position%str.length == 3){
                bannerImg.setVisibility(View.VISIBLE);
                textView.setVisibility(View.GONE);
            }else {
                bannerImg.setVisibility(View.GONE);
                textView.setVisibility(View.VISIBLE);
            }

            return view1;
        }

    }

    int positon = 0;
    boolean isCanScroll = true;

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if(isCanScroll){
                positon++;
                lv.smoothScrollBy(dip2px(UpAnimationActivity.this,72),1000);
//                handler.sendEmptyMessageDelayed(0,3*1000);
                handler.postDelayed(runnable,3*1000);
            }
        }
    };

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

//            if(isCanScroll){
//                positon++;
//                lv.smoothScrollBy(dip2px(UpAnimationActivity.this,72),1000);
//                handler.sendEmptyMessageDelayed(0,3*1000);
//            }


        }
    };


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
