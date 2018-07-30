package com.fabianbleile.jokeractivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

public class JokerActivity extends AppCompatActivity {

    public static String EXT_JOKE = "joke";

    private TextView tvJoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joker);

        tvJoke = findViewById(R.id.tv_joke);
        tvJoke.setText(getIntent().getStringExtra(EXT_JOKE));
    }
}
