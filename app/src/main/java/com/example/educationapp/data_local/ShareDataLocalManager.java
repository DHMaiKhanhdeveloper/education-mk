package com.example.educationapp.data_local;

import android.content.Context;


import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ShareDataLocalManager {


    private static final String FIRST_STRING_HASH = "FIRST_STRING_HASH";
    private static final String STRING_JWT_TOKEN = "STRING_JWT_TOKEN";
    private static final String FIRST_BOOLEAN = "FIRST_BOOLEAN";
    private static final String FIRST_INT = "FIRST_INT";

    private  SharePreferences sharePreferences;
    private static ShareDataLocalManager shareDataLocalManager;

    public static  void init(Context mContext){
        shareDataLocalManager = new ShareDataLocalManager();
        shareDataLocalManager.sharePreferences = new SharePreferences(mContext);
    }
    public ShareDataLocalManager getShareDataLocalManager(){
        if(shareDataLocalManager ==null) {
            return shareDataLocalManager = new ShareDataLocalManager();
        }
      return shareDataLocalManager;
    }
    public static void  putboolean(boolean value){
        shareDataLocalManager.getShareDataLocalManager().sharePreferences.putBoolean(FIRST_BOOLEAN,value);
    }
    public static boolean getboolean(){
        return shareDataLocalManager.getShareDataLocalManager().sharePreferences.getBoolean(FIRST_BOOLEAN);
    }
    public static void  putInt(int value){
        shareDataLocalManager.getShareDataLocalManager().sharePreferences.putInt(FIRST_INT,value);
    }
    public static int getInt(){
        return shareDataLocalManager.getShareDataLocalManager().sharePreferences.getInt(FIRST_INT);
    }

    public static void setJwtToken(String token) {
        shareDataLocalManager.getShareDataLocalManager().sharePreferences.putString(STRING_JWT_TOKEN,token);
    }
    public  String getJwtToken(){
        return shareDataLocalManager.getShareDataLocalManager().sharePreferences.getString(STRING_JWT_TOKEN);
    }
    public static void  putSetHash(Set<String> value){
        shareDataLocalManager.getShareDataLocalManager().sharePreferences.putStringSet(FIRST_STRING_HASH,value);
    }
    public static Set<String> getSetHash(){
        return shareDataLocalManager.getShareDataLocalManager().sharePreferences.getStringSet(FIRST_STRING_HASH);
    }


}
