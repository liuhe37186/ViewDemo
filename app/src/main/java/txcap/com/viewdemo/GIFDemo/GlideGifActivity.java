package txcap.com.viewdemo.GIFDemo;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import txcap.com.viewdemo.R;

public class GlideGifActivity extends AppCompatActivity implements Notify{

    ImageView view,redPacket;
    GIFSurfaceView redPacketChange;
    Button start,change;
    Context context;
    LinearLayout llPacketNum;
    TextView packetNum;
    EditText et,et1;

    int redPacketStatus = 0;
    int redPacketNum = 0;
    boolean isPlay = false;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Log.e("xxxx","收到消息 what："+msg.what+",msg.obj:"+msg.obj);
            int obj = (int) msg.obj;
            if(msg.what == 0){
                isPlay = true;
                redPacketChange.setVisibility(View.VISIBLE);
                redPacketChange.reload();
                switch (obj){
                    case 0:
                        redPacketChange.setPath("red_packet_change.gif");
                        break;
                    case 1:
                        redPacketChange.setPath("red_packet_change1.gif");
                        break;
                    case 2:
                        redPacketChange.setPath("red_packet_change2.gif");
                        break;
                    case 3:
                        redPacketChange.setPath("red_packet_change3.gif");
                        break;
                }
                redPacketChange.setZoom(1);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_glide_gif);
        view = (ImageView) findViewById(R.id.iv);
        redPacket = (ImageView) findViewById(R.id.iv_red_packet);
        llPacketNum = (LinearLayout) findViewById(R.id.ll_packet_num);
        packetNum = (TextView) findViewById(R.id.tv_packet_num);

        redPacketChange = (GIFSurfaceView) findViewById(R.id.gsv_red_packet_change);
        redPacketChange.setNotify(this);
        change = (Button) findViewById(R.id.btn_change);

        et = (EditText) findViewById(R.id.editText);
        et1 = (EditText) findViewById(R.id.editText2);

        start = (Button) findViewById(R.id.start);


        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                redPacketStatus = new Integer(et.getText().toString());
                Message msg = Message.obtain();
                msg.what = 0;
                switch (redPacketStatus){
                    case 0:
                        msg.obj = 0;
                        break;
                    case 1:
                        msg.obj = 1;
                        break;
                    case 2:
                        msg.obj = 2;
                        break;
                    case 3:
                        msg.obj = 3;
                        break;
                }
                handler.sendMessage(msg);

                Log.e("xxxx","发送消息 what："+msg.what+",msg.obj:"+msg.obj);
            }
        });


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                redPacketStatus = new Integer(et.getText().toString());
                redPacketNum =new Integer(et1.getText().toString());

                llPacketNum.setVisibility(View.GONE);
                redPacket.setVisibility(View.VISIBLE);
                switch (redPacketStatus){
                    case 0:
                        Glide.with(context).load(R.drawable.red_packet_normal).into(redPacket);
                        break;
                    case 1:
                        Glide.with(context).load(R.drawable.red_packet_normal1).into(redPacket);
                        break;
                    case 2:
                        Glide.with(context).load(R.drawable.red_packet_normal2).into(redPacket);
                        break;
                    case 3:
                        Glide.with(context).load(R.drawable.red_packet_normal3).into(redPacket);
                        break;
                }

            }
        });
        redPacket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setVisibility(View.VISIBLE);
                Glide.with(context).load(R.drawable.bg_light).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(view);
                if(redPacketNum > 0){
                    llPacketNum.setVisibility(View.VISIBLE);
                    packetNum.setText(redPacketNum+"");

                    switch (redPacketStatus){
                        case 0:
                            Glide.with(context).load(R.drawable.red_packet_open).into(redPacket);
                            break;
                        case 1:
                            Glide.with(context).load(R.drawable.red_packet_open1).into(redPacket);
                            break;
                        case 2:
                            Glide.with(context).load(R.drawable.red_packet_open2).into(redPacket);
                            break;
                        case 3:
                            Glide.with(context).load(R.drawable.red_packet_open3).into(redPacket);
                            break;
                    }

                }else {
                    llPacketNum.setVisibility(View.GONE);
                    switch (redPacketStatus){
                        case 0:
                            Glide.with(context).load(R.drawable.red_packet_none).into(redPacket);
                            break;
                        case 1:
                            Glide.with(context).load(R.drawable.red_packet_none1).into(redPacket);
                            break;
                        case 2:
                            Glide.with(context).load(R.drawable.red_packet_none2).into(redPacket);
                            break;
                        case 3:
                            Glide.with(context).load(R.drawable.red_packet_none3).into(redPacket);
                            break;
                    }
                }




            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        view = null;
        System.gc();
    }

    @Override
    public void playFinish() {
        Log.e("xxx", "playFinish:" );
        redPacketChange.setVisibility(View.GONE);
        isPlay = false;
    }
}
