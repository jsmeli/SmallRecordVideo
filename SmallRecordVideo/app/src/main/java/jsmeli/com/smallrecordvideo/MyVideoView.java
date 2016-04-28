package jsmeli.com.smallrecordvideo;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * Created by xiaomengli on 16/4/28.
 */
public class MyVideoView extends VideoView{
    public MyVideoView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MyVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyVideoView(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getDefaultSize(0, widthMeasureSpec);
        int height = getDefaultSize(0, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }
}
