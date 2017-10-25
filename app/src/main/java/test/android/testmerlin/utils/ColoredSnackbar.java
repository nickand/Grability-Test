package test.android.testmerlin.utils;

import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.androidadvance.topsnackbar.TSnackbar;

import test.android.testmerlin.R;

public class ColoredSnackbar {

    public static final int FIVE_SECONDS = 5000;
    public static final int SNACKBAR_SUCCESS = 1;
    public static final int SNACKBAR_ERROR = -1;
    public static final int SNACKBAR_WARNING = 3;

    private static View getSnackBarLayout(TSnackbar snackbar) {
        if (snackbar != null) {
            return snackbar.getView();
        }
        return null;
    }

    private static TSnackbar colorSnackBar(TSnackbar snackbar, int colorId) {

        View snackBarView = getSnackBarLayout(snackbar);

        if (snackBarView != null) {
            snackBarView.setBackgroundColor(colorId);
            TextView tv = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
            tv.setGravity(Gravity.CENTER);
            tv.setMaxLines(5);
            tv.setTextColor(ContextCompat.getColor(tv.getContext(), R.color.white));
        }

        return snackbar;
    }

    public static TSnackbar warning(TSnackbar snackbar) {
        return colorSnackBar(snackbar, ContextCompat.getColor(App.getContext(), R.color.yellow));
    }

    public static TSnackbar error(TSnackbar snackbar) {
        validateEmptyMessage(snackbar);
        return colorSnackBar(snackbar, ContextCompat.getColor(App.getContext(), R.color.red));
    }

    private static void validateEmptyMessage(TSnackbar snackbar) {
        View snackBarView = getSnackBarLayout(snackbar);

        if (snackBarView != null) {
            TextView tv = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
            if (TextUtils.isEmpty(tv.getText())) {
                tv.setText(App.getContext().getString(R.string.error_network));
            }
        }
    }

    public static TSnackbar success(TSnackbar snackbar) {
        return colorSnackBar(snackbar, ContextCompat.getColor(App.getContext(), R.color.bg_green));
    }
}