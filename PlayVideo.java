package fabulous.a511.sports.com.fabulous_fin;

import android.app.Activity;
import android.os.Bundle;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;


/**
 * Created by shooting on 2017/1/10.
 */

public class PlayVideo extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.playvideo);
        JCVideoPlayerStandard jcVideoPlayerStandard = (JCVideoPlayerStandard) findViewById(R.id.videoplayer);
        jcVideoPlayerStandard.setUp("https://staticssl.keepcdn.com/video/2016/6/1/A001C014.mp4"
                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "前绕肩");
        JCVideoPlayerStandard jcVideoPlayerStandard1 = (JCVideoPlayerStandard) findViewById(R.id.videoplayer2);
        jcVideoPlayerStandard1.setUp("http://staticssl.keepcdn.com/video/2016/6/1/A001C014.mp4"
                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "后绕肩");
        JCVideoPlayerStandard jcVideoPlayerStandard2 = (JCVideoPlayerStandard) findViewById(R.id.videoplayer3);
        jcVideoPlayerStandard2.setUp("https://staticssl.keepcdn.com/video/2016/6/1/A001C029.mp4"
                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "屈膝转体热身");
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
        JCVideoPlayer.clearSavedProgress(this,null);
    }
}
