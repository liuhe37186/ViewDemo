package txcap.com.viewdemo.GIFDemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 文件名:txcap.com.viewdemo.GIFDemo.GIFSurfaceView
 * 描 述:
 * 作 者:liuhe
 * 时 间:2017-04-27 11:04
 */

public class GIFSurfaceView1 extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder holder;
    private String path;
    private Movie movie;
    Canvas canvas;
    private Handler handler;
    private int zoom;
    private int playingTime = 0;
    private static final int FRAME_INTERVAL = 50;
    boolean isFirstTime = true;
    Notify notify;
    private int time;
    Context context;

    public void reload(){
        isFirstTime = true;
        invalidate();
    }

    public void setNotify(Notify notify){
        this.notify = notify;
    }


    public GIFSurfaceView1(Context context) {
        super(context);
        initParam();
        this.context = context;
    }

    public GIFSurfaceView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initParam();
    }

    public GIFSurfaceView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initParam();
    }


    private void initParam() {
        holder = getHolder();
        holder.addCallback(this);
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if(notify != null){
                    notify.playFinish();
                    playingTime = 0;
                }
                return false;
            }
        });

        setZOrderOnTop(true);

        getHolder().setFormat(PixelFormat.TRANSLUCENT);



    }

    Timer timer = new Timer();
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            if (movie != null) {

                canvas = holder.lockCanvas();

                if(canvas== null){
                    holder = getHolder();
                    canvas = holder.lockCanvas();
                }

                canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                canvas.save();
                canvas.scale(zoom, zoom);
                movie.draw(canvas, 0, 0);
                int dura = movie.duration();
                /*long time = System.currentTimeMillis();
                int dura = movie.duration();
                if (dura == 0) {
                    dura = 1000;
                }*/

                playingTime += FRAME_INTERVAL;
                if (playingTime > dura)
                {
                    playingTime = 0;
                }


//                movie.setTime((int) (time % dura));
                movie.setTime(playingTime);
                Log.e("xxxx","playingTime:"+playingTime+"currentTimeMillis:"+ new SimpleDateFormat("HH:mm:ss:SSSS").format(new Date()));
                canvas.restore();

                holder.unlockCanvasAndPost(canvas);
                timer.schedule(task,dura/FRAME_INTERVAL);
                if(isFirstTime){
                    handler.sendEmptyMessageDelayed(0,dura);
                    Log.e("xxxx","sendEmptyMessageDelayed:"+dura);
                    Log.e("xxxx","dura/FRAME_INTERVAL:"+dura/FRAME_INTERVAL);
                    isFirstTime = false;
                }
            }
        }
    };

   /* private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (movie != null) {

                canvas = holder.lockCanvas();
                canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                canvas.save();
                canvas.scale(zoom, zoom);
                movie.draw(canvas, 0, 0);
                int dura = movie.duration();
                *//*long time = System.currentTimeMillis();
                int dura = movie.duration();
                if (dura == 0) {
                    dura = 1000;
                }*//*

                playingTime += FRAME_INTERVAL;
                if (playingTime > dura)
                {
                    playingTime = 0;
                }


//                movie.setTime((int) (time % dura));
                movie.setTime(playingTime);
                Log.e("xxxx","playingTime:"+playingTime+"currentTimeMillis:"+ new SimpleDateFormat("HH:mm:ss:SSSS").format(new Date()));
                canvas.restore();

                holder.unlockCanvasAndPost(canvas);
                handler.postDelayed(runnable,85);
                if(isFirstTime){
                    handler.sendEmptyMessageDelayed(0,dura);
                    Log.e("xxxx","sendEmptyMessageDelayed:"+dura);
                    Log.e("xxxx","dura/FRAME_INTERVAL:"+dura/FRAME_INTERVAL);
                    isFirstTime = false;
                }
            }
        }
    };*/

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!TextUtils.isEmpty(path)) {
            try {
                InputStream stream = getContext().getAssets().open(path);
                movie = Movie.decodeStream(stream);

                int width = movie.width();
                int height = movie.height();

                setMeasuredDimension((int) (width * zoom), (int) (height * zoom));
//                handler.post(runnable);
                timer.schedule(task,0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
//        handler.removeCallbacks(runnable);
        timer.cancel();
    }


    public String getPath() {
        return path;
    }


    public void setPath(String path) {
        this.path = path;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }
}
