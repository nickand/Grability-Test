package test.android.testgrability.interfaces;

import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;

/**
 * Created by Nicolas on 13/10/2016.
 */

public interface OnClickActivityListener {

    void navigateTo(Fragment fragment);
    void navigateTo(Fragment fragment, boolean addToBackStack);
}
