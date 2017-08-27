package com.ankit.glideamazonimageload;

import android.app.Application;

/**
 * Created by ankit on 27-08-2017.
 */

public class TheApplication extends Application{

    public static TheApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
}
