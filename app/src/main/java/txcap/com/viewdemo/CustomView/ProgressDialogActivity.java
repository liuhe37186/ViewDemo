package txcap.com.viewdemo.CustomView;

import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

import txcap.com.viewdemo.R;
import txcap.com.viewdemo.view.CirclePercentView;
import txcap.com.viewdemo.view.CircleTextProgressbar;

public class ProgressDialogActivity extends AppCompatActivity {

    CirclePercentView percentView;
    CircleTextProgressbar textProgressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_dialog);
        percentView = (CirclePercentView) findViewById(R.id.circleView);

        percentView.setPercent(100);

        final CountDownTimer timer = new CountDownTimer(30*1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                percentView.setPercent((int) millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {

            }
        };

        percentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.start();
            }
        });


        textProgressbar = (CircleTextProgressbar) findViewById(R.id.ctp);
        textProgressbar.setCountdownProgressListener(1,progressListener);

        textProgressbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textProgressbar.reStart();
            }
        });
        textProgressbar.setOutLineWidth(30);
        textProgressbar.setProgressLineWidth(30);
        textProgressbar.setOutLineColor(ContextCompat.getColor(this,R.color.emerald));
        textProgressbar.setInCircleColor(ContextCompat.getColor(this,R.color.colorPrimary));
        textProgressbar.setProgressColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));
        textProgressbar.setTimeMillis(30*1000);

    }

    private CircleTextProgressbar.OnCountdownProgressListener progressListener = new CircleTextProgressbar.OnCountdownProgressListener() {
        @Override
        public void onProgress(int what, int progress) {
            if (what == 1) {
                textProgressbar.setText(progress / 12 + "S");
            }
            // 比如在首页，这里可以判断进度，进度到了100或者0的时候，你可以做跳过操作。
        }
    };
}
