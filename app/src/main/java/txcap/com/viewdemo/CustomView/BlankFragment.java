package txcap.com.viewdemo.CustomView;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import txcap.com.viewdemo.R;
import txcap.com.viewdemo.view.SlowScrollView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment implements SlowScrollView.OnScrollListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView title;
    SlowScrollView scrollView;
    RelativeLayout titleSmall;
    ImageView liveImg,searchImg;
    MeasureListview mListView;
    ArrayAdapter arrayAdapter;

    String[] data =  {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o"};
    public BlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(String param1, String param2) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        title = (TextView) view.findViewById(R.id.title);
        scrollView = (SlowScrollView) view.findViewById(R.id.scroll_view);
        scrollView.setOnScrollListener(this);
        titleSmall = (RelativeLayout) view.findViewById(R.id.rl_title_small);
        liveImg = (ImageView) view.findViewById(R.id.iv_live);
        searchImg = (ImageView) view.findViewById(R.id.iv_search);
        mListView = (MeasureListview) view.findViewById(R.id.listview);
        arrayAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,data);
        mListView.setAdapter(arrayAdapter);
        return view;
    }



    private void changeAnimation(int scrollY){
        title.setTextSize((float) (32-0.14*scrollY));
        title.setText("时代直播");
        titleSmall.setVisibility(View.GONE);
        ViewGroup.LayoutParams liveParams = liveImg.getLayoutParams();
        liveParams.height=dip2px(getActivity(), (float) (36-0.04*scrollY));
        liveParams.width =dip2px(getActivity(), (float) (36-0.04*scrollY));
        liveImg.setLayoutParams(liveParams);
        ViewGroup.LayoutParams searchParams = searchImg.getLayoutParams();
        searchParams.height=dip2px(getActivity(), (float) (36-0.04*scrollY));
        searchParams.width =dip2px(getActivity(), (float) (36-0.04*scrollY));
        searchImg.setLayoutParams(searchParams);
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    public void onScroll(int scrollY, int oldScrollY) {
        if(scrollY <= 100 && scrollY >= 0){
            changeAnimation(scrollY);
        }else if(scrollY > 100){
            titleSmall.setVisibility(View.VISIBLE);
        }else if(scrollY < 0){
            titleSmall.setVisibility(View.GONE);
            title.setTextSize(32);
        }

        /**
         * 增加多种情况判断，减少因为快速滑动导致标题突然变大的情况
         */

        if(scrollY >= 100){
            title.setTextSize(18);
        }

        if(oldScrollY > 200 && scrollY <=0){
            title.setTextSize(32);
        }

        if(oldScrollY >180 && scrollY <=80){
            title.setTextSize(32);
        }

//        Log.e("xxxx","scrollY"+scrollY+",oldScrollY"+oldScrollY);
    }



}
