package com.stkj.swiperecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.stkj.recyclerviewlibary.RecyclerAdapter;
import com.stkj.recyclerviewlibary.SimpleViewHolder;

public class Adapter extends RecyclerAdapter<MyItem, RecyclerView.ViewHolder> {

    public Adapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return SimpleViewHolder.from(mContext, R.layout.simple_item);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ImageView iv = (ImageView) holder.itemView.findViewById(R.id.image);
        TextView tv = (TextView) holder.itemView.findViewById(R.id.text);
        iv.setImageResource(getItem(position).res);
        tv.setText(getItem(position).title);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyItem mi = new MyItem();
                mi.res = R.mipmap.ic_launcher;
                mi.title = "added manually";
                add(mi);
            }
        });
    }
}