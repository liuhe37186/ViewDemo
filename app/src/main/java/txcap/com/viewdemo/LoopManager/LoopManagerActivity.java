package txcap.com.viewdemo.LoopManager;

import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import txcap.com.viewdemo.GIFDemo.GIFSurfaceView;
import txcap.com.viewdemo.GIFDemo.GIFSurfaceView1;
import txcap.com.viewdemo.GIFDemo.Notify;
import txcap.com.viewdemo.R;

public class LoopManagerActivity extends AppCompatActivity implements Notify{

    List<Gift> giftList = new ArrayList<>();
    Button send;
    GIFSurfaceView surfaceView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        setContentView(R.layout.activity_loop_manager);
        send = (Button) findViewById(R.id.btn);
        surfaceView = (GIFSurfaceView) findViewById(R.id.gsv);
        surfaceView.setNotify(this);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gift g = new Gift();
                g.setId(new Random(10).nextInt());
                giftList.add(g);
            }
        });
        handler.post(runnable);
    }

    private boolean isRuning = true;
    boolean isPlay = false;
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if(isRuning){
                if (giftList.size() > 0 && !isPlay) {
                    handler.sendEmptyMessage(0);
                    Log.e("xxxx", "run: 有人送礼物");
                    isPlay = false;
                }
                handler.postDelayed(runnable,100);
            }

        }
    };


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(msg.what == 0){
                Log.e("xxxx", "handleMessage: 收到一个礼物 "+giftList.size());
                isPlay = true;
                surfaceView.setVisibility(View.VISIBLE);
                surfaceView.reload();
                surfaceView.setPath("red_packet_change1.gif");
                surfaceView.setZoom(1.5);
            }
        }
    };


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void playFinish() {
        Log.e("xxx", "playFinish:" );
        surfaceView.setVisibility(View.GONE);
        if(giftList.size() > 0){
            giftList.remove(giftList.size() - 1);
        }
        isPlay = false;
    }
}
