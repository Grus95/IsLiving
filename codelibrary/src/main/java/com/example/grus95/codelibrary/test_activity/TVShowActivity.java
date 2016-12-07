package com.example.grus95.codelibrary.test_activity;

import android.content.res.Configuration;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.grus95.baselibrary.ui.activity.BaseActivity;
import com.example.grus95.codelibrary.R;
import com.grus95.thisvideoplayerlibrary.listener.OnPlayerBackListener;
import com.grus95.thisvideoplayerlibrary.listener.OnShowThumbnailListener;
import com.grus95.thisvideoplayerlibrary.widget.PlayStateParams;
import com.grus95.thisvideoplayerlibrary.widget.PlayerView;

public class TVShowActivity extends BaseActivity {

    private PlayerView player;
    private View rootView;
    private String videoTitle;
    private String videoUrl;

    @Override
    protected void initFirst() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    protected Object setContentView() {
        rootView = getLayoutInflater().from(this).inflate(R.layout.simple_player_view_player, null);
        return rootView;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void setToolbar() {
        setToolbarHide();
    }

    @Override
    protected void initData() {
        videoTitle = getIntent().getStringExtra("TVTitle");
        videoUrl = getIntent().getStringExtra("TVUrl");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initOther() {
        player = new PlayerView(this, rootView)
                .setTitle(videoTitle)
                .setScaleType(PlayStateParams.fitparent)
                .forbidTouch(false)
                .hideMenu(true)
                .showThumbnail(new OnShowThumbnailListener() {
                    @Override
                    public void onShowThumbnail(ImageView ivThumbnail) {
//                        Glide.with(ctx)
//                                .load("http://i8.17173.itc.cn/v3/outcms/2014/05/14/1400067215493_image_8532.jpg")
//                                .placeholder(R.color.cl_default)
//                                .error(R.color.cl_error)
//                                .into(ivThumbnail);
                    }
                })
                .setPlaySource(videoUrl)
                .setPlayerBackListener(new OnPlayerBackListener() {
                    @Override
                    public void onPlayerBack() {
                        //这里可以简单播放器点击返回键
                        onBackPressed();
                    }
                })
                .startPlay();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (player != null) {
            player.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (player != null) {
            player.onResume();
        }
    }

    @Override
    protected void onDestroy() {
        if (player != null) {
            player.onDestroy();
        }
        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (player != null) {
            player.onConfigurationChanged(newConfig);
        }
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
        if (player != null && player.onBackPressed()) {
            return;
        }
        super.onBackPressed();
        // 添加返回过渡动画.
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
