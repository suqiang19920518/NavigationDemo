package com.example.navigation.activity;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
//import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.widget.Toast;

import com.example.navigation.R;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    private long exitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initNavController();

        //方法一：添加返回监听回调（此时不要复写 onBackPressed() 方法）
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (navController.getCurrentDestination() != null && navController.getCurrentDestination().getId() != R.id.mainFragment) {
                    //如果当前界面不是主页，那么直接调用返回即可
                    navController.navigateUp();
                } else {
                    //是主页
                    if (System.currentTimeMillis() - exitTime > 2000) {
                        Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_LONG).show();
                        exitTime = System.currentTimeMillis();
                    } else {
                        finish();
                    }
                }
            }
        });
    }

    private void initNavController() {
        /*
         * 使用 FragmentContainerView 创建 NavHostFragment，
         * 或通过 FragmentTransaction 手动将 NavHostFragment 添加到您的 Activity 时
         */
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.host_fragment);
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
            // navController = NavHostFragment.findNavController(navHostFragment);  //或者
        }

        /*
         * 使用 fragment 创建 NavHostFragment时
         */
        //  navController = Navigation.findNavController(this, R.id.host_fragment);
    }

    /*
    * 方法二：复写 onBackPressed() 方法
    @Override
    public void onBackPressed() {
        //  super.onBackPressed();  //禁止调用父类方法
        if (navController.getCurrentDestination() != null && navController.getCurrentDestination().getId() != R.id.mainFragment) {
            //如果当前界面不是主页，那么直接调用返回即可
            navController.navigateUp();
        } else {
            //是主页
            if (System.currentTimeMillis() - exitTime > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_LONG).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
        }
    }
     */

}