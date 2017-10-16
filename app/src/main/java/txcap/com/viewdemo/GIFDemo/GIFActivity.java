package txcap.com.viewdemo.GIFDemo;

import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import txcap.com.viewdemo.R;

public class GIFActivity extends AppCompatActivity implements Notify{

    GIFSurfaceView surfaceView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif);
        surfaceView = (GIFSurfaceView) findViewById(R.id.gsv);
        surfaceView.setPath("2.gif");
        surfaceView.setZoom(3);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        surfaceView = null;
        System.gc();
    }


    @Override
    public void playFinish() {
        surfaceView.setVisibility(View.GONE);
    }
}
