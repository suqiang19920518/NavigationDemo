package com.example.navigation.fragment;

import static com.example.navigation.fragment.LoginFragment.LOGIN_SUCCESSFUL;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.navigation.R;
import com.example.navigation.bean.UserInfo;
import com.example.navigation.viewmodel.UserViewModel;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    private boolean isLogin;
    private NavController navController;
    private UserViewModel mUserViewModel;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navController = NavHostFragment.findNavController(this);

        NavBackStackEntry currentBackStackEntry = navController.getCurrentBackStackEntry();
        if (currentBackStackEntry != null) {
            SavedStateHandle savedStateHandle = currentBackStackEntry.getSavedStateHandle();
            MutableLiveData<Boolean> liveData = savedStateHandle.getLiveData(LOGIN_SUCCESSFUL);
            liveData.observe(currentBackStackEntry, new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean result) {
                    // 处理监听到的数据
                    isLogin = result;
                    if (!isLogin) { //用户不登录,返回回到首页
                        int startDestination = navController.getGraph().getStartDestinationId();
                        NavOptions navOptions = new NavOptions.Builder()
                                .setPopUpTo(startDestination, true)
                                .build();
                        navController.navigate(startDestination, null, navOptions);
                    }
                    // 清除LiveData
                    currentBackStackEntry.getSavedStateHandle().remove(LOGIN_SUCCESSFUL);
                }
            });
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUserViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        mUserViewModel.getUserLiveData().observe(getViewLifecycleOwner(), new Observer<UserInfo>() {
            @Override
            public void onChanged(UserInfo userInfo) {
                boolean isLogin = userInfo.isLogin();
                if (!isLogin) { //进入该界面，系统会先判断是否已登录。如果未登录，则跳转至登录界面
                    NavOptions navOptions = new NavOptions.Builder()
                            .setEnterAnim(R.anim.slide_in_right)
                            .setExitAnim(R.anim.slide_out_left)
                            .build();
                    navController.navigate(R.id.loginFragment, null, navOptions);
                } else {
                    Toast.makeText(getContext(), "登录成功", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}