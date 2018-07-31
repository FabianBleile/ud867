package com.udacity.gradle.builditbigger;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class AdMobActivity extends Activity {

    private InterstitialAd mInterstitialAd;
    private ProgressBar progressBar;
    private TextView tv_adIsLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_mob);

        progressBar = findViewById(R.id.progressBar);
        tv_adIsLoading = findViewById(R.id.tv_ad_is_loading);
        progressBar.setVisibility(View.VISIBLE);
        tv_adIsLoading.setVisibility(View.VISIBLE);

        // Create the InterstitialAd and set the adUnitId (defined in values/strings.xml).
        mInterstitialAd = newInterstitialAd();
        loadInterstitial();
    }

    private InterstitialAd newInterstitialAd() {
        InterstitialAd interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getResources().getString(R.string.TEST_AD_UNIT_ID));
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                showInterstitial();
                progressBar.setVisibility(View.GONE);
                tv_adIsLoading.setVisibility(View.GONE);
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                finish();
            }
            @Override
            public void onAdClosed() {
                finish();
            }
        });
        return interstitialAd;
    }

    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and reload the ad.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
            showInterstitial();
        }
    }

    private void loadInterstitial() {
        // Request for Ads
        AdRequest adRequest = new AdRequest.Builder()
                // Add a test device to show Test Ads
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR) // comment out when publishing
                .addTestDevice(getResources().getString(R.string.addTestDevice))   // comment out when publishing
                .build();
        mInterstitialAd.loadAd(adRequest);
    }
}