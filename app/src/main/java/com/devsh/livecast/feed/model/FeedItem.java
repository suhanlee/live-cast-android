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

package com.devsh.livecast.feed.model;

public class FeedItem {
    private String movie_id;
    private String tags;
    private String movie_url;
    private String thumb_movie_url;
    private String movie_content_type;
    private String updated_at_text;
    private String movie_name;
    private boolean user_feed;

    public String getMovieId() {
        return movie_id;
    }

    public void setMovieId(String movieId) {
        movie_id = movieId;
    }

    public String getMovieUrl() {
        return movie_url;
    }

    public void setMovieUrl(String movieUrl) {
        this.movie_url = movieUrl;
    }

    public String getThumbMovieUrl() {
        return thumb_movie_url;
    }

    public void setThumbMovieUrl(String thumbMovieUrl) {
        this.thumb_movie_url = thumbMovieUrl;
    }

    public String getMovieContentType() {
        return movie_content_type;
    }

    public String getUpdatedAtText() {
        return updated_at_text;
    }

    public boolean getUserFeed() {
        return user_feed;
    }

    public void setUserFeed(boolean userFeed) {
        user_feed = userFeed;
    }

    public String getMovieName() {
        return movie_name;
    }

    public void setMovieName(String movieName) {
        this.movie_name = movieName;
    }

}
