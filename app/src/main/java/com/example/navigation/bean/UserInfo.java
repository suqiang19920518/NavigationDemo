package com.example.navigation.bean;

public class UserInfo {
    private String mName;
    private String mPassword;
    private boolean mIsLogin;

    public UserInfo(String name, String password, boolean isLogin) {
        this.mName = name;
        this.mPassword = password;
        this.mIsLogin = isLogin;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        this.mPassword = password;
    }

    public boolean isLogin() {
        return mIsLogin;
    }

    public void setLogin(boolean isLogin) {
        mIsLogin = isLogin;
    }
}
