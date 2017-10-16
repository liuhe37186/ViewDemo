package txcap.com.viewdemo.FrameAnimDemo;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

import txcap.com.viewdemo.R;

public class FrameAnimActivity extends AppCompatActivity {

    ImageView imageView;
    Button start;
    AnimationDrawable animationDrawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_anim);
        imageView = (ImageView) findViewById(R.id.iv);
        start = (Button) findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                imageView.setImageResource(R.drawable.anim);
//                animationDrawable = (AnimationDrawable) imageView.getDrawable();
//                animationDrawable.start();
                checkGiftCountSubscribe();
            }
        });

    }
    Timer mTimer;

    private void checkGiftCountSubscribe() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                Log.e("xxxx","xxxx");

            }
        };
        mTimer = new Timer();
        mTimer.schedule(task, 0, 1000);


    }
}
