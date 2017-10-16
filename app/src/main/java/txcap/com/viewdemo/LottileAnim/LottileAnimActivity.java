package txcap.com.viewdemo.LottileAnim;

import android.animation.Animator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;

import txcap.com.viewdemo.view.DonutProgress;
import txcap.com.viewdemo.R;

public class LottileAnimActivity extends AppCompatActivity {

    private static final String TAG = "xxx";
    DonutProgress progress;
    LottieAnimationView animationView;
    Button start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottile_anim);
        animationView = (LottieAnimationView) findViewById(R.id.animation_view);
        start = (Button) findViewById(R.id.start);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationView.setAnimation("PinJump.json");
                animationView.loop(true);
                animationView.playAnimation();
                animationView.addAnimatorListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        Log.e(TAG, "onAnimationStart: ");
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
//                        mHandler.sendEmptyMessage(0);
                        Log.e(TAG, "onAnimationEnd: ");

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        Log.e(TAG, "onAnimationCancel: ");
                        System.gc();
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                        Log.e(TAG, "onAnimationRepeat: ");

                    }
                });
            }
        });
    }

   /* Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            animationView.setAnimation("PinJump.json");
            animationView.loop(true);
            animationView.playAnimation();

            return false;
        }
    });*/

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: ");
        animationView.resumeAnimation();
    }

    @Override
    protected void onStop() {
        super.onStop();
        animationView.cancelAnimation();
        Log.e(TAG, "onStop: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        animationView.pauseAnimation();
        Log.e(TAG, "onPause: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.e(TAG, "onRestart: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: ");
        animationView.destroyDrawingCache();
        animationView.cancelAnimation();
        System.gc();
    }
}
