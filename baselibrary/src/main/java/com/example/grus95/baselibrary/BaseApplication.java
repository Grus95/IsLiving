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
    private List<Activity> activitys;


    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        activitys = new LinkedList<>();
    }


    public static BaseApplication getApp() {
        return app;
    }


    public void addActivity(Activity activity) {
        if (null == activitys) activitys = new LinkedList<>();
        activitys.add(activity);
    }

    public void removeActivity(Activity activity) {
        if (null != activitys && activitys.contains(activity)) {
            activitys.remove(activity);
        }
    }

    public void exitActivitys() {
        if (null != activitys) {
            for (Activity activity : activitys) {
                activity.finish();
            }
            activitys.clear();
        }
    }
}
