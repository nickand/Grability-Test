package test.android.testmerlin.activities;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.androidadvance.topsnackbar.TSnackbar;
import com.github.pwittchen.reactivenetwork.library.rx2.Connectivity;
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;
import com.thefinestartist.finestwebview.FinestWebView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import test.android.testmerlin.R;
import test.android.testmerlin.databinding.ActivityCategoriesBinding;
import test.android.testmerlin.fragments.CategoriesFragment;
import test.android.testmerlin.interfaces.OnClickActivityListener;
import test.android.testmerlin.utils.ColoredSnackbar;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class CategoriesActivity extends AppCompatActivity implements OnClickActivityListener {

    private static final String CLASS_TAG = CategoriesActivity.class.getSimpleName();

    private Fragment mFragment;

    private ActivityCategoriesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViews();

        navigateToCategoriesFragment();

        checkNetworkConnection();
    }

    private void initViews() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_categories);

        setTitleToolbar(getString(R.string.category));
        setSupportActionBar(binding.containerToolbar);
    }

    private void navigateToCategoriesFragment() {
        Fragment fragment = null;
        fragment = CategoriesFragment.newInstance();
        navigateTo(fragment);
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

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
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
}
