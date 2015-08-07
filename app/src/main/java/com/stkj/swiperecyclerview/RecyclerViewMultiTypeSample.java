package com.stkj.swiperecyclerview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.stkj.recyclerviewlibary.MultiTypeAdapter;
import com.stkj.recyclerviewlibary.RecyclerItemClickListener;
import com.stkj.recyclerviewlibary.SwipeRecyclerView;

import jp.wasabeef.recyclerview.animators.FlipInBottomXAnimator1;

public class RecyclerViewMultiTypeSample extends AppCompatActivity {

    private static final int TEXT = 0x010;
    private static final int IMAGE = 0x001;

    private SwipeRecyclerView mSwipeRecyclerView;
    private Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_recycler_view_sample);
        mSwipeRecyclerView = (SwipeRecyclerView) findViewById(R.id.swipe);
        mAdapter = new Adapter(this);
        mSwipeRecyclerView.setAdapter(mAdapter);
        for(int i =0; i<30; i++) {
            mAdapter.add(new TypeItem(TEXT, "t", R.drawable.d0));
        }

        mSwipeRecyclerView.getRecyclerView().setItemAnimator(new FlipInBottomXAnimator1());
        mSwipeRecyclerView.getRecyclerView().addOnItemTouchListener(new RecyclerItemClickListener(this) {
            @Override
            public void onItemClick(View childView, int childLayoutPosition) {
                super.onItemClick(childView, childLayoutPosition);
                mAdapter.getItem(childLayoutPosition).type = IMAGE;
                mAdapter.notifyItemChanged(childLayoutPosition);
            }
        });
    }

    private class Adapter extends MultiTypeAdapter<TypeItem, RecyclerView.ViewHolder> {



        public Adapter(Context context) {
            super(context);
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            switch (viewType) {
                case TEXT:
                    return TextHolder.newInstance(mContext);
                case IMAGE:
                    return ImageHolder.newInstance(mContext);
                default:
                    return TextHolder.newInstance(mContext);
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            final int viewType = getItemViewType(position);
            switch (viewType) {
                case TEXT:
                    break;
                case IMAGE:
                    ImageHolder imageHolder = (ImageHolder) holder;
                    imageHolder.imageView.setImageResource(getItem(position).res);
                    break;
            }
        }
    }

    public static class TextHolder extends RecyclerView.ViewHolder {

        public TextHolder(View itemView) {
            super(itemView);
        }

        public static RecyclerView.ViewHolder newInstance(Context context) {
            LayoutInflater lf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = lf.inflate(R.layout.view_animator_item, null, false);
            TextHolder th = new TextHolder(view);
            return th;
        }
    }

    public static class ImageHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public ImageHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image);
        }

        public static RecyclerView.ViewHolder newInstance(Context context) {
            LayoutInflater lf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = lf.inflate(R.layout.view_image_item, null, false);
            ImageHolder ih = new ImageHolder(view);
            return ih;
        }
    }

    private static class TypeItem extends MyItem implements MultiTypeAdapter.TypeMapping{

        private int type;

        public TypeItem(int type, String title, int res) {
            this.type = type;
            this.title = title;
            this.res = res;
        }

        @Override
        public int getTypeId() {
            return this.type;
        }
    }
}
