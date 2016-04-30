package com.ronakmanglani.watchlist.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ronakmanglani.watchlist.R;
import com.ronakmanglani.watchlist.fragment.DetailFragment;

public class DetailActivity extends AppCompatActivity {

    // Key for intent extra
    public static final String MOVIE_ID = "movie_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (savedInstanceState == null) {
            DetailFragment fragment = new DetailFragment();

            Bundle args = new Bundle();
            args.putString(MOVIE_ID, getIntent().getStringExtra(MOVIE_ID));
            fragment.setArguments(args);

            getSupportFragmentManager().beginTransaction().replace(R.id.detail_container, fragment).commit();
        }
    }

}
