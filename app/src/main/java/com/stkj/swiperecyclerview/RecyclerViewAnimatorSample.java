

package com.stkj.swiperecyclerview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.stkj.recyclerviewlibary.RecyclerAdapter;
import com.stkj.recyclerviewlibary.SwipeRecyclerView;

import jp.wasabeef.recyclerview.animators.FlipInTopXAnimator;

public class RecyclerViewAnimatorSample extends AppCompatActivity {

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
        mSwipeRecyclerView.getRecyclerView().setItemAnimator(new FlipInTopXAnimator());
        mAdapter.addAll(MyItem.getAnimatorHardItems());
        mSwipeRecyclerView.setSwipeListener(new SwipeRecyclerView.SwipeListener() {
            @Override
            public void onRefresh() {
                DelayHandler.postDelayed(new RunRefresh(), 300);
            }

            @Override
            public void onLoadMore(int current_page) {
                DelayHandler.postDelayed(new RunLoadMore(), 300);
            }
        });
    }


    private class Adapter extends RecyclerAdapter<MyItem, AnimatorViewHolder> {


        private int index;

        public Adapter(Context context) {
            super(context);
        }

        @Override
        public AnimatorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final AnimatorViewHolder vh = AnimatorViewHolder.from(mContext, R.layout.view_animator_item);
            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    add(MyItem.newColorItem("added", getIndex()));
                }
            });
            return vh;
        }

        @Override
        public void onBindViewHolder(AnimatorViewHolder holder, final int position) {
            holder.cardView.setBackgroundColor(getItem(position).res);
        }
        public int getIndex() {
            index ++;
            return index;
        }
    }


    private class RunRefresh implements Runnable {

        @Override
        public void run() {
            mAdapter.clear();
            mAdapter.addAll(MyItem.getAnimatorHardItems());
            mSwipeRecyclerView.setRefreshing(false);
        }
    }

    private class RunLoadMore implements Runnable {

        @Override
        public void run() {
            LinearLayoutManager ll = (LinearLayoutManager) mSwipeRecyclerView.getRecyclerView().getLayoutManager();
            mAdapter.add(MyItem.newColorItem("on load more", mAdapter.getIndex()));
            mSwipeRecyclerView.setRefreshing(false);
        }
    }

}

