package org.thornfish.androidlibrary.utils;

import android.content.Context;
import android.widget.ListView;

import com.thornfish.pulltorefresh.refresh.PullToRefreshBase;
import com.thornfish.pulltorefresh.refresh.PullToRefreshListView;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;


public abstract class TPullData<T> extends Callback {
	public final static int Refresh=1;//上拉
	public final static int LoadMore=0;//下拉
	
//	public abstract void onPullData(VolleyListener listener,int page); //请求触发类
	public abstract void onPullData(Callback listener, boolean isfrist, int page); //请求触发类
	public abstract void onPullResponse(String arg0,int Loadtype );//请求错误
	public abstract void onPullErrorResponse(Exception arg0,int Loadtype);//请求返回
	
	private int onpage=1;//临时page
	private int page=1;
	private int Loadtype;//拉动类型  0下拉 1上啦
	private Context context;
	private PullToRefreshListView mPullRecyclerView;
	private boolean isLoad=true;

	public TPullData(Context context,final PullToRefreshListView mPullRecyclerView2){
		this.context = context;
		this.mPullRecyclerView = mPullRecyclerView2;

		mPullRecyclerView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
			@Override
			public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
				Loadtype=Refresh;
				getData(Loadtype,false);
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
				if (isLoad) {
					Loadtype=LoadMore;
					getData(Loadtype,false);
				}else{
					PullEnd(LoadMore, false);
				}
			}
		});
		
	}
	
	public  void PullEnd(int Loadtype,boolean isok){
		if (isok) {
			page=onpage;
		}
		if (mPullRecyclerView!=null) {
			mPullRecyclerView.onRefreshComplete();
		}
	}
	
	public void getPullDownToRefresh( boolean isfrist){
		Loadtype=Refresh;
		getData(Loadtype,isfrist);
	}
	
	public void getPullUpToRefresh(boolean isfrist){
		Loadtype=LoadMore;
		getData(Loadtype,isfrist);
	}
	
	public void getData(int Loadtype, boolean isfrist){
		onpage = page;
		if (Loadtype==Refresh) {
			onpage=1;
		}else{
			onpage++;
		}
		onPullData(this,isfrist, onpage);
	}

	/**
	 * Thread Pool Thread
	 *
	 * @param response
	 */
	@Override
	public  T parseNetworkResponse(Response response, int id){
		try{
			onPullResponse(response.body().string(),Loadtype);
		}catch (IOException e){
			onPullResponse("",Loadtype);
		}

		return null;
	}

	@Override
	public  void onError(Call call, Exception e, int id){
		onPullErrorResponse(e, Loadtype);
		PullEnd(Loadtype, false);
	}

	@Override
	public  void onResponse(Object response, int id){

	}

	/**
	 * UI Thread
	 *
	 * @param request
	 */
	@Override
	public void onBefore(Request request, int id){
	}

	/**
	 * UI Thread
	 *
	 * @param
	 */
	@Override
	public void onAfter(int id){
	}
	
//	@Override
//	public void onResponse(String arg0) {
//		onPullResponse(arg0,Loadtype);
//	}
//
//	@Override
//	public void onErrorResponse(VolleyError arg0) {
//		onPullErrorResponse(arg0, Loadtype);
//		PullEnd(Loadtype, false);
//	}
	
	public int getOnpage() {
		return onpage;
	}
	
	public void setOnpage(int onpage) {
		this.onpage = onpage;
	}
	
	public void setLoad(boolean isLoad){
		this.isLoad = isLoad;
	}
	public void onPullData(Callback listener, int page) {
		
	}
}
