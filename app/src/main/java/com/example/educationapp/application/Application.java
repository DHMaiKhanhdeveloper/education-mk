package com.example.educationapp.application;


import com.example.educationapp.data_local.ShareDataLocalManager;

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ShareDataLocalManager.init(getApplicationContext());
    }
}
