package txcap.com.viewdemo.CustomView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import txcap.com.viewdemo.R;
import txcap.com.viewdemo.view.CustomPieView;
import txcap.com.viewdemo.view.PieData;
import txcap.com.viewdemo.view.PieView;

public class TrainingViewActivity extends AppCompatActivity {

    CustomPieView customPieView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_view);
        /**
         *  PieView view = new PieView(this);
         setContentView(view);

         ArrayList<PieData> datas = new ArrayList<>();
         PieData pieData = new PieData("sloop", 60);
         PieData pieData2 = new PieData("sloop", 30);
         PieData pieData3 = new PieData("sloop", 40);
         PieData pieData4 = new PieData("sloop", 20);
         PieData pieData5 = new PieData("sloop", 20);
         datas.add(pieData);
         datas.add(pieData2);
         datas.add(pieData3);
         datas.add(pieData4);
         datas.add(pieData5);
         view.setData(datas);
         */
//        CustomPieView customPieView = new CustomPieView(this);
//        setContentView(customPieView);
        customPieView = (CustomPieView) findViewById(R.id.custom_pie_view);
        ArrayList<PieData> datas = new ArrayList<>();
        PieData pieData = new PieData("sloop", 60);
        PieData pieData2 = new PieData("sloop", 30);
        PieData pieData3 = new PieData("sloop", 40);
        PieData pieData4 = new PieData("sloop", 20);
        PieData pieData5 = new PieData("sloop", 20);
        PieData pieData6 = new PieData("sloop", 50);
        datas.add(pieData);
        datas.add(pieData2);
        datas.add(pieData3);
        datas.add(pieData4);
        datas.add(pieData5);
        datas.add(pieData6);
//        customPieView.setStartAngle(180);
        customPieView.setData(datas);
    }
}
