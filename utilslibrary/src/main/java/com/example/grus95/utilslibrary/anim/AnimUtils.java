package com.example.grus95.utilslibrary.anim;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

/**
 * Created by grus95 on 16/8/31
 */
public class AnimUtils {
	public static Animation getShowAlphaAnimation(long duration) {
		Animation mAnimation = new AlphaAnimation(0.0f, 1.0f);
		mAnimation.setDuration(duration);
		return mAnimation;
	}
}
