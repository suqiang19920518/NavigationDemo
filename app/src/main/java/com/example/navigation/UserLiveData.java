package com.example.navigation;

import androidx.lifecycle.LiveData;

import com.example.navigation.bean.UserInfo;

public class UserLiveData extends LiveData<UserInfo> {
    private static UserLiveData mUserLiveData;

    public UserLiveData() {

    }

    public static UserLiveData getInstance() {
        if (mUserLiveData == null) {
            mUserLiveData = new UserLiveData();
        }
        return mUserLiveData;
    }

    @Override
    public void postValue(UserInfo value) {
        super.postValue(value);
    }

    @Override
    public void setValue(UserInfo value) {
        super.setValue(value);
    }
}
