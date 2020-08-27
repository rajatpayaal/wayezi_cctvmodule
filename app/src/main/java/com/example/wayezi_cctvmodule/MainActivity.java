package com.example.wayezi_cctvmodule;
import java.io.IOException;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ToggleButton;

public class MainActivity extends Activity implements SurfaceHolder.Callback {
    private final String VIDEO_PATH_NAME = "/mnt/sdcard/121212   .mp4";

    private MediaRecorder rmediaRecorder;
    private Camera rCamera;
    private SurfaceView rsurfaceView;
    private SurfaceHolder rHolder;
    private View rtoggleButton;
    private boolean rInitSuccesful;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // we shall take the video in landscape orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);

        rsurfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        rHolder = rsurfaceView.getHolder();
        rHolder.addCallback(this);
        rHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        rtoggleButton = (ToggleButton) findViewById(R.id.toggleRecordingButton);
        rtoggleButton.setOnClickListener(new OnClickListener() {
            @Override
            // toggle video recording // resolve that part stop on toggle button
            public void onClick(View v) {
                if (((ToggleButton) v).isChecked()) {
                    rmediaRecorder.start();
                    try {
                        Thread.sleep(30 * 1000); // This will recode for 10 seconds, you can change this.
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }else {
                    rmediaRecorder.stop();
                    rmediaRecorder.reset();
                    try {
                        initRecorder(rHolder.getSurface());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /* Init the MediaRecorder, the order the methods are called is vital to
     * its correct functioning */
    private void initRecorder(Surface surface) throws IOException {
        // It is very important to unlock the camera before doing setCamera
        // or it will results in a black preview
        if (rCamera == null) {
            rCamera = Camera.open();
            rCamera.unlock();
        }

        if (rmediaRecorder == null) rmediaRecorder = new MediaRecorder();
        rmediaRecorder.setPreviewDisplay(surface);
        rmediaRecorder.setCamera(rCamera);

        rmediaRecorder.setVideoSource(MediaRecorder.VideoSource.DEFAULT);
        //       rmediaRecorder.setOutputFormat(8);
        rmediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        rmediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
        rmediaRecorder.setVideoEncodingBitRate(512 * 1000);
        rmediaRecorder.setVideoFrameRate(30);
        rmediaRecorder.setVideoSize(640, 480);
        rmediaRecorder.setOutputFile(VIDEO_PATH_NAME);

        rmediaRecorder.prepare();

        rInitSuccesful = true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            if (!rInitSuccesful)
                initRecorder(rHolder.getSurface());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        shutdown();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    private void shutdown() {
        // Release MediaRecorder and especially the Camera as it's a shared
        // object that can be used by other applications
        rmediaRecorder.reset();
        rmediaRecorder.release();
        rCamera.release();

        // once the objects have been released they can't be reused
        rmediaRecorder = null;
        rCamera = null;
    }
}