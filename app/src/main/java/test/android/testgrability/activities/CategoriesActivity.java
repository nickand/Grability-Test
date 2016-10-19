package test.android.testgrability.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.List;

import test.android.testgrability.R;
import test.android.testgrability.adapters.AppsListRecyclerViewAdapter;
import test.android.testgrability.adapters.CategoriesListRecyclerViewAdapter;
import test.android.testgrability.fragments.AppsFragment;
import test.android.testgrability.fragments.CategoriesAppFragment;
import test.android.testgrability.fragments.CategoriesFragment;
import test.android.testgrability.interfaces.OnClickActivityListener;
import test.android.testgrability.models.Genre;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class CategoriesActivity extends AppCompatActivity implements OnClickActivityListener{

    private static final String CLASS_TAG = CategoriesActivity.class.getSimpleName();
    private RecyclerView mReciclerView;
    private CategoriesListRecyclerViewAdapter mAdapter;
    private Fragment mFragment;
    private OnClickActivityListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        Fragment fragment =null;
        fragment = CategoriesFragment.newInstance();

        navigateTo(fragment);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void navigateTo(Fragment fragment) {
        navigateTo(fragment, true);
    }

    @Override
    public void navigateTo(Fragment fragment, boolean addToBackStack) {
        final FragmentManager manager = getSupportFragmentManager();

        // Removes current fragment from back stack,
        // if user presses back later he skips this fragment.
        // Avoid adding this fragment to back stack, causes fragments overlapping.
        if (!addToBackStack) {
            manager.popBackStackImmediate();
        }

        FragmentTransaction fragmentTransaction = manager.beginTransaction();

        if (mFragment == null){
            fragmentTransaction.replace(R.id.fragment_container, fragment).commit();

        }else{

            fragmentTransaction.setCustomAnimations(
                    R.anim.enter_from_right, R.anim.exit_to_left,
                    R.anim.enter_from_left, R.anim.exit_to_right);
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();


        }

        mFragment = fragment;
    }
}
