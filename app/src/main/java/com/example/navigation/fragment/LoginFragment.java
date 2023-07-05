package com.example.navigation.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.navigation.R;
import com.example.navigation.bean.LoginResult;
import com.example.navigation.bean.UserInfo;
import com.example.navigation.viewmodel.UserViewModel;

public class LoginFragment extends Fragment {
    public static String LOGIN_SUCCESSFUL = "LOGIN_SUCCESSFUL";
    private SavedStateHandle savedStateHandle;
    private UserViewModel mUserViewModel;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUserViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);

        //设置一个初始值，指示用户是否已成功登录。这是我们希望在用户直接按系统返回按钮后返回的状态。
        savedStateHandle = Navigation.findNavController(view)
                .getPreviousBackStackEntry()
                .getSavedStateHandle();
        savedStateHandle.set(LOGIN_SUCCESSFUL, false);

        EditText etUserName = view.findViewById(R.id.et_username);
        EditText etPassword = view.findViewById(R.id.et_password);
        TextView tvLogin = view.findViewById(R.id.tv_login);

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = etUserName.getText().toString();
                String password = etPassword.getText().toString();
                login(userName, password);
            }
        });

    }

    private void login(String username, String password) {
        mUserViewModel.login(username, password).observe(getViewLifecycleOwner(), new Observer<LoginResult>() {
            @Override
            public void onChanged(LoginResult loginResult) {
                if (loginResult.isSuccess()) {
                    savedStateHandle.set(LOGIN_SUCCESSFUL, true);
                    mUserViewModel.getUserLiveData().setValue(new UserInfo(username, password, true));
                    NavHostFragment.findNavController(LoginFragment.this).popBackStack();
                } else {
                    Toast.makeText(getContext(), "登录失败", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}