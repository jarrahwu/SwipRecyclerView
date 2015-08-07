package com.stkj.swiperecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.stkj.recyclerviewlibary.SwipeRecyclerView;

public class RecyclerViewSwipeSample extends AppCompatActivity {

    private SwipeRecyclerView mSwipeRecyclerView;
    private Adapter mAdapter;
    private static android.os.Handler DelayHandler = new android.os.Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_recycler_view_sample);
        mSwipeRecyclerView = (SwipeRecyclerView) findViewById(R.id.swipe);
        mAdapter = new Adapter(this);
        mSwipeRecyclerView.setAdapter(mAdapter);
        mAdapter.addAll(MyItem.getHardItems());

        mSwipeRecyclerView.setSwipeListener(new SwipeRecyclerView.SwipeListener() {
            @Override
            public void onRefresh() {
                DelayHandler.postDelayed(new RunRefresh(), 2000);
            }

            @Override
            public void onLoadMore(int current_page) {
                DelayHandler.postDelayed(new RunLoadMore(), 2000);
            }
        });
    }

    private class RunRefresh implements Runnable {

        @Override
        public void run() {
            mAdapter.clear();
            mAdapter.addAll(MyItem.getHardItems());
            mSwipeRecyclerView.setRefreshing(false);
        }
    }

    private class RunLoadMore implements Runnable {

        @Override
        public void run() {
            mAdapter.add(MyItem.newItem("on load more"));
            mSwipeRecyclerView.setRefreshing(false);
        }
    }

}
