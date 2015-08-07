package com.stkj.swiperecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.stkj.recyclerviewlibary.SimpleViewHolder;

/**
 * Created by jarrah on 2015/8/6.
 */
public class AnimatorViewHolder extends SimpleViewHolder {

    public FrameLayout cardView;
    public View parent;
    public View[] children;

    private AnimatorViewHolder(View itemView) {
        super(itemView);
        cardView = (FrameLayout) findViewById(R.id.card);
        parent = findViewById(R.id.parent);
        children = new View[]{findViewById(R.id.child0), findViewById(R.id.child1), findViewById(R.id.child2)};
    }

    public static AnimatorViewHolder from(Context context, int res) {
        LayoutInflater lf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = lf.inflate(res, null, false);
        return new AnimatorViewHolder(itemView);
    }

}
