package test.android.testgrability.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import test.android.testgrability.R;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_TIMER = 3000;
    private TextView mImageViewFilling;
    private Handler mHandler;
    private boolean isDestroyed = false;
    private static final String CLASS_TAG = SplashActivity.class.getName();
    private AlertDialog mAlert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        initViews();


    }

    private void initViews() {
        mImageViewFilling = (TextView) findViewById(R.id.imageview_animation_list_filling);

        Animation logoScaleAnimation = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.scale_in);
        mImageViewFilling.startAnimation(logoScaleAnimation);

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

                        Intent openSplashActivity = new Intent(SplashActivity.this, MainActivity.class);
                        openSplashActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(openSplashActivity);
                        overridePendingTransition(0, R.anim.screen_splash_fade_out);
                        finish();

                    }
                };
                mHandler.postDelayed(r, SPLASH_TIMER);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
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
