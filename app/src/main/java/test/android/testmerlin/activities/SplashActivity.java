package test.android.testmerlin.activities;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import test.android.testmerlin.R;
import test.android.testmerlin.databinding.ActivitySplashBinding;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_TIMER = 3000;
    private Handler mHandler;
    private boolean isDestroyed = false;
    private static final String CLASS_TAG = SplashActivity.class.getName();

    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViews();

        setListeners();
    }

    private void initViews() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
    }

    private void setListeners() {
        Animation logoScaleAnimation = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.scale_in);
        binding.textSplash.startAnimation(logoScaleAnimation);

        logoScaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animation.cancel();
                mHandler = new Handler();
                final Runnable r = new Runnable() {
                    @Override
                    public void run() {

                        if (isDestroyed)
                            return;

                        openMainActivity();

                    }
                };
                mHandler.postDelayed(r, SPLASH_TIMER);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void openMainActivity() {
        Intent openSplashActivity = new Intent(SplashActivity.this, MainActivity.class);
        openSplashActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(openSplashActivity);
        overridePendingTransition(0, R.anim.screen_splash_fade_out);
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        isDestroyed = true;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
