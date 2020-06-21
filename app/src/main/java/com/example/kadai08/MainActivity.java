package com.example.kadai08;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import  android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

class BallSurfaceView implements SurfaceHolder.Callback,Runnable{
    private  int x =0,y=0,dx=5,dy=5;
    int screen_width,screen_height;
    private Thread thread;
    private SurfaceHolder holder;

    BallSurfaceView(SurfaceView sv){
        holder=sv.getHolder();
        holder.addCallback(this);
    }
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){
        screen_width=width;
        screen_height=height;
    }
    public void surfaceCreated(SurfaceHolder holder){
        thread=new Thread(this);
        thread.start();
    }
    public  void surfaceDestroyed(SurfaceHolder holder){
        thread=null;
    }
    public  void run(){
        while (thread!=null){
            Canvas canvas=holder.lockCanvas();
            if(canvas !=null){
                Paint paint = new Paint();
                canvas.drawColor(Color.BLACK);
                paint.setColor(Color.WHITE);
                canvas.drawCircle(x,y,10,paint);
                holder.unlockCanvasAndPost(canvas);
                x=x+dx;
                y=y+dy;
                if(x<0 || x> screen_width) dx=dx* -1;
                if(y<0 || y> screen_height) dy=dy* -1;
            }
            try{
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SurfaceView sv=(SurfaceView)findViewById(R.id.surfaceview);
        BallSurfaceView bsv=new BallSurfaceView(sv);
    }
}



















