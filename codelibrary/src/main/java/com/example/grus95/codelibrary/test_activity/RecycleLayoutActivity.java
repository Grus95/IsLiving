package com.example.grus95.codelibrary.test_activity;

import android.os.Bundle;

import com.example.grus95.baselibrary.ui.activity.BaseActivity;
import com.example.grus95.codelibrary.R;
import com.example.grus95.codelibrary.test_activity.TVShowTool.TVShowRecyclerFragment;

public class RecycleLayoutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initFirst() {

    }

    @Override
    protected Object setContentView() {
        return R.layout.activity_recycle_layout;
    }

    @Override
    protected void initView() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.recycle_fragment_container, TVShowRecyclerFragment.newInstance())
                .commit();
    }

    @Override
    protected void setToolbar() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initOther() {

    }
}
