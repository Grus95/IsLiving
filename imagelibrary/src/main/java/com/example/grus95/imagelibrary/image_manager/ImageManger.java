package com.example.grus95.imagelibrary.image_manager;

import com.example.grus95.imagelibrary.image_realizer.ImageRealizer;

/**
 * Created by grus95 on 2016/9/1.
 */
public class ImageManger {

    private volatile static ImageManger imageManger;

    private ImageRealizer imageRealizer;

    private ImageManger() {

    }

    public static ImageManger getInstance() {
        if (null == imageManger){
            synchronized (ImageManger.class){
                if (null == imageManger){
                    imageManger = new ImageManger();
                }
            }
        }
        return imageManger;
    }
}
