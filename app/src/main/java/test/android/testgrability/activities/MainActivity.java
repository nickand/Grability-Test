package test.android.testgrability.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.android.testgrability.R;
import test.android.testgrability.fragments.AppsFragment;
import test.android.testgrability.fragments.AppsTabletFragment;
import test.android.testgrability.interfaces.OnClickActivityListener;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements OnClickActivityListener {

    private static final String CLASS_TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.toolbar_title)
    TextView titleToolbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private Fragment mFragment;

    // ¿Hay dos paneles?
    private boolean dosPaneles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setTitleToolbar("Test Grability");
        setSupportActionBar(toolbar);

        boolean tabletSize = getResources().getBoolean(R.bool.isTablet);
        if (tabletSize) {
            // do something
            Fragment fragment = null;
            fragment = AppsTabletFragment.newInstance();
            navigateTo(fragment);
        } else {
            // do something else
            Fragment fragment = null;
            fragment = AppsFragment.newInstance();
            navigateTo(fragment);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
