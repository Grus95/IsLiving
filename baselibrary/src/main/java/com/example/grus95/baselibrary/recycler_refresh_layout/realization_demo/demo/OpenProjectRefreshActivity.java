package com.example.grus95.baselibrary.recycler_refresh_layout.realization_demo.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.grus95.baselibrary.R;

/**
 * Created by grus95 on 16/8/31
 */
public class OpenProjectRefreshActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.fragment_container, OpenProjectRecyclerFragment.newInstance())
//                .commit();
    }
}
