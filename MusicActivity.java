package fabulous.a511.sports.com.fabulous_fin;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
/**
 * Created by shooting on 2017/2/4.
 */

public class MusicActivity extends Activity
{
    private MediaPlayer mp=new MediaPlayer();
    private Button play;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music);
        play= (Button) findViewById(R.id.play);
        final MediaPlayer mp =MediaPlayer.create(this, R.raw.fade);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mp != null) {
            mp.stop();
            mp.release();
        }
    }
}
