package com.devsh.livecast.live;

import android.content.Context;

import com.devsh.livecast.common.Common;
import com.devsh.livecast.common.ServiceGenerator;

import rx.Observable;

public class LiveServiceController {

    public static Observable<LiveResponse> createLive(Context context, String roomName) {
        LiveService service = ServiceGenerator.createService(Common.SERVER_URL, LiveService.class);
        Observable<LiveResponse> response = service.createLive(roomName);
        return response;
    }

    public static Observable<LiveResponse> deleteLive(Context context, String appName) {
        LiveService service = ServiceGenerator.createService(Common.SERVER_URL, LiveService.class);
        Observable<LiveResponse> response = service.deleteLive(appName);

        return response;
    }
}
