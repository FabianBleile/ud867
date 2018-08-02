package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.fabianbleile.javajokes.Joker;
import com.fabianbleile.jokeractivity.JokerActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    Joker mJoker = new Joker();
    private static ProgressBar mProgressBar;
    EndpointsAsyncTask.AsyncResponse asyncResponse = new EndpointsAsyncTask.AsyncResponse() {
        @Override
        public void onProcessFinish(String result) {
            Toast.makeText(getActivity(), "Here I am", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), JokerActivity.class);
            intent.putExtra(JokerActivity.EXT_JOKE, result);
            startActivity(intent);
            mProgressBar.setVisibility(View.GONE);
        }

        @Override
        public void onError(String errorString) {

        }
    };

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        mProgressBar = root.findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.GONE);

        Button bt_tellJoke = root.findViewById(R.id.bt_tell_joke);
        bt_tellJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(getActivity(), AdMobActivity.class));
                mProgressBar.setVisibility(View.VISIBLE);
                new EndpointsAsyncTask(asyncResponse).execute();
            }
        });

        return root;
    }
}
