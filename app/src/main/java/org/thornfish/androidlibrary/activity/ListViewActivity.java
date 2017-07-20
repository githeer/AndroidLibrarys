package org.thornfish.androidlibrary.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.thornfish.pulltorefresh.refresh.PullToRefreshBase;
import com.thornfish.pulltorefresh.refresh.PullToRefreshListView;
import com.zhy.http.okhttp.callback.Callback;

import org.thornfish.androidlibrary.Model.ListViewEntity;
import org.thornfish.androidlibrary.R;
import org.thornfish.androidlibrary.adapter.ListViewAdapter;
import org.thornfish.androidlibrary.base.BaseActivity;
import org.thornfish.androidlibrary.network.BaseCallback;
import org.thornfish.androidlibrary.network.FastJsonUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

import static org.thornfish.androidlibrary.view.EmptyView.EmptyView;


public class ListViewActivity extends BaseActivity implements PullToRefreshBase.OnRefreshListener {


    @InjectView(R.id.listview)
    PullToRefreshListView listview;

    private ListView listView2;
    private ListViewAdapter adapter;
    private ListViewEntity entity;
    private ListViewEntity liswentity;
    private ArrayList<ListViewEntity.RespdataBean> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        ButterKnife.inject(this);
        aInit();
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void initViews() {
//        headTitle.setText(getString(R.string.doll));
        TextView textView = new TextView(this);
        textView.setHeight(40);
        textView.setBackgroundColor(ContextCompat.getColor(this, R.color.bg_ea));

        listView2 = listview.getRefreshableView();
        listView2.setDividerHeight(0);
        listView2.addFooterView(textView);
        listview.setEmptyView(EmptyView(this, listview, "暂无"));
        listview.setPullLoadEnabled(true);
        listview.setScrollLoadEnabled(false);
        listview.setOnRefreshListener(this);
        adapter = new ListViewAdapter(this, null);
        listview.setAdapter(adapter);
        listview.onRefreshComplete();
    }

    @Override
    protected void init() {
        page = 1;
//        HttpPostGet.POST_BABYLIST(this, SpUtils.getStringSP(this, "user", "uid"), String.valueOf(page), "1", callback);
        list = new ArrayList<>();
    }

    @Override
    protected void initListening() {
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    if (entity != null) {
                        if (entity.getRespdata().get(position).getCheck() == null || !entity.getRespdata().get(position).getCheck()) {
                            entity.getRespdata().get(position).setCheck(true);
                        } else {
                            entity.getRespdata().get(position).setCheck(false);
                        }
                        adapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private int page;//页码
    private Callback callback = new BaseCallback(this) {

        @Override
        protected void onContent(String content) {
            if (content != null && JSONObject.parseObject(content) != null) {
                if (JSONObject.parseObject(content).getString("respcode") != null) {

                    if (JSONObject.parseObject(content).getString("respcode").equals("0")) {
                        try {
                            entity = FastJsonUtils.toBean(content, ListViewEntity.class);
                            List<ListViewEntity.RespdataBean> news = entity.getRespdata();
                            if (page == 1) {
                                liswentity = entity;
                                adapter.setmDatas(news);
                                adapter.notifyDataSetChanged();
                            } else if (page > 1) {
                                if (news.size() == 0) {
                                    entity = liswentity;
                                    showLongToast("就这么多啦！");
                                } else {
                                    news.addAll(0, (Collection<? extends ListViewEntity.RespdataBean>) adapter.getmDatas());
                                    adapter.setmDatas(news);
                                    adapter.notifyDataSetChanged();
                                    liswentity = entity;
                                }
                            }
                            isF = true;
                            Refresh();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        showShortToast(JSONObject.parseObject(content).getString("respmsg"));
                    }
                }
            }
        }

        @Override
        public void onError(Call call, Exception e, int i) {

            Log.e("Activity", e.toString());
            if (page > 1) {
                page--;
            }
            isF = true;
            Refresh();
        }

        @Override
        public void onResponse(String o, int i) {

        }

        @Override
        public void onAfter(int id) {
            super.onAfter(id);

        }
    };

    private Callback excallback = new BaseCallback(this) {

        @Override
        protected void onContent(String content) {

            if (content != null && JSONObject.parseObject(content) != null) {
                if (JSONObject.parseObject(content).getString("respcode") != null) {
                    if (JSONObject.parseObject(content).getString("respcode").equals("0")) {
                        showShortToast(JSONObject.parseObject(content).getString("respmsg"));
                        loadData(0);
                    } else {
                        showShortToast(JSONObject.parseObject(content).getString("respmsg"));
                    }
                }
            }
        }

        @Override
        public void onError(Call call, Exception e, int i) {

            Log.e("Activity", e.toString());
        }

        @Override
        public void onResponse(String o, int i) {
        }
    };

    private boolean isF = false;
    private boolean isT = false;


    public void loadData(final int typ) {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                isT = true;
                Refresh();
            }
        }, 1000);
        switch (typ) {
            case 0:
                page = 1;
                isT = true;
//                HttpPostGet.POST_LIST(this, SpUtils.getStringSP(this, "user", "uid"), String.valueOf(page), "1", callback);
                break;
            case 1:
                page++;
                isT = true;
//                HttpPostGet.POST_LIST(this, SpUtils.getStringSP(this, "user", "uid"), String.valueOf(page), "1", callback);
                break;
        }
    }

    private void Refresh() {
        if (isF && isT) {
            isF = false;
            isT = false;
            listview.onRefreshComplete();
        }
    }



    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        loadData(0);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        loadData(1);
    }
}
