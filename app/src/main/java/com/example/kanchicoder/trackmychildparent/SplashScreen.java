package com.example.kanchicoder.trackmychildparent;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.*;
import android.widget.Toast;


/**
 * Created by kanchicoder on 11/6/2016.
 */

public class SplashScreen extends AppCompatActivity {
    private ImageView logo;
    private TextView appname;
    private static int TIME_OUT = 800;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash);
        //setting the animation in splash
        logo = (ImageView) findViewById(R.id.splash_logo_imageview);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
        logo.startAnimation(animation);
        appname = (TextView) findViewById(R.id.splash_app_name_textView);
        Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        appname.startAnimation(animation2);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(checkNetworkState(getApplicationContext())) {
                            startActivity(new Intent(getApplicationContext(), WelcomeActivity.class));
                            finish();
                        }
                        else {
                            Snackbar mySnackbar  = Snackbar.make(findViewById(R.id.splashCoordinatorLayout),
                                    "No Internet Connection", Snackbar.LENGTH_INDEFINITE).setAction("Close", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    finish();
                                    System.exit(0);
                                }
                            });
                            mySnackbar.show();
                        }
                    }
                }, TIME_OUT);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    public boolean checkNetworkState(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }
}
