package com.example.grus95.codelibrary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.grus95.baselibrary.animation.circular_anim.CircularAnim;
import com.example.grus95.baselibrary.recycler_refresh_layout.realization_demo.demo.OpenProjectRecyclerFragment;
import com.example.grus95.baselibrary.ui.activity.BaseActivity;
import com.example.grus95.codelibrary.test_activity.CircularAnimActivity;
import com.example.grus95.codelibrary.test_activity.RecycleLayoutActivity;

/**
 * Created by grus95 on 16/8/31
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initFirst() {

    }

    @Override
    protected int setContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

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

    public void goToRecycleLayoutActivity(View view) {
        startActivity(new Intent(this, RecycleLayoutActivity.class));
    }

    public void goToAnimationActivity(View view) {
        CircularAnim.fullActivity(MainActivity.this, view)
                .go(new CircularAnim.OnAnimationEndListener() {
                    @Override
                    public void onAnimationEnd() {
                        startActivity(new Intent(MainActivity.this, CircularAnimActivity.class));
                    }
                });
    }
}
