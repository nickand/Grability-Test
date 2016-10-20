package test.android.testgrability.models;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import test.android.testgrability.R;

/**
 * Created by Nicolas on 16/10/2016.
 */

public class Genre {

    private static final String TAG = Genre.class.getSimpleName();

    private static Context mContext;
    private String mTitle;
    private int mId;

    public Genre(Context context, String name, int id) {
        mContext = context;
        mTitle = name;
        mId = id;
    }

    public Genre(String name, int id) {
        mTitle = name;
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public static List<Genre> getCategories(Context context) {
        List<Genre> list = new ArrayList<>();

        String[] names = context.getResources().getStringArray(R.array.category_name);
        int[] ids = context.getResources().getIntArray(R.array.category_id);

        for (int i = 0; i < names.length; i++) {
            list.add(new Genre(names[i], ids[i]));
            Log.d(TAG, "ID: " + list.get(i).getId() + " Categories: " + list.get(i).getTitle());
        }

        return list;
    }
}
