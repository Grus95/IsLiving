package com.example.grus95.baselibrary.recycler_refresh_layout;

public interface IDragDistanceConverter {
    /**
     *  Created by grus95 on 16/8/31
     * @param scrollDistance the distance between the ACTION_DOWN point and the ACTION_MOVE point
     * @param refreshDistance the distance between the refresh point and the initialize point
     * @return the real distance of the refresh view moved
     */
    float convert(float scrollDistance, float refreshDistance);
}
