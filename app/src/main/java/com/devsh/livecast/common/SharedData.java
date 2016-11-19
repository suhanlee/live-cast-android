package com.devsh.livecast.common;

import android.content.Context;

public class SharedData extends ASharedData {

    private static String PUSH_REGISTRATION_TOKEN ="PUSH_REGISTRATION_TOKEN_KEY";
    private static String SERVER_TOKEN = "SERVER_TOKEN";
    private static String USER_PHOTO_KEY = "USER_PHOTO_KEY";
    private static String USER_NICKNAME_KEY = "USER_NICKNAME_KEY";

    private static String LIVE_HOST_ADDRESS_KEY = "wz_live_host_address";
    private static String LIVE_APPLICATION_NAME_KEY = "wz_live_app_name";
    private static String LIVE_USER_NAME_KEY = "wz_live_username";
    private static String LIVE_USER_PASSWORD = "wz_live_password";

    public static void putPushRegistrationToken(Context context, String registrationToken) {
        putSharedPreferenceString(context, PUSH_REGISTRATION_TOKEN, registrationToken);
    }

    public static String getPushRegistrationToken(Context context) {
        return getSharedPreferenceString(context, PUSH_REGISTRATION_TOKEN);
    }

    public static void putServerToken(Context context, String serverToken) {
        putSharedPreferenceString(context, SERVER_TOKEN, serverToken);
    }

    public static String getServerToken(Context context) {
        return getSharedPreferenceString(context, SERVER_TOKEN);
    }

    public static String getAccountUserPhoto(Context context) {
        return getSharedPreferenceString(context, USER_PHOTO_KEY);
    }

    public static void putAccountUserPhoto(Context context, String photoURI) {
        putSharedPreferenceString(context, USER_PHOTO_KEY, photoURI);
    }

    public static String getAccountUserNickname(Context context) {
        return getSharedPreferenceString(context, USER_NICKNAME_KEY);
    }

    public static void putLiveHostAddress(Context context, String liveAddress) {
        putSharedPreferenceString(context, LIVE_HOST_ADDRESS_KEY, liveAddress);
    }

    public static void putLiveApplicationName(Context context, String applicationName) {
        putSharedPreferenceString(context, LIVE_APPLICATION_NAME_KEY, applicationName);
    }

    public static void putLiveUserName(Context context, String liveUserName) {
        putSharedPreferenceString(context, LIVE_USER_NAME_KEY, liveUserName);
    }

    public static void putLiveUserPassword(Context context, String liveUserPassword) {
        putSharedPreferenceString(context, LIVE_USER_PASSWORD, liveUserPassword);
    }
}
