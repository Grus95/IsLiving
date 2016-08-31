package com.example.grus95.baselibrary.manager;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;

import com.example.grus95.baselibrary.BaseApplication;

import java.lang.reflect.Field;



public class UIManager {
    public static float DENSITY; // 屏幕的密度
    public static int WIDTH; // 屏幕的宽度 dp
    public static int HEIGHT; // 屏幕的高度 dp

    static int screenWidthDefault = 640;

    public static void init() {
        if (WIDTH == 0 || HEIGHT == 0) {
            DisplayMetrics ds = BaseApplication.getApp().getResources().getDisplayMetrics();
            DENSITY = ds.density;
            WIDTH = ds.widthPixels;
            HEIGHT = ds.heightPixels;
        }
    }

    public static int getScreenWidth() {
        init();
        return WIDTH;
    }

    public static int getScreenHeight() {
        init();
        return HEIGHT;
    }

    public static int getScaleLength(int length) {
        return length * getScreenWidth() / screenWidthDefault;
    }

    public static void setViewSquareLength(View view, int width) {
        setViewLength(view, width, width);
    }

    public static void setViewLength(View view, int width, int height) {
        LayoutParams params = view.getLayoutParams();
        if (null == params) {
            view.setLayoutParams(new LayoutParams(width, height));
        } else {
            params.width = width;
            params.height = height;
            view.setLayoutParams(params);
        }
    }

    public static void setViewHeight(View view, int height) {
        LayoutParams params = view.getLayoutParams();
        if (null == params) {
            view.setLayoutParams(new LayoutParams(0, height));
        } else {
            params.height = height;
            view.setLayoutParams(params);
        }
    }

    public static void setViewWidth(View view, int width) {
        LayoutParams params = view.getLayoutParams();
        if (null == params) {
            view.setLayoutParams(new LayoutParams(width, 0));
        } else {
            params.width = width;
            view.setLayoutParams(params);
        }
    }

    public static void setViewMargin(View view, int left, int top) {
        LayoutParams params = view.getLayoutParams();
        if (params instanceof MarginLayoutParams) {
            ((MarginLayoutParams) params).topMargin = top;
            ((MarginLayoutParams) params).leftMargin = left;
            view.setLayoutParams(params);
        }
    }

    public static void setViewMargin(View view, int bottom) {
        LayoutParams params = view.getLayoutParams();
        if (null == params)params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        if (params instanceof MarginLayoutParams) {
            ((MarginLayoutParams) params).bottomMargin = bottom;
            view.setLayoutParams(params);
        }
    }

    public static void setViewMargin(View view, int left, int top, int right,
                                     int bottom) {
        LayoutParams params = view.getLayoutParams();
        if (params instanceof MarginLayoutParams) {
            ((MarginLayoutParams) params).topMargin = top;
            ((MarginLayoutParams) params).leftMargin = left;
            ((MarginLayoutParams) params).rightMargin = right;
            ((MarginLayoutParams) params).bottomMargin = bottom;
        }
    }

    public static final float getHeightInPx() {
        final float height = BaseApplication.getApp().getResources().getDisplayMetrics().heightPixels;
        return height;
    }

    public static final float getWidthInPx() {
        final float width = BaseApplication.getApp().getResources().getDisplayMetrics().widthPixels;
        return width;
    }

    public static final int getHeightInDp() {
        final float height = BaseApplication.getApp().getResources().getDisplayMetrics().heightPixels;
        int heightInDp = pxTodp(height);
        return heightInDp;
    }

    public static final int getWidthInDp() {
        final float height = BaseApplication.getApp().getResources().getDisplayMetrics().heightPixels;
        int widthInDp = pxTodp(height);
        return widthInDp;
    }

    public static int toDip(int value) {
        return (int) (value * DENSITY);
    }

    /**
     * Convert Dp to Pixel 将dp转换为pixel
     */
    public static int dpToPx(float dp) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, BaseApplication.getApp().getResources().getDisplayMetrics());
        return (int) px;
    }

    /**
     * Convert Dp to Pixel 将dp转换为pixel
     */
    public static int pxTodp(float px) {
        final float scale = BaseApplication.getApp().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    /**
     * @param value
     * @return 将dip或者dp转为float
     */
    public static float dipOrDpToFloat(String value) {
        if (value.indexOf("dp") != -1) {
            value = value.replace("dp", "");
        } else {
            value = value.replace("dip", "");
        }
        return Float.parseFloat(value);
    }

    public static int to640(int size) {
        return toDip((int) (size * 1.0f / 640 * WIDTH));
    }

    public static int getDP(int dimensId) {
        return (int) BaseApplication.getApp().getResources().getDimension(dimensId);
    }

    /**
     * 等比缩放尺寸
     *
     * @param size
     * @return
     */
    public static int to640(float size) {
        return (int) (size * 1.0f / 640 * WIDTH);
    }

    /**
     * 等比缩放尺寸
     *
     * @param size
     * @return
     */
    public static int to320(int size) {
        return (int) (size * 1.0f / 320 * WIDTH);
    }

    /**
     * 等比缩放尺寸
     *
     * @param size
     * @return
     */
    public static int to320(float size) {
        return (int) (size * 1.0f / 320 * WIDTH);
    }

    /**
     * 颜色转换
     *
     * @param txt
     * @return
     */
    public static int parseColor(String txt) {
        if (TextUtils.isEmpty(txt)) {
            return Color.WHITE;
        }
        if (!txt.startsWith("#")) {
            txt = "#" + txt;
        }
        try {
            return Color.parseColor(txt);
        } catch (Exception e) {
            return Color.WHITE;
        }
    }

    /**
     * 颜色转换
     *
     * @param color 颜色值
     * @param alpha 透明度
     * @return
     */
    public static int parseColor(String color, float alpha) {
        String _alpha = Integer.toHexString((int) (255 * alpha));
        if (_alpha.length() == 1) {
            _alpha = "0" + _alpha;
        }
        if (TextUtils.isEmpty(color)) {
            color = "#FFFFFF";
        }
        if (!color.startsWith("#")) {
            color = "#" + color;
        }
        StringBuilder sb = new StringBuilder(color);
        sb.insert(1, _alpha);
        return parseColor(sb.toString());
    }

    /**
     * 颜色转换
     *
     * @param color 颜色值
     * @param alpha 透明度
     * @return
     */
    public static int parseColor(int color, float alpha) {
        if (color == 0)
            return 0;
        String _color = "#" + Integer.toHexString(color).substring(2);
        return parseColor(_color, alpha);
    }

    /**
     * 获取TextView的高度
     *
     * @param fontSize
     * @return
     */
    public static int getFontHeight(float fontSize, float lineWeigth, int lineNumber) {
        Paint paint = new Paint();
        paint.setTextSize(fontSize);
        FontMetrics fm = paint.getFontMetrics();
        return (int) ((Math.ceil(fm.bottom - fm.top) + 7) * lineWeigth) * lineNumber;
    }

    /**
     * 获取状态栏的高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }

    /**
     * 获取ActionBar的高度
     *
     * @param context
     * @return
     */
    public static int getActionBarHeight(Context context) {
        TypedValue tv = new TypedValue();
        int actionBarHeight = 0;
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
        }
        return actionBarHeight;
    }
}
