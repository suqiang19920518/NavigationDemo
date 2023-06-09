package com.example.navigation.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ActivityNavigator;

import android.os.Bundle;

import com.example.navigation.R;

public class SimpleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
    }

    @Override
    public void finish() {
        super.finish();
        ActivityNavigator.applyPopAnimationsToPendingTransition(this);
    }
}