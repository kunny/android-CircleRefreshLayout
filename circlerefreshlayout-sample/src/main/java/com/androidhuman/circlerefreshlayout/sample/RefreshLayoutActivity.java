package com.androidhuman.circlerefreshlayout.sample;

import com.androidhuman.circlerefreshlayout.SwipeRefreshLayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class RefreshLayoutActivity extends AppCompatActivity
        implements SwipeRefreshLayout.OnRefreshListener {

    public static final String KEY_TITLE = "title";

    public static final String KEY_REFRESH_DRAWABLE_STYLE = "refresh_drawable_style";

    public static final String KEY_PULL_POSITION = "pull_position";

    SwipeRefreshLayout swlRefresh;

    RecyclerView rlList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refreshlayout);

        String title = getIntent().getStringExtra(KEY_TITLE);
        if (null != title) {
            setTitle(title);
        }

        swlRefresh = (SwipeRefreshLayout) findViewById(R.id.srl_activity_refreshlayout_refresh);
        rlList = (RecyclerView) findViewById(R.id.rv_list);

        setupSwipeRefreshLayout(swlRefresh, getIntent().getExtras());

        rlList.setLayoutManager(new LinearLayoutManager(this));
        rlList.setAdapter(new ItemAdapter());
    }

    private void setupSwipeRefreshLayout(SwipeRefreshLayout view, @Nullable Bundle extras) {
        view.setColorSchemeResources(R.color.colorAccent);
        view.setOnRefreshListener(this);

        if (null == extras) {
            return;
        }

        @SwipeRefreshLayout.RefreshDrawableStyle
        int style = extras.getInt(KEY_REFRESH_DRAWABLE_STYLE, SwipeRefreshLayout.CIRCLE);
        view.setRefreshDrawableStyle(style);

        @SwipeRefreshLayout.PullPosition
        int position = extras.getInt(KEY_PULL_POSITION, Gravity.TOP);
        view.setPullPosition(position);
    }

    @Override
    public void onRefresh() {
        Toast.makeText(getApplicationContext(), "Refreshing...", Toast.LENGTH_SHORT).show();
    }

    class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {

        @Override
        public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ItemHolder(parent);
        }

        @Override
        public void onBindViewHolder(ItemHolder holder, int position) {
            holder.text.setText("Item " + position);
        }

        @Override
        public int getItemCount() {
            return 30;
        }
    }

    class ItemHolder extends RecyclerView.ViewHolder {

        public TextView text;

        public ItemHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_simple, parent, false));
            text = (TextView) itemView.findViewById(R.id.tv_item_simple);
        }
    }
}
