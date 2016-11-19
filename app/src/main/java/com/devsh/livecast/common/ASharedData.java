package com.devsh.livecast.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class ASharedData {

    protected static String getSharedPreferenceString(Context context, String key) {
        return getSharedPreferenceString(context, key, null);
    }

    private static String getSharedPreferenceString(Context context, String key, String defaultReturnValue) {
        try{
            SharedPreferences mSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
            return mSharedPrefs.getString(key, defaultReturnValue);
        }catch(NullPointerException e){
            e.printStackTrace();
            return defaultReturnValue;
        }
    }

    protected static void putSharedPreferenceString(Context context, String key, String value){
        putSharedPreference(context, key, value);
    }

    private static Boolean getSharedPreferenceBoolean(Context context, String key) {
        return getSharedPreferenceBoolean(context, key, false);
    }

    private static Boolean getSharedPreferenceBoolean(Context context, String key, Boolean defaultReturnValue) {
        try{
            SharedPreferences mSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
            return mSharedPrefs.getBoolean(key, defaultReturnValue);
        }catch(NullPointerException e){
            e.printStackTrace();
            return defaultReturnValue;
        }
    }

    public static void putSharedPreferenceBoolean(Context context, String key, Boolean value){
        putSharedPreference(context, key, value);
    }

    private static void putSharedPreference(Context context, String key, Object value) {
        if (context == null) {
            return;
        }
        SharedPreferences mSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = mSharedPrefs.edit();

        if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else {
            throw new IllegalArgumentException("Not supported data type");
        }
        editor.commit();
    }

    public static void clearSharedPreference(Context context) {
        SharedPreferences mSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = mSharedPrefs.edit();
        editor.clear();
        editor.commit();
    }
}
