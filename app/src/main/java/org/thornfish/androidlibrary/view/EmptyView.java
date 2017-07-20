package org.thornfish.androidlibrary.view;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.thornfish.androidlibrary.R;


/**
 * Created by Administrator on 2017/4/6.
 *
 */

public class EmptyView {


   public static View EmptyView(Activity context, View refreshlistview, String toast){
       View inflate = context.getLayoutInflater().inflate(
               R.layout.inflate_emptyview, null);

       TextView mmessage = (TextView) inflate.findViewById(R.id.tv_message);
       ImageView img_null = (ImageView) inflate.findViewById(R.id.img_null);
       ViewGroup parentView = (ViewGroup) refreshlistview.getParent();
       parentView.addView(inflate, 1); // 你需要在这儿设置正确的位置，以达到你需要的效果。
       mmessage.setText(toast);
       img_null.setImageResource(R.mipmap.ic_launcher);
  return inflate;
   }

}
