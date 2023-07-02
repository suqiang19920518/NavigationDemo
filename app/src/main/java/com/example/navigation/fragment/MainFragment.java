package com.example.navigation.fragment;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.ActivityNavigator;
import androidx.navigation.NavController;
import androidx.navigation.NavDeepLinkBuilder;
import androidx.navigation.NavDeepLinkRequest;
import androidx.navigation.NavDirections;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.navigation.MainNavigationDirections;
import com.example.navigation.R;
import com.example.navigation.activity.SimpleActivity;

import java.util.Objects;

public class MainFragment extends Fragment implements View.OnClickListener {


    private NavController navController;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navController = NavHostFragment.findNavController(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        TextView mTvLogin = view.findViewById(R.id.tv_login);
        TextView mTvRegister = view.findViewById(R.id.tv_register);
        TextView mTvProfile = view.findViewById(R.id.tv_profile);
        TextView mTvStartSimpleActivity = view.findViewById(R.id.tv_start_simple_activity);
        TextView mTvStartIntentActivity = view.findViewById(R.id.tv_start_intent_activity);
        TextView mTvStartPersonalInfo = view.findViewById(R.id.tv_start_personal_info);
        TextView mTvStartWebActivity = view.findViewById(R.id.tv_start_web_activity);
        TextView mTvStartDeeplinkActivity = view.findViewById(R.id.tv_start_deep_link_activity);

        mTvLogin.setOnClickListener(this);
        mTvRegister.setOnClickListener(this);
        mTvProfile.setOnClickListener(this);
        mTvStartSimpleActivity.setOnClickListener(this);
        mTvStartIntentActivity.setOnClickListener(this);
        mTvStartPersonalInfo.setOnClickListener(this);
        mTvStartWebActivity.setOnClickListener(this);
        mTvStartDeeplinkActivity.setOnClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String type = MainFragmentArgs.fromBundle(getArguments()).getFromType();
        if (type == null) {
            type = "null";
        }
        Toast.makeText(getContext(), type, Toast.LENGTH_SHORT).show();
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_login) {
            //方法一
            //  NavDirections action = MainFragmentDirections.actionMainFragmentToLoginFragment();
            //  navController.navigate(action);
            //方法二
            navController.navigate(R.id.action_mainFragment_to_loginFragment);
        } else if (v.getId() == R.id.tv_register) {
            MainFragmentDirections.ActionMainFragmentToRegisterFragment action = MainFragmentDirections.actionMainFragmentToRegisterFragment();
            action.setUserId("123456");
            navController.navigate(action);
        } else if (v.getId() == R.id.tv_profile) {
            navController.navigate(R.id.action_mainFragment_to_profileFragment);
        } else if (v.getId() == R.id.tv_start_simple_activity) {
            NavOptions navOptions = new NavOptions.Builder()
                    .setEnterAnim(R.anim.slide_in_right)
                    .setExitAnim(R.anim.slide_out_left)
                    .setPopEnterAnim(R.anim.slide_in_left)
                    .setPopExitAnim(R.anim.slide_out_right)
                    .build();
            navController.navigate(R.id.sampleActivityDestination, null, navOptions);
            // startActivity(new Intent(getActivity(), SimpleActivity.class));
        } else if (v.getId() == R.id.tv_start_intent_activity) {
            //目的地应用未在其清单中使用匹配的 intent-filter 定义 Activity，系统就会抛出 ActivityNotFoundException。
            NavOptions navOptions = new NavOptions.Builder()
                    .setEnterAnim(R.anim.slide_in_right)
                    .setExitAnim(R.anim.slide_out_left)
                    .setPopEnterAnim(R.anim.slide_in_left)
                    .setPopExitAnim(R.anim.slide_out_right)
                    .build();
            navController.navigate(R.id.intentActivityDestination, null, navOptions);
            // startActivity(new Intent("android.intent.action.SETTING"));
        } else if (v.getId() == R.id.tv_start_personal_info) {
            navController.navigate(MainFragmentDirections.actionMainFragmentToPersonalInfo());
//            navController.navigate(R.id.action_mainFragment_to_personal_info);
        } else if (v.getId() == R.id.tv_start_web_activity) {
            navController.navigate(MainFragmentDirections.actionMainFragmentToWebGraph());
//            navController.navigate(R.id.action_mainFragment_to_webGraph);
        } else if (v.getId() == R.id.tv_start_deep_link_activity) {
            NavDeepLinkRequest request = NavDeepLinkRequest.Builder.fromAction("android.intent.action.SEARCH").build();
            NavOptions navOptions = new NavOptions.Builder()
                    .setEnterAnim(R.anim.slide_in_right)
                    .setExitAnim(R.anim.slide_out_left)
                    .setPopEnterAnim(R.anim.slide_in_left)
                    .setPopExitAnim(R.anim.slide_out_right)
                    .build();
            navController.navigate(request, navOptions);
        }
    }
}