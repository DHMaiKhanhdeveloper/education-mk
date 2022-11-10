package com.example.sharepreferences.data_local;

import android.content.Context;

import com.example.sharepreferences.model.User;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ShareDataLocalManager {

    private static final String FIRST_INSTALL = " FIRST_INSTALL";
    private static final String FIRST_STRING_HASH = "FIRST_STRING_HASH";
    private static final String FIRST_OBJECT_USER = "FIRST_OBJECT_USER";
    private static final String FIRST_OBJECT_LIST_USER = "FIRST_OBJECT_LIST_USER";
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
    public static void  putFirstInstall(boolean value){
        shareDataLocalManager.getShareDataLocalManager().sharePreferences.putBoolean(FIRST_INSTALL,value);
    }
    public static boolean getFirstInstall(){
        return shareDataLocalManager.getShareDataLocalManager().sharePreferences.getBoolean(FIRST_INSTALL);
    }

    public static void  putSetHash(Set<String> value){
        shareDataLocalManager.getShareDataLocalManager().sharePreferences.putStringSet(FIRST_STRING_HASH,value);
    }
    public static Set<String> getSetHash(){
        return shareDataLocalManager.getShareDataLocalManager().sharePreferences.getStringSet(FIRST_STRING_HASH);
    }

    public static void  putUser(User user){
        Gson gson = new Gson(); // convert object to stringjson
        String strUser = gson.toJson(user);
        shareDataLocalManager.getShareDataLocalManager().sharePreferences.putString(FIRST_OBJECT_USER,strUser);
    }

    public static User getUser(){
        String strObjectUser = shareDataLocalManager.getShareDataLocalManager().sharePreferences.getString(FIRST_OBJECT_USER);
        Gson gson = new Gson();
        User user = gson.fromJson(strObjectUser,User.class);
        return user;
    }

    public static void  putListUser(List<User> listUser){
        Gson gson = new Gson(); // convert object to stringjson
        JsonArray jsonArray = gson.toJsonTree(listUser).getAsJsonArray(); // convert list object to json array
        String strUser = jsonArray.toString();
        shareDataLocalManager.getShareDataLocalManager().sharePreferences.putString(FIRST_OBJECT_LIST_USER,strUser);
    }

    public static List<User> getListUser(){
        String strObjectUser = shareDataLocalManager.getShareDataLocalManager().sharePreferences.getString(FIRST_OBJECT_LIST_USER);
        List<User> listUser = new ArrayList<>();

        JSONObject jsonObject;
        User user;

            try {
                JSONArray jsonArray = new JSONArray(strObjectUser);
                for (int i=0;i<jsonArray.length();i++) {
                    jsonObject = jsonArray.getJSONObject(i);
                    Gson gson = new Gson();
                    user = gson.fromJson(jsonObject.toString(), User.class);
                    listUser.add(user);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        return listUser;
    }


}
