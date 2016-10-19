package test.android.testgrability.utils;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by Nicolas on 19/10/2016.
 */

public class Utils {

    public static boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) App.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
}
