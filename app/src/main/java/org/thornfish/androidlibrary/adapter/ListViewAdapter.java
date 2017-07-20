package org.thornfish.androidlibrary.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhy.autolayout.utils.AutoUtils;

import org.thornfish.androidlibrary.Model.ListViewEntity;
import org.thornfish.androidlibrary.R;
import org.thornfish.androidlibrary.base.BaseObjectListAdapter;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * @name CaptionAdapter
 * @class
 * @anthor tank QQ:297890301
 * @time 2017/3/24 14:24
 * @change
 * @chang time
 * @class describe
 */

public class ListViewAdapter extends BaseObjectListAdapter {

    public ListViewAdapter(Context context, List<Object> listData) {
        super(context, listData);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.layout_list_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
            AutoUtils.autoSize(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ListViewEntity.RespdataBean s = (ListViewEntity.RespdataBean) getItem(position);

        viewHolder.dollItemTitle.setText(s.getTitle());
        viewHolder.dollItemInte.setText(s.getJifen());
        viewHolder.dollItemCurr.setText(s.getMoney());
        viewHolder.dollItemTime.setText(s.getTime());
        viewHolder.dollItemDelivery.setText(s.getValidtime());
        if (s.getCheck()!=null)
        viewHolder.dollItemCheckBox.setChecked(s.getCheck());
        else
        viewHolder.dollItemCheckBox.setChecked(false);
        Glide.with(mContext).load(s.getImgurl())
                .error(R.mipmap.ic_launcher)
                .into(viewHolder.dollItemImg);

        return convertView;
    }


    static class ViewHolder {
        @InjectView(R.id.doll_item_checkBox)
        CheckBox dollItemCheckBox;
        @InjectView(R.id.doll_item_img)
        ImageView dollItemImg;
        @InjectView(R.id.doll_item_title)
        TextView dollItemTitle;
        @InjectView(R.id.doll_item_inte)
        TextView dollItemInte;
        @InjectView(R.id.doll_item_curr)
        TextView dollItemCurr;
        @InjectView(R.id.doll_item_time)
        TextView dollItemTime;
        @InjectView(R.id.doll_item_delivery)
        TextView dollItemDelivery;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}