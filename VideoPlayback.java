package kaelyntoth.friendfinder;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.VideoView;


public class VideoPlayback extends ActionBarActivity {

    public static VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_playback);

        //get pointer to VideoView, use findViewById
        //set VideoView path

        mVideoView = (VideoView) findViewById(R.id.videoView);
        mVideoView.setVideoPath("http://www.ebookfrenzy.com/android_book/movie.mp4");


        //create media controller
        MediaController myMediaController = new MediaController(this);

        //link media controller to video view with setanchor
        myMediaController.setAnchorView(mVideoView);

        //link videoview to media controller with setmediacontroller
        mVideoView.setMediaController(myMediaController);

        //start video
        mVideoView.start();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
