package com.mradking.mradkingshop.kirana_store.custmer.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.mradking.mradkingshop.R;

public class Learn_more_home_act extends AppCompatActivity {

    private Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learn_more_home_screen);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Learn More");
        setSupportActionBar(toolbar);


    }
}
