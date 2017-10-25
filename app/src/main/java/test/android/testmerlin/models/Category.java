package test.android.testmerlin.models;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import test.android.testmerlin.R;

public class Category {

    private static final String TAG = Category.class.getSimpleName();

    private String mTitle;
    private String mId;

    public Category(String name, String id) {
        mTitle = name;
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public static List<Category> getCategories(Context context) {
        List<Category> list = new ArrayList<>();

        String[] names = context.getResources().getStringArray(R.array.category_name);
        String[] ids = context.getResources().getStringArray(R.array.category_id);

        for (int i = 0; i < names.length; i++) {
            list.add(new Category(names[i], ids[i]));
            Log.d(TAG, "ID: " + list.get(i).getId() + " Categories: " + list.get(i).getTitle());
        }

        return list;
    }
}
