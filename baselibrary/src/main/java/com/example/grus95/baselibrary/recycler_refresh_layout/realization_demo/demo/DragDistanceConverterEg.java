package com.example.grus95.baselibrary.recycler_refresh_layout.realization_demo.demo;

import com.example.grus95.baselibrary.recycler_refresh_layout.IDragDistanceConverter;

/**
 * Created by grus95 on 16/8/31
 */
public class DragDistanceConverterEg implements IDragDistanceConverter {

    @Override
    public float convert(float scrollDistance, float refreshDistance) {
        return scrollDistance * 0.5f;
    }
}
