package com.example.viking.tsx6.Fragments;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by viking on 24/7/16.
 */
public class Config {
    public static final String DEVELOPER_KEY = "AIzaSyCqjCZP5ljNyRYyLXv-BfJrP6pkHb2YoAQ";
    public  static String SESS_ID=null;
    public static final String LOGIN_URL="http://192.168.0.220/tsx6backend/android_login";
    public static final String LOGOUT_URL="http://192.168.0.220/tsx6backend/android_logout";
    public static final String REGISTER_URL="http://192.168.0.220/tsx6backend/android_register";
    public static final String GET_LEVEL="http://192.168.0.220/tsx6backend/android_getlevel";
    public static final String CHECK="http://192.168.0.220/tsx6backend/android_chkans";
//    public static final String LOGIN_URL="http://cadnitd.co.in/android_login";
//    public static final String LOGOUT_URL="http://cadnitd.co.in/android_logout";
//    public static final String REGISTER_URL="http://cadnitd.co.in/android_register";
//    public static final String GET_LEVEL="http://cadnitd.co.in/android_getlevel";
//    public static final String CHECK="http://cadnitd.co.in/tsx6backend/android_chkans";
    public static final String SCHEDULE="http://2015.cadnitd.co.in/images/schedule.jpg";
    public static String USERNAME = null;
    public static String PASSWORD = null;
    public static Boolean LOGGED_IN = null;


    Config() {
    }
    public static void showToast(Context context,String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}
