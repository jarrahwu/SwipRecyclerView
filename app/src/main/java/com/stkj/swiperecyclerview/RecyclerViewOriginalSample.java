package com.stkj.swiperecyclerview;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.stkj.recyclerviewlibary.OnLoadMoreListener;
import com.stkj.recyclerviewlibary.RecyclerHelper;


public class RecyclerViewOriginalSample extends AppCompatActivity {

    private static final int MAX_COUNT = 40;
    RecyclerView mRv;
    Adapter mAdapter;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private MyOnLoadMoreListener ml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);

        mRv = (RecyclerView) findViewById(R.id.rv);
        mAdapter = new Adapter(this);
        mRv.setAdapter(mAdapter);
        mRv.setLayoutManager(RecyclerHelper.getVerticalLayout(this));
        mAdapter.addAll(MyItem.getHardItems());
        ml = new MyOnLoadMoreListener();

        mRv.addOnScrollListener(ml);
        mSwipeRefreshLayout.setOnRefreshListener(new MyOnRefreshListener());
    }

    public class RefreshRun implements Runnable {
        @Override
        public void run() {
            mAdapter.clear();
            mAdapter.addAll(MyItem.getHardItems());
            mSwipeRefreshLayout.setRefreshing(false);
            ml.reset();
        }
    }


    private class MyOnLoadMoreListener extends OnLoadMoreListener {
        public MyOnLoadMoreListener() {
            super((LinearLayoutManager) RecyclerViewOriginalSample.this.mRv.getLayoutManager());
        }

        @Override
        public void onLoadMore(int current_page) {
            mSwipeRefreshLayout.setRefreshing(true);
            DelayHandler.postDelayed(new LoadRun(), 200);
        }
    }

    public class LoadRun implements Runnable {

        @Override
        public void run() {
            mAdapter.addAll(MyItem.getHardItems());
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }



    private static android.os.Handler DelayHandler = new android.os.Handler();

    private class MyOnRefreshListener implements SwipeRefreshLayout.OnRefreshListener {
        @Override
        public void onRefresh() {
            DelayHandler.postDelayed(new RefreshRun(), 3000);
        }
    }
}
