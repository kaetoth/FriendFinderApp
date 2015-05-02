package kaelyntoth.friendfinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;


public class MainActivity extends ActionBarActivity {

    private static HashMap mHashMap;
    private static ListView mlistViewMap;
    private static TextView mTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView)findViewById(R.id.textView);

        Button b = (Button)findViewById(R.id.contactAccessButton);

        final Intent myIntent2 = new Intent(this, loginpage.class);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(myIntent2);
            }
        });

        ImageButton c = (ImageButton)findViewById(R.id.homeButton2);

        final Intent myIntent = new Intent(this, EditUserSettings.class);
        c.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                startActivity(myIntent);
            }
        });


        Button d = (Button)findViewById(R.id.mapScreenButton);

        final Intent myIntent3 = new Intent(this, MapsActivity.class);
        d.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(myIntent3);
            }
        });

    }

    /*     public void findNearby(){
            String tmpString = null;

            //get everything from database select *
            //get db handler and select all
            //for loop, put each one in the hashmap and later will validate

            //filter for location
            //mHashMap.put
            //in design doc make sure to say that it doesn't make sense for me to delete in my app
            //so to distribute those points in a different way
            //implement method later for specificity, just all for now
            //


            //create empty hashmap
            mHashMap = new HashMap();

            //mHashMap.put(//id and the object that was created from the );


            //put in the key and value pairs
            mHashMap.put(1, "friend 1");
            mHashMap.put(2, "friend 2");
            mHashMap.put(3, "friend 3");
            mHashMap.put(4, "friend 4");


           //set text for the listview
            tmpString += "Your friends nearby are: " + "\n" + mHashMap;

            //get users to display from hashmap
            //get all data from userdbhandler
            User [] myUsers = dbHandler.displayUsers(4);

            //set adapter for listview
            ArrayAdapter<User> adapter = new ArrayAdapter<User>(this, android.R.layout.simple_list_item_1
                    , android.R.id.text1, myUsers);

            mListView.setAdapter(adapter);
        }
    */



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
