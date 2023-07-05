package com.example.navigation.bean;

public class LoginResult {

    private boolean mIsSuccess;

    public LoginResult(boolean isSuccess) {
        this.mIsSuccess = isSuccess;
    }

    public boolean isSuccess() {
        return mIsSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        mIsSuccess = isSuccess;
    }
}
