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

package com.devsh.livecast.feed;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.devsh.livecast.LivePlayer;
import com.devsh.livecast.MainActivity;
import com.devsh.livecast.R;
import com.devsh.livecast.feed.model.FeedItem;

import java.util.List;


public class FeedRecyclerAdapter extends RecyclerView.Adapter<FeedRecyclerAdapter.CustomViewHolder> {
    private List<FeedItem> mFeedItemList;

    private Activity mActivity;


    public FeedRecyclerAdapter(FragmentActivity activity, List<FeedItem> feedItems) {
        this.mActivity = activity;
        this.mFeedItemList = feedItems;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.feed_list_row, viewGroup, false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        FeedItem feedItem = mFeedItemList.get(position);

        holder.authorName.setText(feedItem.getMovieName());

        String url = feedItem.getThumbMovieUrl();
        if (url != null) {
            Uri uri = Uri.parse(url);

            if (uri != null) {
                // Movie Image
                Glide.with(mActivity)
                        .load(uri)
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .into(holder.imageView);
            }
        }

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mActivity, LivePlayer.class);
                intent.putExtra("movie_url", feedItem.getMovieUrl());
                mActivity.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mFeedItemList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView blurPanel;

        protected TextView authorName;

        protected ImageView imageView;

        protected ImageView feedbackLike;

        public CustomViewHolder(View view) {
            super(view);
            this.authorName = (TextView) view.findViewById(R.id.author_profile_name);

            this.imageView = (ImageView) view.findViewById(R.id.videoView);
            this.feedbackLike = (ImageView) view.findViewById(R.id.image_like);
            this.blurPanel = (ImageView) view.findViewById(R.id.blur_pannel);
        }
    }
}