package jsmeli.com.smallrecordvideo;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;

import java.io.File;

public class PlayVideoActivity extends Activity {

    private MyVideoView videoView;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
        videoView = (MyVideoView) findViewById(R.id.video);
        Button playBtn = (Button) findViewById(R.id.play_video);
        Intent intent = getIntent();
        path = intent.getStringExtra("path");
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play();
            }
        });
    }
    private void play() {
        Uri uri = Uri.parse(path);
        videoView.setVideoURI(uri);
        MediaController mMediaController = new MediaController(this);
        videoView.setMediaController(mMediaController);
        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
                mp.setLooping(true);
            }
        });
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

            }
        });
    }
}
