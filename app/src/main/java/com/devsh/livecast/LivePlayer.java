package com.devsh.livecast;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;

public class LivePlayer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_player);

        VideoView videoView = (VideoView) findViewById(R.id.video_view);
        videoView.setVideoURI(Uri.parse("rtsp://52.78.228.123:1935/live/myStream"));
        videoView.start();

    }
}
