package txcap.com.viewdemo;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import txcap.com.viewdemo.CustomView.CommonActivity;
import txcap.com.viewdemo.CustomView.HeadZoomActivity;
import txcap.com.viewdemo.CustomView.ProgressDialogActivity;
import txcap.com.viewdemo.CustomView.PullToZoomActivity;
import txcap.com.viewdemo.CustomView.PullZoomActivity;
import txcap.com.viewdemo.CustomView.TrainingViewActivity;
import txcap.com.viewdemo.CustomView.UpAnimationActivity;
import txcap.com.viewdemo.FrameAnimDemo.FrameAnimActivity;
import txcap.com.viewdemo.GIFDemo.GIFActivity;
import txcap.com.viewdemo.GIFDemo.GlideGifActivity;
import txcap.com.viewdemo.LoopManager.GiftPanelActivity;
import txcap.com.viewdemo.LoopManager.LoopManagerActivity;
import txcap.com.viewdemo.LottileAnim.LottileAnimActivity;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    private String[] data = new String[]{"lottile", "gif", "frame", "glide",
            "zoom", "list", "loop","panel","pullZoom","progress","upAnimation","commonActivity","自定义View"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Resources res = getResources();
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.lv);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data);
        lv.setAdapter(arrayAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        startActivity(new Intent(MainActivity.this, LottileAnimActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, GIFActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, FrameAnimActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, GlideGifActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this, HeadZoomActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(MainActivity.this, PullToZoomActivity.class));
                        break;
                    case 6:
                        startActivity(new Intent(MainActivity.this, LoopManagerActivity.class));
                        break;
                    case 7:
                        startActivity(new Intent(MainActivity.this, GiftPanelActivity.class));
                        break;
                    case 8:
                        startActivity(new Intent(MainActivity.this, PullZoomActivity.class));
                        break;
                    case 9:
                        startActivity(new Intent(MainActivity.this, ProgressDialogActivity.class));
                        break;
                    case 10:
                        startActivity(new Intent(MainActivity.this, UpAnimationActivity.class));
                        break;
                    case 11:
                        startActivity(new Intent(MainActivity.this, CommonActivity.class));
                        break;
                    case 12:
                        startActivity(new Intent(MainActivity.this, TrainingViewActivity.class));
                        break;

                }
            }
        });


    }


}
