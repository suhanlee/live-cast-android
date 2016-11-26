package com.devsh.livecast.live;

import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface LiveService {

    @POST("/android/create_live.json")
    Observable<LiveResponse> createLive(@Query("room_name") String roomName);

    @DELETE("/android/live/{app_name}")
    Observable<LiveResponse> deleteLive(@Path("app_name") String appName);
}
