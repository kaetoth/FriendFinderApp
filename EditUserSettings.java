package kaelyntoth.friendfinder;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class EditUserSettings extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_settings);

        //screen transitions

        Button x = (Button)findViewById(R.id.emailShareButton);

        final Intent myIntent = new Intent(this, Email.class);
        x.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                startActivity(myIntent);
            }
        });

        Button y = (Button)findViewById(R.id.imageSettingButton);

        final Intent myIntent2 = new Intent(this, Camera.class);
        y.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                startActivity(myIntent2);
            }
        });

        Button z = (Button)findViewById(R.id.locationSettingButton);

        final Intent myIntent3 = new Intent(this, editLocationSettings.class);
        z.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                startActivity(myIntent3);
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_user_settings, menu);
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
