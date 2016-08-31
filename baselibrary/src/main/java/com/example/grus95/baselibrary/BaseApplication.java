package com.example.grus95.baselibrary;

import android.app.Activity;
import android.app.Application;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by grus95 on 16/8/31
 */
public class BaseApplication extends Application {

    private static BaseApplication app;
    private List<Activity> activities;


    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        activities = new LinkedList<>();
    }


    public static BaseApplication getApp() {
        return app;
    }


    public void addActivity(Activity activity) {
        if (null == activities) activities = new LinkedList<>();
        activities.add(activity);
    }

    public void removeActivity(Activity activity) {
        if (null != activities && activities.contains(activity)) {
            activities.remove(activity);
        }
    }

    public void exitActivitys() {
        if (null != activities) {
            for (Activity activity : activities) {
                activity.finish();
            }
            activities.clear();
        }
    }
}
