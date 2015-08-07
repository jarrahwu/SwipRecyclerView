package com.stkj.swiperecyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SelectActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        findViewById(R.id.btn0).setOnClickListener(this);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
    }

    private Intent newIntent(Class cls) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        return intent;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn0) {
            startActivity(newIntent(RecyclerViewOriginalSample.class));
        }

        if(v.getId() == R.id.btn1) {
            startActivity(newIntent(RecyclerViewSwipeSample.class));
        }

        if(v.getId() == R.id.btn2) {
            startActivity(newIntent(RecyclerViewDecorationSample.class));
        }

        if(v.getId() == R.id.btn3) {
            startActivity(newIntent(RecyclerViewAnimatorSample.class));
        }

        if(v.getId() == R.id.btn4) {
            startActivity(newIntent(RecyclerViewMultiTypeSample.class));
        }

        if(v.getId() == R.id.btn5) {
            startActivity(newIntent(RecyclerViewStaggeredSample.class));
        }
    }
}
