/**
 *  This is sample code provided by Wowza Media Systems, LLC.  All sample code is intended to be a reference for the
 *  purpose of educating developers, and is not intended to be used in any production environment.
 *
 *  IN NO EVENT SHALL WOWZA MEDIA SYSTEMS, LLC BE LIABLE TO YOU OR ANY PARTY FOR DIRECT, INDIRECT, SPECIAL, INCIDENTAL,
 *  OR CONSEQUENTIAL DAMAGES, INCLUDING LOST PROFITS, ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION,
 *  EVEN IF WOWZA MEDIA SYSTEMS, LLC HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  WOWZA MEDIA SYSTEMS, LLC SPECIFICALLY DISCLAIMS ANY WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. ALL CODE PROVIDED HEREUNDER IS PROVIDED "AS IS".
 *  WOWZA MEDIA SYSTEMS, LLC HAS NO OBLIGATION TO PROVIDE MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS, OR MODIFICATIONS.
 *
 *  Copyright © 2015 Wowza Media Systems, LLC. All rights reserved.
 */

package com.devsh.livecast;

import android.Manifest;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;

import com.devsh.livecast.common.SharedData;
import com.devsh.livecast.live.LiveResponse;
import com.devsh.livecast.live.LiveServiceController;
import com.devsh.livecast.ui.AutoFocusListener;
import com.devsh.livecast.ui.MultiStateButton;
import com.devsh.livecast.ui.TimerView;
import com.wowza.gocoder.sdk.api.devices.WZCamera;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CameraActivity extends CameraActivityBase {
    private final static String TAG = CameraActivity.class.getSimpleName();

    // UI controls
    protected MultiStateButton mBtnSwitchCamera  = null;
    protected MultiStateButton      mBtnTorch         = null;
    protected TimerView mTimerView        = null;

    // Gestures are used to toggle the focus modes
    protected GestureDetectorCompat mAutoFocusDetector = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        mRequiredPermissions = new String[] {
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO
        };

        // Initialize the UI controls
        mBtnTorch           = (MultiStateButton) findViewById(R.id.ic_torch);
        mBtnSwitchCamera    = (MultiStateButton) findViewById(R.id.ic_switch_camera);
        mTimerView          = (TimerView) findViewById(R.id.txtTimer);
    }

    /**
     * Android Activity lifecycle methods
     */
    @Override
    protected void onResume() {
        super.onResume();

        if (sGoCoderSDK != null && mWZCameraView != null) {
            if (mAutoFocusDetector == null)
                mAutoFocusDetector = new GestureDetectorCompat(this, new AutoFocusListener(this, mWZCameraView));

            WZCamera activeCamera = mWZCameraView.getCamera();
            if (activeCamera != null && activeCamera.hasCapability(WZCamera.FOCUS_MODE_CONTINUOUS))
                activeCamera.setFocusMode(WZCamera.FOCUS_MODE_CONTINUOUS);
        }

    }

    private void deleteApplication() {
        String appName = SharedData.getLiveApplicationName(getApplicationContext());
        if (!TextUtils.isEmpty(appName)) {
            Observable<LiveResponse> liveResponse = LiveServiceController.deleteLive(getApplicationContext(), appName);
            liveResponse.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe( body-> {
                        SharedData.putLiveApplicationName(getApplicationContext(), null);
                        finish();
                    }, erros -> {
                        finish();
                    });
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder d = new AlertDialog.Builder(this);
        d.setMessage("정말 종료하시겠습니까?");
        d.setPositiveButton("예", new DialogInterface.OnClickListener() {


            public void onClick(DialogInterface dialog, int which) {
                // process전체 종료
                deleteApplication();
            }
        });
        d.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        d.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * Click handler for the switch camera button
     */
    public void onSwitchCamera(View v) {
        if (mWZCameraView == null) return;

        mBtnTorch.setState(false);
        mBtnTorch.setEnabled(false);

        WZCamera newCamera = mWZCameraView.switchCamera();
        if (newCamera != null) {
            if (newCamera.hasCapability(WZCamera.FOCUS_MODE_CONTINUOUS))
                newCamera.setFocusMode(WZCamera.FOCUS_MODE_CONTINUOUS);

            boolean hasTorch = newCamera.hasCapability(WZCamera.TORCH);
            if (hasTorch) {
                mBtnTorch.setState(newCamera.isTorchOn());
                mBtnTorch.setEnabled(true);
            }
        }
    }

    /**
     * Click handler for the torch/flashlight button
     */
    public void onToggleTorch(View v) {
        if (mWZCameraView == null) return;

        WZCamera activeCamera = mWZCameraView.getCamera();
        activeCamera.setTorchOn(mBtnTorch.toggleState());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mAutoFocusDetector != null)
            mAutoFocusDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    /**
     * Update the state of the UI controls
     */
    @Override
    protected boolean syncUIControlState() {
        boolean disableControls = super.syncUIControlState();

        if (disableControls) {
            mBtnSwitchCamera.setEnabled(false);
            mBtnTorch.setEnabled(false);
        } else {
            boolean isDisplayingVideo = (getBroadcastConfig().isVideoEnabled() && mWZCameraView.getCameras().length > 0);
            boolean isStreaming = getBroadcast().getStatus().isRunning();

            if (isDisplayingVideo) {
                WZCamera activeCamera = mWZCameraView.getCamera();

                boolean hasTorch = (activeCamera != null && activeCamera.hasCapability(WZCamera.TORCH));
                mBtnTorch.setEnabled(hasTorch);
                if (hasTorch) {
                    mBtnTorch.setState(activeCamera.isTorchOn());
                }

                mBtnSwitchCamera.setEnabled(mWZCameraView.getCameras().length > 0);
                //mBtnSwitchCamera.setEnabled(mWZCameraView.isSwitchCameraAvailable());
            } else {
                mBtnSwitchCamera.setEnabled(false);
                mBtnTorch.setEnabled(false);
            }

            if (isStreaming && !mTimerView.isRunning()) {
                mTimerView.startTimer();
            } else if (getBroadcast().getStatus().isIdle() && mTimerView.isRunning()) {
                mTimerView.stopTimer();
            } else if (!isStreaming) {
                mTimerView.setVisibility(View.GONE);
            }
        }

        return disableControls;
    }
}
