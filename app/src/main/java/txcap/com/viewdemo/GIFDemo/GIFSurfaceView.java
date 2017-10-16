package txcap.com.viewdemo.GIFDemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import txcap.com.viewdemo.R;

import static android.R.attr.bitmap;

/**
 * 文件名:txcap.com.viewdemo.GIFDemo.GIFSurfaceView
 * 描 述:
 * 作 者:liuhe
 * 时 间:2017-04-27 11:04
 */

public class GIFSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder holder;
    private String path;
    private Movie movie;
    Canvas canvas;
    private Handler handler;
    private double zoom;
    private int playingTime = 0;
    private static final int FRAME_INTERVAL = 50;
    boolean isFirstTime = true;
    Notify notify;

    public void reload(){
        isFirstTime = true;
        mStart = 0;
        invalidate();
    }

    public void setNotify(Notify notify){
        this.notify = notify;
    }


    public GIFSurfaceView(Context context) {
        super(context);
        initParam();
    }

    public GIFSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initParam();
    }

    public GIFSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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

    long mStart;

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (movie != null) {

                canvas = holder.lockCanvas();
                canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                canvas.save();
                canvas.scale((float)zoom, (float)zoom);
                movie.draw(canvas, 0, 0);
                int dura = movie.duration();
                if (dura == 0) {
                    dura = 1000;
                }


                long now = android.os.SystemClock.uptimeMillis();
                if(mStart == 0){
                    mStart = now;
                }

                movie.setTime((int) ((now - mStart) % dura));
//                Log.e("xxxx","time:"+(int) ((now - mStart) % dura)+"currentTimeMillis:"+ new SimpleDateFormat("HH:mm:ss:SSSS").format(new Date()));
                canvas.restore();

                holder.unlockCanvasAndPost(canvas);
                handler.postDelayed(runnable,50);
                if(isFirstTime){
                    handler.sendEmptyMessageDelayed(0,dura);
                    Log.e("xxxx","sendEmptyMessageDelayed:"+dura);
                    Log.e("xxxx","dura/FRAME_INTERVAL:"+dura/FRAME_INTERVAL);
                    isFirstTime = false;
                }
            }
        }
    };

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

                Log.e("xxxx","onMeasure width:"+width+",height"+height);

                setMeasuredDimension((int) (width * zoom), (int) (height * zoom));
                handler.post(runnable);
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
        handler.removeCallbacks(runnable);
    }


    public String getPath() {
        return path;
    }


    public void setPath(String path) {
        this.path = path;
    }

    public void setZoom(double zoom) {
        this.zoom = zoom;
    }
}
