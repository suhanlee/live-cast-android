package com.devsh.livecast;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.VideoView;

public class LivePlayer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_player);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        String movieUrl = intent.getExtras().getString("movie_url");

        Toast.makeText(getApplicationContext(), "url:" + movieUrl, Toast.LENGTH_SHORT).show();
        if (movieUrl != null) {
            VideoView videoView = (VideoView) findViewById(R.id.video_view);
            videoView.setVideoURI(Uri.parse(movieUrl));
            videoView.start();
        }
    }
}
