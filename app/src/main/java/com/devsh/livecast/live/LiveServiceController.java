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
}
