package kaelyntoth.friendfinder;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Camera extends ActionBarActivity {

    public static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    //public static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 200;

    private Uri fileUri;
    public static final int MEDIA_TYPE_IMAGE = 1;
    Button captureButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        captureButton = (Button) findViewById(R.id.captureButton);

        if (!hasCamera()) {
            captureButton.setEnabled(false);
        }
    }

    //check to see if the device has a camera
    private boolean hasCamera() {
        if (getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA_ANY)) {
            //device does have camera
            return true;
        } else {
            return false;
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_camera, menu);
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

    public void captureClick(View view) {
        //create intent to take a picture and return control to app

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File mediaFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+ "/mypicture.jpg");

        fileUri = Uri.fromFile(mediaFile); //create file to save image

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); //set image file name

        //start image capture intent
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

       /* File mediaFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/mypicture.jpg");
        fileUri = Uri.fromFile(mediaFile);
        \*/
    }

    //receive the intent result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                //image captured and saved to file uri specified in intent
                Toast.makeText(this, "Image was saved", Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED) {
                //user cancelled image capture
                Toast.makeText(this, "capture cancelled.",
                        Toast.LENGTH_LONG).show();
            } else {
                //image capture failed
                Toast.makeText(this, "failed to capture image",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    //start method for saving the image

    private static Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    //create file for saving image
    private static File getOutputMediaFile(int type){
        //to be safe, check that sdcard is mounted



        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "FriendFinder");
        //this location wokrs best if you want the created images to be shared
        //between apps and persist after app is uninstalled

        //create storage directory

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("Camera", "failed to create directory");
                return null;
            }
        }

        //create media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_" + timeStamp + ".jpg");

        } else {
            return null;
        }

        return mediaFile;
    }
}
