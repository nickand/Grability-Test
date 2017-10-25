package test.android.testmerlin.interfaces;

import android.app.Activity;
import android.support.v4.app.Fragment;

public interface OnClickActivityListener {

    void setTitleToolbar(String title);
    void navigateTo(Fragment fragment);
    void navigateTo(Fragment fragment, boolean addToBackStack);
    void openWebView(Activity activity, String url);
}