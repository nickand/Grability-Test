package test.android.testmerlin.activities;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.androidadvance.topsnackbar.TSnackbar;
import com.github.pwittchen.reactivenetwork.library.rx2.Connectivity;
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;
import com.thefinestartist.finestwebview.FinestWebView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import test.android.testmerlin.R;
import test.android.testmerlin.databinding.ActivityMainBinding;
import test.android.testmerlin.fragments.AboutMeFragment;
import test.android.testmerlin.fragments.AppsFragment;
import test.android.testmerlin.interfaces.OnClickActivityListener;
import test.android.testmerlin.utils.ColoredSnackbar;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements OnClickActivityListener {

    private static final String CLASS_TAG = MainActivity.class.getSimpleName();

    private Fragment mFragment;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViews();

        navigateToAppsFragment();

        checkNetworkConnection();
    }

    private void checkNetworkConnection() {
        final ViewGroup viewGroup = (ViewGroup) getWindow().getDecorView().getRootView();

        ReactiveNetwork.observeNetworkConnectivity(this)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Connectivity>() {
                    @Override
                    public void accept(final Connectivity connectivity) {
                        if (!connectivity.isAvailable()) {
                            TSnackbar snackbar = TSnackbar.make(viewGroup, getString(R.string.error_network), ColoredSnackbar.FIVE_SECONDS);
                            ColoredSnackbar.error(snackbar).show();
                        }
                    }
                });
    }

    private void navigateToAppsFragment() {
        Fragment fragment = null;
        fragment = AppsFragment.newInstance();
        navigateTo(fragment);
    }

    private View initViews() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setTitleToolbar(getString(R.string.app_name));
        setSupportActionBar(binding.containerToolbar);

        return binding.getRoot();
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
            binding.containerToolbar.setVisibility(View.GONE);
            navigateTo(new AboutMeFragment());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitleToolbar(String title) {
        binding.toolbarTitle.setText(title);
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
    public void openWebView(Activity activity, String url) {
        new FinestWebView.Builder(activity).show(url);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        binding.containerToolbar.setVisibility(View.VISIBLE);
    }
}
