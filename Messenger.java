package kaelyntoth.friendfinder;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;


public class Messenger extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);


        ImageButton g = (ImageButton)findViewById(R.id.kael2button);

        final Intent myIntent = new Intent(this, SampleProfile.class);
        g.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                startActivity(myIntent);
            }
        });


        /*ImageButton h = (ImageButton)findViewById(R.id.kels2Button);

        final Intent myIntent2 = new Intent(this, SampleProfile.class);
        h.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                startActivity(myIntent2);
            }
        });


        ImageButton i = (ImageButton)findViewById(R.id.arika2Button);

        final Intent myIntent3 = new Intent(this, SampleProfile.class);
        i.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                startActivity(myIntent3);
            }
        });

        ImageButton j = (ImageButton)findViewById(R.id.allie2Button);

        final Intent myIntent4 = new Intent(this, SampleProfile.class);
        j.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                startActivity(myIntent4);
            }
        });*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_messenger, menu);
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
