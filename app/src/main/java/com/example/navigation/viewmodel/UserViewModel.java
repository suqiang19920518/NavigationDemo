package com.example.navigation.viewmodel;

import android.text.TextUtils;

import androidx.lifecycle.ViewModel;

import com.example.navigation.LoginResultLiveData;
import com.example.navigation.UserLiveData;
import com.example.navigation.bean.LoginResult;
import com.example.navigation.bean.UserInfo;

public class UserViewModel extends ViewModel {
    private UserLiveData userLiveData;
    private LoginResultLiveData loginResultLiveData;

    public UserLiveData getUserLiveData() {
        if (userLiveData == null) {
            userLiveData = new UserLiveData();
            userLiveData.setValue(new UserInfo("", "", false));
        }
        return userLiveData;
    }

    public LoginResultLiveData getLoginResultLiveData() {
        if (loginResultLiveData == null) {
            loginResultLiveData = new LoginResultLiveData();
            loginResultLiveData.setValue(new LoginResult(false));
        }
        return loginResultLiveData;
    }

    public LoginResultLiveData login(String username, String password) {
        LoginResultLiveData loginResultLiveData = getLoginResultLiveData();
        //模拟用户登录
        if (!(TextUtils.isEmpty(username) || TextUtils.isEmpty(password))) {
            LoginResult loginResult = loginResultLiveData.getValue();
            loginResult.setSuccess(true);
            loginResultLiveData.setValue(loginResult);
        }
        return loginResultLiveData;
    }
}
