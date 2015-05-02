package kaelyntoth.friendfinder;

import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;


public class MediaRecorder extends ActionBarActivity {


        private static android.media.MediaRecorder mMediaRecorder;
        private static MediaPlayer mMediaPlayer;
        private static Button mRecordButton;
        private static Button mPlayButton;
        private static Button mStopRecordButton;
        private static Button mStopPlayButton;
        private static String mAudioFilePath;
        private static TextView mTextView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_media_recorder);

            mRecordButton = (Button) findViewById(R.id.recordButton);
            mPlayButton = (Button) findViewById(R.id.playButton);
            mStopPlayButton = (Button) findViewById(R.id.stopPlayButton);
            mStopRecordButton = (Button) findViewById(R.id.stopRecordButton);
            mTextView = (TextView) findViewById(R.id.textView);

            //check for MIC on device and if not disable recorder
            PackageManager myManager = this.getPackageManager();
            if(myManager.hasSystemFeature(PackageManager.FEATURE_MICROPHONE))
                mRecordButton.setEnabled(true);

            else
                mRecordButton.setEnabled(false);

            //disable buttons that cannot be used at first
            mPlayButton.setEnabled(false);
            mStopPlayButton.setEnabled(false);
            mStopRecordButton.setEnabled(false);

            //set path for audio file
            mAudioFilePath = Environment.getExternalStorageDirectory().getAbsolutePath()
                    + "/myAudio.3gp";

            mTextView.setText("file" + mAudioFilePath +
                            "\nstate: " + Environment.getExternalStorageState()
            );


        }

        public void onRecord (View view) throws IOException {
            //configure recorder
            //disable this button and enable stopRecord button
            mRecordButton.setEnabled(false);
            mStopRecordButton.setEnabled(true);

            //start recording, use try/catch when creating
            try {
                mMediaRecorder = new android.media.MediaRecorder();
                mMediaRecorder.setAudioSource(android.media.MediaRecorder.AudioSource.MIC);
                mMediaRecorder.setOutputFormat(android.media.MediaRecorder.OutputFormat.AAC_ADTS);
                mMediaRecorder.setOutputFile(mAudioFilePath);
                mMediaRecorder.setAudioEncoder(android.media.MediaRecorder.AudioEncoder.AAC);
                // mMediaRecorder.setAudioChannels(1);
                // mMediaRecorder.setAudioSamplingRate(8000);
                mMediaRecorder.prepare();

            } catch (IOException e) {
                e.printStackTrace();
            }
            //start recording
            mMediaRecorder.start();
        }

        //when done recording
        public void onStopRecord(View view) {
            //disable this button, enable play
            mStopRecordButton.setEnabled(false);
            mPlayButton.setEnabled(true);

            //stop recording, release resource
            mMediaRecorder.stop();
            mMediaRecorder.release();
            mMediaRecorder = null;

        }



        public void onPlay (View view) throws IOException {
            //disable this and enable stopplay
            mPlayButton.setEnabled(false);
            mStopPlayButton.setEnabled(true);

            try {
                mMediaPlayer = new MediaPlayer();
                mMediaPlayer.setDataSource(mAudioFilePath);
                mMediaPlayer.prepare();
            } catch (Exception e){
                e.printStackTrace();
            }

            //start playback
            mMediaPlayer.start();

        }

        public void onStopPlay (View view){
            //disable this and enable record
            mStopPlayButton.setEnabled(false);
            mRecordButton.setEnabled(true);

            //stop the playback and release the resource
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
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