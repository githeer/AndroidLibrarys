package org.thornfish.androidlibrary.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/5/15.
 */

public class LightView extends View{

    private RectF rectF;
    private Paint paint;
    private Paint circle;
    private Paint circlePic;
    private int width;
    private int heigth;
    private Matrix matrix;
    private int degrees;

    private Bitmap bitmap;

    private Handler mHandler = new Handler();
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            degrees++;
            matrix.postRotate(degrees, width/2, heigth/2);//旋转矩阵
            mHandler.postDelayed(mRunnable, 20);
            invalidate();// 重绘
        }
    };

    public LightView(Context context) {
        super(context);
        initPoint();
        mHandler.postDelayed(mRunnable,500);
    }

    public LightView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPoint();
        mHandler.postDelayed(mRunnable,500);
    }

    public LightView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPoint();
        mHandler.postDelayed(mRunnable,500);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        heigth = h;
        RadialGradient gradient = new RadialGradient(0,0,width/2+width/5, new int[]{Color.parseColor("#ffffff"),
                Color.parseColor("#00ffffff")},null, Shader.TileMode.REPEAT);
        paint.setShader(gradient);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.setMatrix(matrix);
        canvas.translate(width/2,heigth/2);
        rectF.set(-width,-width,width,width);
//        rectFCir.set(-width/8,-width/8,width/8,width/8);
//
//        canvas.drawArc(rectFCir, (float) 17.937, (float) 36,true,paint);
//        canvas.drawArc(rectFCir, (float) 90, (float) 36,true,paint);
//        canvas.drawArc(rectFCir, (float) 162, (float) 36,true,paint);
//        canvas.drawArc(rectFCir, (float) 234, (float) 36,true,paint);
//        canvas.drawArc(rectFCir, (float) 306, (float) 36,true,paint);

        canvas.drawArc(rectF, (float) 342, (float) 36,true,paint);
        canvas.drawArc(rectF, (float) 54, (float) 36,true,paint);
        canvas.drawArc(rectF, (float) 126, (float) 36,true,paint);
        canvas.drawArc(rectF, (float) 198, (float) 36,true,paint);
        canvas.drawArc(rectF, (float) 270, (float) 36,true,paint);

        canvas.drawCircle(0, 0, 50, circle);
//        canvas.drawBitmap(bitmap,-bitmap.getWidth()/2,-bitmap.getHeight()/2,circlePic);

        matrix.reset();
    }



    private void initPoint() {

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(Color.parseColor("#70ffffff"));
        paint.setStyle(Paint.Style.FILL);
        circle = new Paint(paint);
        circlePic = new Paint();
        circle.setColor(Color.parseColor("#ffffff"));

        rectF = new RectF();
        matrix = new Matrix();
//        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.game_pop_congratulation);

    }

}
