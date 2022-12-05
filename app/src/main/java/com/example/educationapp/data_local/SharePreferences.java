package com.example.educationapp.data_local;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

public class SharePreferences {

    private static final String SHARE_PREFERENCE = "SHARE_PREFERENCE";
    private Context mContext;
    private SharedPreferences.Editor prefsEditor;

    public SharePreferences(Context context) {
        this.mContext = context;
    }

    public void putInt(String key, int value) {
        SharedPreferences  sharedPreferences = mContext.getSharedPreferences(SHARE_PREFERENCE,Context.MODE_PRIVATE);


        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putInt(key,value);
        myEdit.apply();
    }

    public int getInt(String key) {
        SharedPreferences  sharedPreferences = mContext.getSharedPreferences(SHARE_PREFERENCE,Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key,0);
    }


    public void putBoolean(String key, boolean value){
        SharedPreferences  sharedPreferences = mContext.getSharedPreferences(SHARE_PREFERENCE,Context.MODE_PRIVATE);


        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putBoolean(key,value );
        myEdit.apply();
    }
    public  boolean getBoolean(String key){
        SharedPreferences  sharedPreferences = mContext.getSharedPreferences(SHARE_PREFERENCE,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key,false);
    }


    public void putString(String key, String value){
        SharedPreferences  sharedPreferences = mContext.getSharedPreferences(SHARE_PREFERENCE,Context.MODE_PRIVATE);


        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString(key,value );
        myEdit.apply();
    }

    public  String getString(String key){
        SharedPreferences  sharedPreferences = mContext.getSharedPreferences(SHARE_PREFERENCE,Context.MODE_PRIVATE);
//        String token =  sharedPreferences.getString(key," ");
//        if (token == null || token.isEmpty()) {
//            token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjIxNzc0NTI3OTksImlhdCI6MTUxNjAyMjk5OSwiaXNzIjoiQmFzb2JhYXMgTmVwYWwiLCJuYmYiOjE1MTYwMjI5OTksImp0aSI6Ikd1ZXN0VG9rZW4iLCJzdWIiOjB9.QikmNgBYmqch5HREGFEpUs4Xk3x-zFfDg5mhYJO7jM8";
//        }
//        return token;
        return sharedPreferences.getString(key," ");
    }

    public void putStringSet(String key, Set<String> values){
        SharedPreferences  sharedPreferences = mContext.getSharedPreferences(SHARE_PREFERENCE,Context.MODE_PRIVATE);


        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putStringSet(key,values );
        myEdit.apply();
    }

    public  Set<String> getStringSet(String key){
        SharedPreferences  sharedPreferences = mContext.getSharedPreferences(SHARE_PREFERENCE,Context.MODE_PRIVATE);
        Set<String> setHashString = new HashSet<String>();
        return sharedPreferences.getStringSet(key,setHashString );
    }
    public void clear() {
        prefsEditor.clear().commit();
    }



}
