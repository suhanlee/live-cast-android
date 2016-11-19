package com.devsh.livecast;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.devsh.livecast.common.Common;
import com.devsh.livecast.common.SharedData;
import com.devsh.livecast.live.LiveResponse;
import com.devsh.livecast.live.LiveServiceController;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CreateLiveStreamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_live_stream);

        initUI();
    }

    private void initUI() {
        EditText editRoomName = (EditText) findViewById(R.id.edit_room_name);
        Button btnCreatelive= (Button)findViewById(R.id.button_create_live);
        btnCreatelive.setOnClickListener( v -> {
            String roomName = editRoomName.getText().toString();
            Observable<LiveResponse> response = LiveServiceController.createLive(getApplicationContext(), roomName);
            response.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe( live -> {
                        Toast.makeText(getApplicationContext(), "live url:" + live.movie_url, Toast.LENGTH_SHORT).show();
                        updatePrefs(live.app_name);
                        goCameraActivity();
                    }, erros -> {
                        Toast.makeText(getApplicationContext(), "Failed to create Live", Toast.LENGTH_SHORT).show();
                    });
        });

    }

    private void updatePrefs(String appName) {
        SharedData.putLiveHostAddress(this, Common.LIVE_SERVER_ADDRESS);
        SharedData.putLiveApplicationName(this, appName);
    }
    private void goCameraActivity() {
        Intent intent = new Intent(this, CameraActivity.class);
        startActivity(intent);
    }
}
