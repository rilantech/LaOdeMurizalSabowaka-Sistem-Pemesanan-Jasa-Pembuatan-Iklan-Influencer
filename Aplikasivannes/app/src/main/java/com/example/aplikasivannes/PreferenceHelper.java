package com.example.aplikasivannes;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {

    private final String INTRO = "";
    private final String NAMA = "";
    private final String NIP = "";
    private final String USERNAME = "";
    private final String PASSWORD = "";
    public static SharedPreferences.Editor editor;
    public static SharedPreferences app_prefs;
    public static Context context;

    public PreferenceHelper(Context context){
        this.context = context;
        app_prefs = context.getSharedPreferences("shared", Context.MODE_PRIVATE);

    }

    public void putIsLogin(boolean loginorout){
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putBoolean(INTRO, loginorout);
        edit.commit();
    }

    public boolean getIsLogin(){
        return app_prefs.getBoolean(INTRO, false);
    }

    public void putNama(String loginorout){
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(NAMA, loginorout);
        edit.commit();
    }

    public String getNip(){
        return app_prefs.getString(NIP, "");
    }

    public void putNip(String loginorout){
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(NIP, loginorout);
        edit.commit();
    }


    public String getNama(){
        return app_prefs.getString(NAMA, "");
    }


    public void putUsername(String loginorout){
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(USERNAME, loginorout);
        edit.commit();
    }

    public String getUsername(){
        return app_prefs.getString(USERNAME, "");
    }

    public void putPassword(String loginorout){
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(PASSWORD, loginorout);
        edit.commit();
    }

    public String getPassword(){
        return app_prefs.getString(PASSWORD, "");
    }
}
