package com.example.grus95.baselibrary.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.grus95.baselibrary.BaseApplication;
import com.example.grus95.baselibrary.R;
import com.example.grus95.baselibrary.manager.UIManager;


/**
 * 基类Activity
 * Created by grus95 on 16/8/31
 */
public abstract class BaseActivity extends AppCompatActivity {

    private LinearLayout rootLayout;
    private TitleBar mTitleBar;
    private Toolbar mToolbar;

    public static Context ctx;

    protected Bundle savedInstanceState;


    protected Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out);

        BaseApplication.getApp().addActivity(this);
        ctx = this;
        this.savedInstanceState = savedInstanceState;

        super.setContentView(R.layout.activity_base);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }

        initFirst();

        Object contentViewRes = setContentView();
        if (contentViewRes instanceof Integer) {
            setContentView((Integer) contentViewRes);
        } else if (contentViewRes instanceof View) {
            setContentView((View) contentViewRes);
        }
        init();
    }


    private void init() {
        initToolbar();
        initView();
        setToolbar();
        initData();
        initListener();
        initOther();
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (null != mToolbar) {
            mTitleBar = new TitleBar();
            mTitleBar.init(mToolbar);
            setSupportActionBar(mToolbar);
        }
    }

    protected abstract void initFirst();

    protected abstract Object setContentView();

    protected abstract void initView();

    protected abstract void setToolbar();

    protected abstract void initData();

    protected abstract void initListener();

    protected abstract void initOther();


    @Override
    public void setContentView(@LayoutRes int layoutId) {
        setContentView(View.inflate(this, layoutId, null));
    }

    @Override
    public void setContentView(View view) {
        rootLayout = (LinearLayout) findViewById(R.id.root_layout);
        if (null == rootLayout) return;
        rootLayout.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        initToolbar();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseApplication.getApp().removeActivity(this);
    }

    @Override
    public void finish() {
        super.finish();
    }
    public void setDialogDividerColor(Dialog dialog) {
        Context context = dialog.getContext();
        int divierId = context.getResources().getIdentifier(
                "android:id/titleDivider", null, null);
        View divider = dialog.findViewById(divierId);
        if (null != divider)
            divider.setBackgroundColor(getResources().getColor(
                    R.color.divider_dialog_header));
    }

    Dialog showDialog(int titleId, int messageId, int btnPId, int btnNId,
                      DialogInterface.OnClickListener lp,
                      DialogInterface.OnClickListener ln) {
        return showDialog(getString(titleId), getString(messageId), getString(btnPId),
                getString(btnNId), lp, ln);
    }

    Dialog showDialog(String title, String message, String btnP, String btnN,
                      DialogInterface.OnClickListener lp,
                      DialogInterface.OnClickListener ln) {
        if (isFinishing())
            return null;
        Dialog dialog = new AlertDialog.Builder(this).setTitle(title)
                .setMessage(message).setPositiveButton(btnP, lp)
                .setNegativeButton(btnN, ln).create();
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        setDialogDividerColor(dialog);
        return dialog;
    }


    /***************************************
     * 设置ActionBar的方法
     ****************************************/

    /**
     * 隐藏Toolbar
     */
    public void setToolbarHide() {

        android.support.v7.app.ActionBar supportActionBar = getSupportActionBar();
        if (null != supportActionBar) {
            supportActionBar.hide();
            //去掉toolbar的
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
        }
    }

    /**
     * 显示Toolbar
     */
    public void setToolbarShow() {

        android.support.v7.app.ActionBar supportActionBar = getSupportActionBar();
        if (null != supportActionBar) {
            supportActionBar.show();
        }
    }

    public void setToolbarBackEnabled(boolean able) {
        if (null != mTitleBar) {
            mTitleBar.setBackEnabled(able, this);
        }
    }

    public void setToolbarBackEnabled(boolean able, View.OnClickListener l) {
        if (null != mTitleBar) {
            mTitleBar.setBackEnabled(able, l);
        }
    }

    /**
     * 设置Toolbar标题
     *
     * @param title 标题文字
     */
    public void setToolbarTitle(String title) {
        if (null != mTitleBar)
            mTitleBar.setTitleText(true, title, null);
    }


    /**
     * 设置Toolbar标题
     *
     * @param ResId 标题的资源
     */
    public void setToolbarTitle(@StringRes int ResId) {
        if (null != mTitleBar)
            mTitleBar.setTitleText(true, getString(ResId), null);
    }

    /**
     * 设置Toolbar标题
     *
     * @param title
     * @param l
     */
    public void setToolbarTitle(String title, View.OnClickListener l) {
        if (null != mTitleBar)
            mTitleBar.setTitleText(true, title, l);
    }

    /**
     * 设置Toolbar标题
     *
     * @param ResId
     * @param l
     */
    public void setToolbarTitle(@StringRes int ResId, View.OnClickListener l) {
        if (null != mTitleBar)
            mTitleBar.setTitleText(true, getString(ResId), l);
    }


    /**
     * 设置Toolbar的TabLayout
     *
     * @param tabs 选项卡的字
     */
    public void setToolbarTabLayout(String[] tabs) {
        if (null != mTitleBar)
            mTitleBar.setTabLayout(true, tabs);
    }

    /**
     * 设置Toolbar里TabLayout的ViewPager
     *
     * @param mViewPager
     */
    public void setToolbarTabLayoutViewPager(ViewPager mViewPager) {
        if (null != mViewPager && null != mViewPager.getAdapter() && null != mTitleBar)
            mTitleBar.setTabLayoutViewPager(mViewPager);
    }

    /**
     * 设置Toolbar里的LeftButton
     *
     * @param text
     * @param l
     */
    public void setToolbarLeftButton(String text, View.OnClickListener l) {
        if (null != mTitleBar)
            mTitleBar.setLeftButton(true, text, l);
    }

    /**
     * 设置Toolbar里的LeftButton
     *
     * @param ResId
     * @param l
     */
    public void setToolbarLeftButton(@StringRes int ResId, View.OnClickListener l) {
        if (null != mTitleBar)
            mTitleBar.setLeftButton(true, getString(ResId), l);
    }

    /**
     * 设置Toolbar里的RightButton
     *
     * @param text
     * @param l
     */
    public void setToolbarRightButton(String text, View.OnClickListener l) {
        if (null != mTitleBar)
            mTitleBar.setRightButton(true, text, l);
    }

    /**
     * 设置Toolbar里的RightButton
     *
     * @param ResId
     * @param l
     */
    public void setToolbarRightButton(@StringRes int ResId, View.OnClickListener l) {
        if (null != mTitleBar)
            mTitleBar.setRightButton(true, getString(ResId), l);
    }

    /**
     * 设置ToolBar的LeftImageButton
     *
     * @param ResId
     * @param l
     */
    public void setToolbarLeftImageButton(@DrawableRes int ResId, View.OnClickListener l) {
        if (null != mTitleBar)
            mTitleBar.setLeftImageButton(true, getResources().getDrawable(ResId), l);
    }

    /**
     * 设置ToolBar的LeftImageButton
     *
     * @param background_drawable
     * @param l
     */
    public void setToolbarLeftImageButton(Drawable background_drawable, View.OnClickListener l) {
        if (null != mTitleBar)
            mTitleBar.setLeftImageButton(true, background_drawable, l);
    }

    /**
     * 设置ToolBar的LeftImageButton以及它的tag
     *
     * @param ResId
     * @param tag
     * @param l
     */
    public void setToolbarLeftImageButton(@DrawableRes int ResId, Object tag, View.OnClickListener l) {
        if (null != mTitleBar)
            mTitleBar.setLeftImageButton(true, getResources().getDrawable(ResId), tag, l);
    }


    /**
     * 设置ToolBar的LeftImageButton以及它的tag
     *
     * @param background_drawable
     * @param l
     */
    public void setToolbarLeftImageButton(Drawable background_drawable, Object tag, View.OnClickListener l) {
        if (null != mTitleBar)
            mTitleBar.setLeftImageButton(true, background_drawable, tag, l);
    }

    /**
     * 设置ToolBar的RightImageButton
     *
     * @param ResId
     * @param l
     */
    public void setToolbarRightImageButton(@DrawableRes int ResId, View.OnClickListener l) {
        if (null != mTitleBar)
            mTitleBar.setRightImageButton(true, getResources().getDrawable(ResId), l);
    }


    /**
     * 设置ToolBar的RightImageButton
     *
     * @param background_drawable
     * @param l
     */
    public void setToolbarRightImageButton(Drawable background_drawable, View.OnClickListener l) {
        if (null != mTitleBar)
            mTitleBar.setRightImageButton(true, background_drawable, l);
    }


    /**
     * 设置ToolBar的RightImageButton以及它的tag
     *
     * @param ResId
     * @param tag
     * @param l
     */
    public void setToolbarRightImageButton(@DrawableRes int ResId, Object tag, View.OnClickListener l) {
        if (null != mTitleBar)
            mTitleBar.setRightImageButton(true, getResources().getDrawable(ResId), tag, l);
    }


    /**
     * 设置ToolBar的RightImageButton以及它的tag
     *
     * @param background_drawable
     * @param l
     */
    public void setToolbarRightImageButton(Drawable background_drawable, Object tag, View.OnClickListener l) {
        if (null != mTitleBar)
            mTitleBar.setRightImageButton(true, background_drawable, tag, l);
    }

    public void setToolbarLineHide() {
        if (null != mTitleBar)
            mTitleBar.hideViewLine();
    }


    public int getToolbarHeight() {
        ActionBar actionbar = getSupportActionBar();
        if (null != actionbar && actionbar.isShowing()) {
            TypedValue tv = new TypedValue();
            if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv,
                    true)) {
                return TypedValue.complexToDimensionPixelSize(tv.data,
                        getResources().getDisplayMetrics());
            }
        }
        return 0;
    }

    /**
     * 获取状态栏高度
     *
     * @return
     */
    public int getStatusBarHeight() {
        Class<?> c = null;
        Object obj = null;
        java.lang.reflect.Field field = null;
        int x = 0;
        int statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = getResources()
                    .getDimensionPixelSize(x);
            return statusBarHeight;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight;
    }


    /***************************************
     * 设置ActionBar的方法
     ****************************************/

    private class TitleBar {
        private ImageButton ibtn_toolbar_left, ibtn_toolbar_right;
        private Button btn_toolbar_left, btn_toolbar_right;
        private TextView tv_toolbar_title;
        private TabLayout tl_toolbar_title;

        private View view_toolbar_divider_bottom;

        public TitleBar() {

        }

        private void init(Toolbar mToolbar) {
            tv_toolbar_title = (TextView) mToolbar.findViewById(R.id.tv_toolbar_title);
            ibtn_toolbar_left = (ImageButton) mToolbar.findViewById(R.id.ibtn_toolbar_left);
            ibtn_toolbar_right = (ImageButton) mToolbar.findViewById(R.id.ibtn_toolbar_right);

            btn_toolbar_right = (Button) mToolbar.findViewById(R.id.btn_toolbar_right);
            btn_toolbar_left = (Button) mToolbar.findViewById(R.id.btn_toolbar_left);

            tl_toolbar_title = (TabLayout) mToolbar.findViewById(R.id.tl_toolbar_title);

            view_toolbar_divider_bottom = mToolbar.findViewById(R.id.view_toolbar_divider_bottom);
        }

        private void setBackEnabled(boolean able, final Activity activity) {
            if (able) {
                ibtn_toolbar_left.setVisibility(View.VISIBLE);
                ibtn_toolbar_left.setImageResource(R.mipmap.icon_nav_back);
                if (null != activity)
                    ibtn_toolbar_left.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            activity.finish();
                        }
                    });
            } else {
                ibtn_toolbar_left.setVisibility(View.INVISIBLE);
            }
        }

        private void setBackEnabled(boolean able, View.OnClickListener l) {
            if (able) {
                ibtn_toolbar_left.setVisibility(View.VISIBLE);
                ibtn_toolbar_left.setImageResource(R.mipmap.icon_nav_back);
                if (null != l) ibtn_toolbar_left.setOnClickListener(l);
            } else {
                ibtn_toolbar_left.setVisibility(View.INVISIBLE);
            }
        }

        private void setLeftButton(boolean isVisibility, String text, View.OnClickListener l) {
            if (isVisibility) {
                btn_toolbar_left.setVisibility(View.VISIBLE);
                btn_toolbar_left.setOnClickListener(l);
                btn_toolbar_left.setText(text);

                ibtn_toolbar_left.setVisibility(View.GONE);
            } else {
                btn_toolbar_left.setVisibility(View.GONE);
            }
        }

        private void setLeftImageButton(boolean isVisibility, Drawable background_drawable, View.OnClickListener l) {
            if (isVisibility) {
                ibtn_toolbar_left.setVisibility(View.VISIBLE);
                ibtn_toolbar_left.setOnClickListener(l);
                ibtn_toolbar_left.setBackgroundDrawable(background_drawable);

                btn_toolbar_left.setVisibility(View.GONE);
            } else {
                ibtn_toolbar_left.setVisibility(View.GONE);
            }
        }

        private void setLeftImageButton(boolean isVisibility, Drawable background_drawable, Object tag, View.OnClickListener l) {
            if (isVisibility) {
                ibtn_toolbar_left.setVisibility(View.VISIBLE);
                ibtn_toolbar_left.setOnClickListener(l);
                ibtn_toolbar_left.setBackgroundDrawable(background_drawable);
                ibtn_toolbar_left.setTag(tag);

                btn_toolbar_left.setVisibility(View.GONE);
            } else {
                ibtn_toolbar_left.setVisibility(View.GONE);
            }
        }

        private void setRightButton(boolean isVisibility, String text, View.OnClickListener l) {
            if (isVisibility) {
                btn_toolbar_right.setVisibility(View.VISIBLE);
                btn_toolbar_right.setOnClickListener(l);
                btn_toolbar_right.setText(text);

                ibtn_toolbar_right.setVisibility(View.GONE);
            } else {
                btn_toolbar_right.setVisibility(View.GONE);
            }
        }

        private void setRightImageButton(boolean isVisibility, Drawable background_drawable, View.OnClickListener l) {
            if (isVisibility) {
                ibtn_toolbar_right.setVisibility(View.VISIBLE);
                ibtn_toolbar_right.setOnClickListener(l);
                ibtn_toolbar_right.setBackgroundDrawable(background_drawable);

                btn_toolbar_right.setVisibility(View.GONE);
            } else {
                ibtn_toolbar_right.setVisibility(View.GONE);
            }
        }


        private void setRightImageButton(boolean isVisibility, Drawable background_drawable, Object tag, View.OnClickListener l) {
            if (isVisibility) {
                ibtn_toolbar_right.setVisibility(View.VISIBLE);
                ibtn_toolbar_right.setOnClickListener(l);
                ibtn_toolbar_right.setBackgroundDrawable(background_drawable);
                ibtn_toolbar_right.setTag(tag);

                btn_toolbar_right.setVisibility(View.GONE);
            } else {
                ibtn_toolbar_right.setVisibility(View.GONE);
            }
        }

        private void setTitleText(boolean isVisibility, String title, View.OnClickListener l) {
            if (isVisibility) {
                tv_toolbar_title.setText(title);
                tv_toolbar_title.setVisibility(View.VISIBLE);
                tl_toolbar_title.setVisibility(View.GONE);
                if (null != l) tl_toolbar_title.setOnClickListener(l);
            } else {
                tv_toolbar_title.setVisibility(View.GONE);
            }
        }


        private String[] currentTabs;

        private void setTabLayout(boolean isVisibility, String[] tabs) {
            if (isVisibility) {
                if (null == tabs) {
                    if (null == currentTabs) return;
                    tabs = currentTabs;
                }
                tl_toolbar_title.setVisibility(View.VISIBLE);

                if (currentTabs != tabs) {
                    tl_toolbar_title.removeAllTabs();
                    for (String string_tab : tabs) {
                        tl_toolbar_title.addTab(tl_toolbar_title.newTab().setText(string_tab));
                    }
                }

                int tabCount = tl_toolbar_title.getTabCount();
                int screenWidth = UIManager.getScreenWidth();
                if (tabCount < 4) {
                    UIManager.setViewWidth(tl_toolbar_title, screenWidth / 4 * tabCount);
                }

                currentTabs = tabs;
                //隐藏title
                tv_toolbar_title.setVisibility(View.GONE);
            } else {
                tl_toolbar_title.setVisibility(View.GONE);
            }
        }

        private void setTabLayoutViewPager(ViewPager mViewPager) {
            tl_toolbar_title.setupWithViewPager(mViewPager);
        }


        private void hideViewLine() {
            view_toolbar_divider_bottom.setVisibility(View.GONE);
        }
    }

}
