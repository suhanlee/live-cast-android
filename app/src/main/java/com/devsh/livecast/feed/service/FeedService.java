/*
 *
 *  Copyright (C) 2016 Suhan Lee
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.devsh.livecast.feed.service;



import com.devsh.livecast.feed.model.Feed;
import com.devsh.livecast.feed.model.FeedItem;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface FeedService {

    @GET("/android/feeds.json")
    Call<Feed> getFeeds(@Query("api_token") String apiToken);

    @GET("/android/feed.json")
    Observable<Feed> getFeeds2(@Query("api_token") String apiToken);

    @GET("/android/feed/{movieId}.json")
    Call<FeedItem> getFeedItems(@Path("movieId") String movieId, @Query("api_token") String apiToken);

    @DELETE("/android/feed/{movieId}.json")
    Observable<FeedItem> deleteItems(@Path("movieId") String movieId, @Query("api_token") String apiToken);
}
