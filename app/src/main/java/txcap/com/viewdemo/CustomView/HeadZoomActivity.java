package txcap.com.viewdemo.CustomView;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import txcap.com.viewdemo.R;

public class HeadZoomActivity extends AppCompatActivity {

    ImageView iv;
    Context context;
//    String url = "http://seopic.699pic.com/photo/50008/2836.jpg_wh1200.jpg";
    String url = "http://pic.qiantucdn.com/58pic/15/82/03/80J58PICnhD_1024.jpg";
    HeadZoomScrollView  scrollView;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_head_zoom);
        iv = (ImageView) findViewById(R.id.iv);
        scrollView = (HeadZoomScrollView) findViewById(R.id.scroll_view);
        linearLayout = (LinearLayout) findViewById(R.id.ll);

        scrollView.setZoomView(iv);
        scrollView.setCoverView(linearLayout);

        try {
            Glide.with(context).load(url).listener(requestListener).error(R.drawable.g1).into(iv);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private RequestListener<String, GlideDrawable> requestListener = new RequestListener<String, GlideDrawable>() {
        @Override
        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
            // todo log exception

            e.printStackTrace();
            return false;
        }

        @Override
        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
            return false;
        }
    };
}
