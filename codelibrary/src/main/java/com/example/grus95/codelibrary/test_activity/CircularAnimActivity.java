package com.example.grus95.codelibrary.test_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.grus95.baselibrary.animation.circular_anim.CircularAnim;
import com.example.grus95.baselibrary.ui.activity.BaseActivity;
import com.example.grus95.codelibrary.MainActivity;
import com.example.grus95.codelibrary.R;

public class CircularAnimActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initFirst() {

    }

    @Override
    protected int setContentView() {
        return R.layout.activity_circular_anim;
    }

    @Override
    protected void initView() {
        if (getSupportActionBar() != null) {
            // 默认左上角按钮可以点击
            getSupportActionBar().setHomeButtonEnabled(true);
            // 默认显示左上角返回按钮
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // 添加返回过渡动画.
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void showColorToActivity(View view) {
        // 先将颜色展出铺满，然后启动新的Activity
        CircularAnim.fullActivity(CircularAnimActivity.this, view)
                .colorOrImageRes(R.color.color_theme_main)
                .go(new CircularAnim.OnAnimationEndListener() {
                    @Override
                    public void onAnimationEnd() {
                        startActivity(new Intent(CircularAnimActivity.this, CircularAnimActivity.class));
                        finish();
                    }
                });
    }

    public void showLoadingToActivity(View view) {

        final ProgressBar mProgressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
        CircularAnim.hide(view)
                .endRadius(mProgressBar2.getHeight() / 2)
                .go(new CircularAnim.OnAnimationEndListener() {
                    @Override
                    public void onAnimationEnd() {
                        mProgressBar2.setVisibility(View.VISIBLE);
                        mProgressBar2.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                CircularAnim.fullActivity(CircularAnimActivity.this, mProgressBar2)
                                        .colorOrImageRes(R.color.color_theme_main)
                                        .go(new CircularAnim.OnAnimationEndListener() {
                                            @Override
                                            public void onAnimationEnd() {
                                                startActivity(new Intent(CircularAnimActivity.this, CircularAnimActivity.class));
                                                finish();
                                            }
                                        });
                            }
                        }, 3000);
                    }
                });
    }
}
