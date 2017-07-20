package org.thornfish.androidlibrary.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * Created by Administrator on 2017/6/23.
 */

public class InputHeight {



    private View rootView;//activity的根视图
    int rootViewVisibleHeight;//纪录根视图的显示高度
    private OnSoftKeyBoardChangeListener onSoftKeyBoardChangeListener;

    public InputHeight(Activity activity) {
        //获取activity的根视图
        rootView = activity.getWindow().getDecorView();

        //监听视图树中全局布局发生改变或者视图树中的某个视图的可视状态发生改变
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //获取当前根视图在屏幕上显示的大小
                Rect r = new Rect();
                rootView.getWindowVisibleDisplayFrame(r);
                int visibleHeight = r.height();
                if (rootViewVisibleHeight == 0) {
                    rootViewVisibleHeight = visibleHeight;
                    return;
                }

                //根视图显示高度没有变化，可以看作软键盘显示／隐藏状态没有改变
                if (rootViewVisibleHeight == visibleHeight) {
                    return;
                }

                //根视图显示高度变小超过200，可以看作软键盘显示了
                if (rootViewVisibleHeight - visibleHeight > 200) {
                    if (onSoftKeyBoardChangeListener != null) {
                        onSoftKeyBoardChangeListener.keyBoardShow(rootViewVisibleHeight - visibleHeight);
                    }
                    rootViewVisibleHeight = visibleHeight;
                    return;
                }

                //根视图显示高度变大超过200，可以看作软键盘隐藏了
                if (visibleHeight - rootViewVisibleHeight > 200) {
                    if (onSoftKeyBoardChangeListener != null) {
                        onSoftKeyBoardChangeListener.keyBoardHide(visibleHeight - rootViewVisibleHeight);
                    }
                    rootViewVisibleHeight = visibleHeight;
                    return;
                }

            }
        });
    }

    private void setOnSoftKeyBoardChangeListener(OnSoftKeyBoardChangeListener onSoftKeyBoardChangeListener) {
        this.onSoftKeyBoardChangeListener = onSoftKeyBoardChangeListener;
    }

    public interface OnSoftKeyBoardChangeListener {
        void keyBoardShow(int height);

        void keyBoardHide(int height);
    }

    public static void setListener(Activity activity, OnSoftKeyBoardChangeListener onSoftKeyBoardChangeListener) {
        InputHeight softKeyBoardListener = new InputHeight(activity);
        softKeyBoardListener.setOnSoftKeyBoardChangeListener(onSoftKeyBoardChangeListener);
    }




    private static boolean isFirst = true;
    public interface OnGetSoftHeightListener {
        void onShowed(int height);
    }
    public interface OnSoftKeyWordShowListener {
        void hasShow(boolean isShow);
    }

    /** * 获取软键盘的高度 * *
     @param rootView *
     @param listener
     */
    public static void getSoftKeyboardHeight(final View rootView, final OnGetSoftHeightListener listener) {
        final ViewTreeObserver.OnGlobalLayoutListener layoutListener
                = new ViewTreeObserver.OnGlobalLayoutListener() {
            @SuppressLint("NewApi")
            @Override
            public void onGlobalLayout() {
                if (isFirst) {
                    final Rect rect = new Rect();
                    rootView.getWindowVisibleDisplayFrame(rect);
                    final int screenHeight = rootView.getRootView().getHeight();
                    final int heightDifference = screenHeight - rect.bottom;
//设置一个阀值来判断软键盘是否弹出
                    boolean visible = heightDifference > screenHeight / 3;
                    if (visible) {
                        isFirst = false;
                        if (listener != null) {
                            listener.onShowed(heightDifference);
                        }
                        rootView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                }
            }
        };
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(layoutListener);
    }


    /** * 判断软键盘是否弹出
     * * @param rootView
     * @param listener
     *备注：在不用的时候记得移除OnGlobalLayoutListener
     */
    public static ViewTreeObserver.OnGlobalLayoutListener doMonitorSoftKeyWord(final View rootView, final OnSoftKeyWordShowListener listener) {
        final ViewTreeObserver.OnGlobalLayoutListener layoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                final Rect rect = new Rect();
                rootView.getWindowVisibleDisplayFrame(rect);
                final int screenHeight = rootView.getRootView().getHeight();
//                Log.e("TAG",rect.bottom+"#"+screenHeight);
                final int heightDifference = screenHeight - rect.bottom;
                boolean visible = heightDifference > screenHeight / 3;
                if (listener != null)
                    listener.hasShow(visible);
            }
        };
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(layoutListener);
        return layoutListener;
    }

}
