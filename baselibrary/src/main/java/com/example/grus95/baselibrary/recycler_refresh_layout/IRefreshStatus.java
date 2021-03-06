package com.example.grus95.baselibrary.recycler_refresh_layout;

/**
 *  Created by grus95 on 16/8/31
 * {@link RecyclerRefreshLayout#mRefreshView} all the custom drop-down refresh view need to
 * implements the interface
 */
public interface IRefreshStatus {
  /**
   * When the content view has reached top and refresh has been completed, view will be reset.
   */
  void reset();

  /**
   * Refresh View is refreshing
   */
  void refreshing();

  /**
   * Refresh View is dropped down to the refresh point
   */
  void pullToRefresh();

  /**
   * Refresh View is released into the refresh point
   */
  void releaseToRefresh();

  /**
   * @param pullDistance The drop-down distance of the refresh View
   * @param pullProgress The drop-down progress of the refresh View and the pullProgress may be more than 1.0f
   */
  void pullProgress(float pullDistance, float pullProgress);
}
