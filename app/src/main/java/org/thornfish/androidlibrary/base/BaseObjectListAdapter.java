package org.thornfish.androidlibrary.base;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.Collection;
import java.util.List;

public class BaseObjectListAdapter extends BaseAdapter {

    protected Context mContext;
    protected LayoutInflater mInflater;
    protected List<? extends Object> mDatas;
    private int mPerPageSize = 10;

    public BaseObjectListAdapter(Context context,
                                 List<Object> datas) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        if (datas != null) {
            this.mDatas = datas;
        }
    }

    public BaseObjectListAdapter(Activity mContext) {
        this(mContext,10);
    }

    public BaseObjectListAdapter(Activity mContext, int mPerPageSize){
        this.mContext = mContext;
        this.mPerPageSize = mPerPageSize;
    }

    public List<? extends Object> getmDatas() {
        return mDatas;
    }

    public void setmDatas(List<? extends Object> mDatas) {
        this.mDatas = mDatas;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
     return mDatas==null?0:mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas==null?null:mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    /**
     * 获取当前页
     * @return 当前页
     */
    public int getPageNo(){
        return (getCount() / mPerPageSize) + 1;
    }

//    /**
//     * 添加数据
//     * @paramitem 数据项
//     */
//    public boolean addItem(Object object){
//        return mDatas.add(object);
//    }
//
//    /**
//     * 在指定索引位置添加数据
//     * @param location 索引
//     * @param object 数据
//     */
//    public void addItem(int location,Object object){
//        mDatas.add(location, object);
//    }
//
//    /**
//     * 集合方式添加数据
//     * @param collection 集合
//     */
//    public boolean addItem(Collection<? extends Object> collection){
//        return mDatas.addAll(collection);
//    }
//
//    /**
//     * 在指定索引位置添加数据集合
//     * @param location 索引
//     * @param collection 数据集合
//     */
//    public boolean addItem(int location,Collection<? extends Object> collection){
//        return mDatas.addAll(location,collection);
//    }

    /**
     * 移除指定对象数据
     * @param object 移除对象
     * @return 是否移除成功
     */
    public boolean removeItem(Object object){
        return mDatas.remove(object);
    }

    /**
     * 移除指定索引位置对象
     * @param location 删除对象索引位置
     * @return 被删除的对象
     */
    public Object removeItem(int location){
        return mDatas.remove(location);
    }

    /**
     * 移除指定集合对象
     * @param collection 待移除的集合
     * @return 是否移除成功
     */
    public boolean removeAll(Collection<? extends Object> collection){
        return mDatas.removeAll(collection);
    }


//    public void setItem(int position,Object object){
//        mDatas.set(position, object);
//    }

    /**
     * 清空数据
     */
    public void clear() {
        mDatas.clear();
    }

}
