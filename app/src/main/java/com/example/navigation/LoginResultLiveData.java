package com.example.navigation;

import androidx.lifecycle.LiveData;

import com.example.navigation.bean.LoginResult;
import com.example.navigation.bean.UserInfo;

public class LoginResultLiveData extends LiveData<LoginResult> {
    private static LoginResultLiveData mLoginResultLiveData;

    public LoginResultLiveData() {

    }

    public static LoginResultLiveData getInstance() {
        if (mLoginResultLiveData == null) {
            mLoginResultLiveData = new LoginResultLiveData();
        }
        return mLoginResultLiveData;
    }

    @Override
    public void postValue(LoginResult value) {
        super.postValue(value);
    }

    @Override
    public void setValue(LoginResult value) {
        super.setValue(value);
    }
}
