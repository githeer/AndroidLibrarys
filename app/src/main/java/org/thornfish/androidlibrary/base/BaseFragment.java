package org.thornfish.androidlibrary.base;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.thornfish.androidlibrary.application.Application;
import org.thornfish.androidlibrary.utils.ToastUtil;


public abstract class BaseFragment extends Fragment{
    protected Application mApplication;
    protected FragmentActivity mActivity;
    protected Context mContext;
    protected View mView;


    public BaseFragment() {
        super();
    }

    public BaseFragment(Application application, Activity activity, Context context) {
        mApplication = application;
        mActivity = (FragmentActivity) activity;
        mContext = context;
    }

    // 缓存片段
    public View FragmentCache(int id, LayoutInflater inflater, ViewGroup container) {
        if (mView == null) {
            mView = inflater.inflate(id, container, false);
            initViews();
            initEvents();
            init();
        }
        ViewGroup parent = (ViewGroup) mView.getParent();
        if (parent != null) {
            parent.removeView(mView);
        }

        return mView;
    }

    // 片段
    public View NoFragmentCache(int id, LayoutInflater inflater, ViewGroup container) {

        mView = inflater.inflate(id, container, false);
        initViews();
        initEvents();
        init();

        return mView;
    }

    public void FragmentCache(View view) {
        ViewGroup parent = (ViewGroup) mView.getParent();
        if (parent != null) {
            parent.removeView(mView);
        }
    }

    protected void onInvisible(View view) {
        mView = view;
    }

    protected abstract void initViews();

    protected abstract void initEvents();

    protected abstract void init();

    public View findViewById(int id) {
        return mView.findViewById(id);
    }

    /**
     * 短暂显示Toast提示(来自res)
     **/
    protected void showShortToast(int resId) {
        ToastUtil.showMessageShort(getActivity(), getString(resId));
    }

    /**
     * 短暂显示Toast提示(来自String)
     **/
    protected void showShortToast(String text) {
        ToastUtil.showMessageShort(getActivity(), text);
    }

    /**
     * 长时间显示Toast提示(来自res)
     **/
    protected void showLongToast(int resId) {
        ToastUtil.showMessageLong(getActivity(), getString(resId));
    }

    /**
     * 长时间显示Toast提示(来自String)
     **/
    protected void showLongToast(String text) {
        ToastUtil.showMessageLong(getActivity(), text);
    }


    /**
     * 通过Class跳转界面
     **/
    protected void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    protected void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        // overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    /**
     * 含有Bundle带返回结果通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
        // overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    /**
     * 通过Action跳转界面
     **/
    protected void startActivity(String action) {
        startActivity(action, null);
    }

    /**
     * 含有Bundle通过Action跳转界面
     **/
    protected void startActivity(String action, Bundle bundle) {
        Intent intent = new Intent();
        intent.setAction(action);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        // overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

}
