package txcap.com.viewdemo.CustomView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import txcap.com.viewdemo.MainActivity;
import txcap.com.viewdemo.R;

public class PullToZoomActivity extends AppCompatActivity {

    PullToZoomListView listView;
    private String[] adapterData;
    String url = "http://pic.qiantucdn.com/58pic/15/82/03/80J58PICnhD_1024.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_zoom);
        listView = (PullToZoomListView)findViewById(R.id.listview);
        adapterData = new String[] { "Activity","Service","Content Provider","Intent","BroadcastReceiver","ADT","Sqlite3","HttpClient","DDMS","Android Studio","Fragment","Loader" };

        listView.setAdapter(new ArrayAdapter<>(PullToZoomActivity.this,
                android.R.layout.simple_list_item_1, adapterData));
        Glide.with(this).load(url).error(R.drawable.g1).into(listView.getHeaderView());
//        listView.getHeaderView().setImageResource(R.drawable.g1);
        listView.getHeaderView().setScaleType(ImageView.ScaleType.CENTER_CROP);
    }
}
