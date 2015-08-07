package com.stkj.swiperecyclerview;


import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import jp.wasabeef.recyclerview.animators.BaseItemAnimator;

public class ExpandAnimator extends BaseItemAnimator {

    public ExpandAnimator() {
        setRemoveDuration(2000);
    }

    @Override
    protected void animateRemoveImpl(final RecyclerView.ViewHolder holder) {

    }

    @Override
    protected void animateAddImpl(RecyclerView.ViewHolder holder) {
        ViewCompat.animate(wrap(holder).children[0])
                .rotationX(-180)
                .setDuration(getRemoveDuration())
                .setListener(new MyListener(holder))
                .start();
    }



    @Override
    protected void preAnimateAddImpl(RecyclerView.ViewHolder holder) {
        wrap(holder).parent.setVisibility(View.VISIBLE);
        wrap(holder).children[0].setVisibility(View.INVISIBLE);
        wrap(holder).children[1].setVisibility(View.GONE);
        wrap(holder).children[2].setVisibility(View.GONE);
    }



    public AnimatorViewHolder wrap(RecyclerView.ViewHolder vh) {
        AnimatorViewHolder avh = (AnimatorViewHolder) vh;
        return avh;
    }

    public class MyListener extends DefaultRemoveVpaListener {

        private AnimatorViewHolder mViewHolder;

        public MyListener(RecyclerView.ViewHolder holder) {
            super(holder);
            mViewHolder = (AnimatorViewHolder) holder;
        }

        @Override
        public void onAnimationStart(View view) {
            wrap(mViewHolder).children[0].setVisibility(View.VISIBLE);
            super.onAnimationStart(view);
        }

        @Override
        public void onAnimationEnd(View view) {
            super.onAnimationEnd(view);
        }
    }
}
