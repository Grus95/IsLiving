package com.example.grus95.imagelibrary.image_interface;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by grus95 on 16/8/31
 */
public interface ImageEngine {

    //采用责任链模式

    void init();

    ImageEngine with(Context context);

    ImageEngine load(String path);

    ImageEngine into(ImageView target);


}
