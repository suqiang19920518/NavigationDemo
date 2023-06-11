package com.example.navigation.fragment;

import static com.example.navigation.fragment.LoginFragment.LOGIN_SUCCESSFUL;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.SavedStateHandle;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.navigation.R;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    private boolean isLogin;
    private NavController navController;

    public ProfileFragment() {
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
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        NavBackStackEntry currentBackStackEntry = navController.getCurrentBackStackEntry();
        if (currentBackStackEntry != null) {
            SavedStateHandle savedStateHandle = currentBackStackEntry.getSavedStateHandle();
            savedStateHandle.getLiveData(LOGIN_SUCCESSFUL).observe(currentBackStackEntry, (Observer<Object>) success -> {
                if (!(Boolean) success) { //用户不登录
                    int startDestination = navController.getGraph().getStartDestinationId();
                    NavOptions navOptions = new NavOptions.Builder()
                            .setPopUpTo(startDestination, true)
                            .build();
                    navController.navigate(startDestination, null, navOptions);
                }
            });
            Boolean isLogin = savedStateHandle.get(LOGIN_SUCCESSFUL);
            if (isLogin == null || !isLogin) { //未登录
                NavOptions navOptions = new NavOptions.Builder()
                        .setEnterAnim(R.anim.slide_in_right)
                        .setExitAnim(R.anim.slide_out_left)
                        .build();
                navController.navigate(R.id.loginFragment, null, navOptions);
            }
        }
    }
}