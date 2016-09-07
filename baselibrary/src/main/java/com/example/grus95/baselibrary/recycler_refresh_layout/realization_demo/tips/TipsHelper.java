package com.example.grus95.baselibrary.recycler_refresh_layout.realization_demo.tips;

/**
 * Created by grus95 on 16/8/31
 */
public interface TipsHelper {

    void showEmpty();

    void hideEmpty();

    void showLoading(boolean firstPage);

    void hideLoading();

    void showError(boolean firstPage, Throwable error);

    void hideError();

    void showHasMore();

    void hideHasMore();
}