package test.android.testgrability.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.android.testgrability.R;
import test.android.testgrability.adapters.CategoriesListRecyclerViewAdapter;
import test.android.testgrability.fragments.AppsFragment;
import test.android.testgrability.fragments.AppsTabletFragment;
import test.android.testgrability.fragments.CategoriesFragment;
import test.android.testgrability.fragments.CategoriesTabletFragment;
import test.android.testgrability.interfaces.OnClickActivityListener;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class CategoriesActivity extends AppCompatActivity implements OnClickActivityListener {

    private static final String CLASS_TAG = CategoriesActivity.class.getSimpleName();
    @BindView(R.id.toolbar_title)
    TextView titleToolbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private RecyclerView mReciclerView;
    private CategoriesListRecyclerViewAdapter mAdapter;
    private Fragment mFragment;
    private OnClickActivityListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        ButterKnife.bind(this);

        setTitleToolbar("Categories");
        setSupportActionBar(toolbar);

        boolean tabletSize = getResources().getBoolean(R.bool.isTablet);
        if (tabletSize) {
            // do something
            Fragment fragment = null;
            fragment = CategoriesTabletFragment.newInstance();
            navigateTo(fragment);
        } else {
            // do something else
            Fragment fragment = null;
            fragment = CategoriesFragment.newInstance();
            navigateTo(fragment);
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void setTitleToolbar(String title) {
        titleToolbar.setText(title);
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

        if (mFragment == null) {
            fragmentTransaction.add(R.id.fragment_container, fragment).commit();

        } else {

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
