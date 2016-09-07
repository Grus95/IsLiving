package com.example.grus95.imagelibrary.image_realizer;

import android.content.Context;
import android.widget.ImageView;

import com.example.grus95.imagelibrary.image_interface.ImageEngine;

/**
 * Created by grus95 on 2016/9/1.
 */
public class ImageRealizer implements ImageEngine {
    @Override
    public void init() {

    }

    @Override
    public ImageEngine with(Context context) {
        return null;
    }

    @Override
    public ImageEngine load(String path) {
        return null;
    }

    @Override
    public ImageEngine into(ImageView target) {
        return null;
    }
}
