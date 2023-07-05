package com.example.navigation.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ActivityNavigator;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.view.View;

import com.example.navigation.R;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.host_fragment);

        NavController navController = navHostFragment.getNavController();
        // 第一步：inflate导航图
        NavGraph navGraph = navController.getNavInflater().inflate(R.navigation.setting_navigation);
        // 第二步：设置startDestination	会覆盖在XML中指定的startDestination
        navGraph.setStartDestination(R.id.settingFragment);
        // 第三步：通过NavController设置导航图       必须最后再调用本方法
        navController.setGraph(navGraph);
    }

    @Override
    public void finish() {
        super.finish();
        ActivityNavigator.applyPopAnimationsToPendingTransition(this);
    }
}