package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.fabianbleile.javajokes.Joker;
import com.fabianbleile.jokeractivity.JokerActivity;
import com.udacity.gradle.builditbigger.free.AdMobActivity;


public class MainActivity extends AppCompatActivity{

    Joker mJoker = new Joker();
    private ProgressBar mProgressBar;
    EndpointsAsyncTask.AsyncResponse asyncResponse = new EndpointsAsyncTask.AsyncResponse() {
        @Override
        public void onProcessFinish(String result) {
            Toast.makeText(getApplicationContext(), "Here I am", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), JokerActivity.class);
            intent.putExtra(JokerActivity.EXT_JOKE, result);
            startActivity(intent);
            mProgressBar.setVisibility(View.GONE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.GONE);
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

    public void tellJoke(View view) {
        mProgressBar.setVisibility(View.VISIBLE);
        startActivity(new Intent(this, AdMobActivity.class));
        Toast.makeText(this, "" + mJoker.getJoke(), Toast.LENGTH_SHORT).show();
        new EndpointsAsyncTask(this,asyncResponse).execute(new Pair<Context, String>(this, "Fabi"));
    }
}
