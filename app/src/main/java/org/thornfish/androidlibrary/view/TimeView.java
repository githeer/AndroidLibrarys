package org.thornfish.androidlibrary.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import org.thornfish.androidlibrary.R;


/**
 *  @name 时间倒计时
 *  @time 2017/4/21  17:54
 *  @describe 
 */
@SuppressLint("AppCompatCustomView")
public class TimeView extends TextView {

    private long hours;
    private long minutes;
    private long seconds;
    private long diff;
    private long days;
    private long time = 0;
    private String hour;
    private String minute;
    private String secnd;
    private Boolean isfirst;

    public TimeView(Context context) {
        this(context, null);
    }

    public TimeView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TimeView);
        diff = a.getInteger(R.styleable.TimeView_time, 0) * 1000;
        onCreate();
        isfirst = true;
    }


    /**
     * 根据 attrs 设置时间开始
     */
    private void onCreate() {
        start();
    }

    //开始计时
    private void start() {

        handler.removeMessages(1);
        
        getTime();
        Message message = handler.obtainMessage(1);
        handler.sendMessageDelayed(message, 1000);
    }
    


    /**
     * 设置事件
     *
     * @param time
     */
    public void setTime(long time) {
        this.time = time * 1000;
    }

    final Handler handler = new Handler(Looper.getMainLooper()) {

        public void handleMessage(Message msg) {         // handle message
            switch (msg.what) {
                case 1:
                    setVisibility(View.VISIBLE);
                    if(diff>0)
                    diff = diff - 1000;
                    getShowTime();
                    if (diff > 0) {
                        Message message = handler.obtainMessage(1);
                        handler.sendMessageDelayed(message, 1000);
                    }else if (diff==0){
                        timeOut.isdown();
                    } else {
                        if(!isfirst) {
//                        EventBus.getDefault().post(new IndexPwd(3));
                        }
                    }
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    /**
     * 得到时间差
     */
    private void getTime() {

        try {

            days = diff / (1000 * 60 * 60 * 24);
            hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
            seconds = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60) - minutes * (1000 * 60)) / (1000);
//            setText(days + ":" + hours + ":" + minutes + ":" + seconds);
            if(hours<10){
                 hour = "0"+String.valueOf(hours);
            }else{
                hour = String.valueOf(hours);
            }
            if(minutes<10){
                 minute = "0"+String.valueOf(minutes);
            }else{
                minute = String.valueOf(minutes);
            }
            if(seconds<10){
                 secnd = "0"+String.valueOf(seconds);
            }else{
                secnd = String.valueOf(seconds);
            }
            setText(minute + "分" + secnd + "秒");

        } catch (Exception e) {
        }
    }

    /**
     * 获得要显示的时间
     */
    private void getShowTime() {

        days = diff / (1000 * 60 * 60 * 24);
        hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
        seconds = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60) - minutes * (1000 * 60)) / (1000);
//        setText(days + ":" + hours + ":" + minutes + ":" + seconds);
        if(hours<10){
            hour = "0"+String.valueOf(hours);
        }else{
            hour = String.valueOf(hours);
        }
        if(minutes<10){
            minute = "0"+String.valueOf(minutes);
        }else{
            minute = String.valueOf(minutes);
        }
        if(seconds<10){
            secnd = "0"+String.valueOf(seconds);
        }else{
            secnd = String.valueOf(seconds);
        }
        setText(minute + " 分 " + secnd + " 秒");
    }

    /**
     * 以之前设置的时间重新开始
     */
    public void reStart() {
        this.diff = this.time;
        start();
    }

    /**
     * 设置时间重新开始
     *
     * @param time 重新开始的事件
     */
    public void reStart(long time) {
        if (time > 0) {
            this.diff = time * 1000;
            isfirst = false;
        }
        start();
    }

    public interface timeOut{
        void isdown();
    }

    timeOut timeOut;
    public void setTimeOut(timeOut timeOut){
        this.timeOut = timeOut;
    }

}
