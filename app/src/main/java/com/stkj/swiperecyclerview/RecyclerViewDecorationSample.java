package com.stkj.swiperecyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.stkj.recyclerviewlibary.RecyclerAdapter;
import com.stkj.recyclerviewlibary.SimpleViewHolder;
import com.stkj.recyclerviewlibary.SwipeRecyclerView;

public class RecyclerViewDecorationSample extends AppCompatActivity {

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
        mAdapter.addAll(MyItem.getImageHardItems());
//        mSwipeRecyclerView.getRecyclerView().setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
//        mSwipeRecyclerView.getRecyclerView().addItemDecoration(new MyItemDecoration());
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

    private class MyItemDecoration extends RecyclerView.ItemDecoration {

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDraw(c, parent, state);
            c.drawColor(getResources().getColor(R.color.material_deep_teal_500));
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.set(0, 0, 30, 30);
            super.getItemOffsets(outRect, view, parent, state);
        }
    }

    private class Adapter extends RecyclerAdapter<MyItem, SimpleViewHolder> {


        public Adapter(Context context) {
            super(context);
        }

        @Override
        public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final SimpleViewHolder sp = SimpleViewHolder.from(mContext, R.layout.view_image_item);
            sp.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    remove(getItem(sp.getLayoutPosition()));
                }
            });
            return sp;
        }

        @Override
        public void onBindViewHolder(SimpleViewHolder holder, final int position) {
            ImageView img = (ImageView) holder.findViewById(R.id.image);
            TextView text = (TextView) holder.findViewById(R.id.text);
            img.setImageResource(getItem(position).res);
            text.setText(getItem(position).title);
        }
    }


    private class RunRefresh implements Runnable {

        @Override
        public void run() {
            mAdapter.clear();
            mAdapter.addAll(MyItem.getImageHardItems());
            mSwipeRecyclerView.setRefreshing(false);
        }
    }

    private class RunLoadMore implements Runnable {

        @Override
        public void run() {
            LinearLayoutManager ll = (LinearLayoutManager) mSwipeRecyclerView.getRecyclerView().getLayoutManager();
            mAdapter.add(MyItem.newImageItem("on load more", ll.findFirstVisibleItemPosition()));
            mSwipeRecyclerView.setRefreshing(false);
        }
    }

}
