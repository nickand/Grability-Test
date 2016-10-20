package test.android.testgrability.utils;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

import test.android.testgrability.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Nicolas on 19/08/2016.
 */
public class App extends Application {

    public static Context mContext;

    public static Context getContext() {
        return App.mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/CircularStd-Bold.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        mContext = getApplicationContext();
    }
}
