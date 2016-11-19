package com.devsh.livecast.live;

import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface LiveService {

    @POST("/android/create_live.json")
    Observable<LiveResponse> createLive(@Query("room_name") String roomName);
}
