package jsmeli.com.smallrecordvideo;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import jsmeli.com.smallrecordvideo.R;

public class MainActivity extends Activity {

    private RecordVideo mRecorderView;//视频录制控件
    private Button mShootBtn;//视频开始录制按钮
    private boolean isFinish = true;
    private boolean success = false;//防止录制完成后出现多次跳转事件
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecorderView = (RecordVideo) findViewById(R.id.movieRecorderView);
        mShootBtn = (Button) findViewById(R.id.shoot_button);

        mShootBtn.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mRecorderView.record(new RecordVideo.OnRecordFinishListener() {

                        @Override
                        public void onRecordFinish() {
                            handler.sendEmptyMessage(1);
                        }
                    });
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (mRecorderView.getTimeCount() > 1)
                        handler.sendEmptyMessage(1);
                    else {
                        if (mRecorderView.getmRecordFile() != null)
                            mRecorderView.getmRecordFile().delete();
                        mRecorderView.stop();
                        Toast.makeText(MainActivity.this, "视频录制时间太短", Toast.LENGTH_SHORT).show();
                    }
                }
                return true;
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        isFinish = true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        isFinish = false;
        mRecorderView.stop();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            finishActivity();
        }
    };

    private void finishActivity() {
        if (isFinish) {
            mRecorderView.stop();
            // 返回到播放页面
            Intent intent = new Intent(MainActivity.this, PlayVideoActivity.class);
            Log.d("TAG", mRecorderView.getmRecordFile().getAbsolutePath());
            intent.putExtra("path", mRecorderView.getmRecordFile().getAbsolutePath());
            startActivity(intent);
        }
        // isFinish = false;
        finish();
    }

    /**
     * 录制完成回调
     *
     * @author liuyinjun
     *
     * @date 2015-2-9
     */
    public interface OnShootCompletionListener {
        public void OnShootSuccess(String path, int second);
        public void OnShootFailure();
    }

}

