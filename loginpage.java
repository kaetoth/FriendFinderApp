package kaelyntoth.friendfinder;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;


public class loginpage extends ActionBarActivity {

    //handled to GUI components
    TextView mUserIDView;
    EditText mFirstNameTextView;
    EditText mLastNameTextView;
    ListView mListView;
    private static HashMap mHashMap;
    private static ListView mlistViewMap;
    private static TextView mTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

        mUserIDView = (TextView) findViewById(R.id.userIDTextValue);
        mFirstNameTextView = (EditText) findViewById(R.id.editFname);
        mLastNameTextView = (EditText) findViewById(R.id.editLname);
        mListView = (ListView) findViewById(R.id.editlistView);
        updateListView();
    }

    //get the most recent database entries

    private void updateListView(){
        //create db handler
        UserDBHandler dbHandler = new UserDBHandler(this, null, null, 1);

        //get users to display from handler
        User [] myUsers = dbHandler.displayUsers(4);

        //set adapter for listview
        ArrayAdapter<User> adapter = new ArrayAdapter<User>(this, android.R.layout.simple_list_item_1
                , android.R.id.text1, myUsers);

        mListView.setAdapter(adapter);
    }


    public void onAddClick(View view) {
        //create db
        UserDBHandler dbHandler = new UserDBHandler(this, null, null, 1);


        //get user indicated first name

        String myFirstname = mFirstNameTextView.getText().toString();
        //get user indicated last name
        String myLastname = mLastNameTextView.getText().toString();

        //make new student with these values

        User myUser = new User(myFirstname, myLastname);

        //add student to db
        dbHandler.addUser(myUser);

        //clear input fields
        mFirstNameTextView.setText("");
        mLastNameTextView.setText("");
        mUserIDView.setText("");

        updateListView();

    }

    public void onFindClick(View view) {

        //create DB handler
        UserDBHandler dbHandler = new UserDBHandler(this, null, null, 1);

        //get the user indicated lastname
        String myLastname = mLastNameTextView.getText().toString();

        //see if user exists with that lastname

        User myUser = dbHandler.findUser(myLastname);

        if (myUser != null) {
            //set names to be those for returned user value
            mUserIDView.setText("match found!");
            mFirstNameTextView.setText(String.valueOf(myUser.getFirstName()));
            mLastNameTextView.setText(String.valueOf(myUser.getLastName()));
        } else
            mUserIDView.setText("No Find Match Found");

        updateListView();
    }

    public void onDeleteClick(View view) {
        //create DB handler
        UserDBHandler dbHandler = new UserDBHandler(this, null, null, 1);

        //get user indicated lastname
        String myLastname = mLastNameTextView.getText().toString();


        //delete record
        boolean myResult = dbHandler.deleteUser(myLastname);

        //if deleted successfully
        if (myResult) {
            mUserIDView.setText("record deleted");
            mFirstNameTextView.setText("");
            mLastNameTextView.setText("");
        }
        else
            mUserIDView.setText("no delete match found");

        updateListView();

    }

    public void onUpdateClick(View view) {
        //create DB handler
        UserDBHandler dbHandler = new UserDBHandler(this, null, null, 1);

        //update the view

        updateListView();
    }







        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_loginpage, menu);
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

    public void onHashMapClick(View view) {
        String tmpString;

        //create an empty hashmap
        mHashMap = new HashMap();
        tmpString = "\n";
        //Add to the hashmap
        mHashMap.put(27, "Kaelyn Toth");
        mHashMap.put(17, "Allie Kratowicz");
        mHashMap.put(15, "Arika Jeter");
        mHashMap.put(12, "Kelsey Kennedy");

        /*//at least one duplicate key
        mHashMap.put(17, "Cara");


        tmpString += "HashMap size after add: " + mHashMap.size() + "\n";
        tmpString += "HashMap is: " + mHashMap + "\n";

        //check to see if a key is in the map
        tmpString += "HashMap contains 12: " + mHashMap.containsKey(12) + "\n";

        //add one that isn't
        tmpString += "HashMap contains 13: " + mHashMap.containsKey("13") + "\n";
*/
        //Get all keys
        tmpString += "Keys are: " + mHashMap.keySet() + "\n";
        tmpString += "Values includes: " + mHashMap.values() + "\n";
        tmpString += "ID and name pair is: " + mHashMap.entrySet() + "\n\n";


/*        //Remove an item
        mHashMap.remove(17);
        tmpString += "HashMap size after remove: " + mHashMap.size() + "\n";
        tmpString += "HashMap is: " + mHashMap + "\n";*/

        mTextView.setText(tmpString);

        //set adapter for listview -- create a class for the data.
        //ArrayAdapter<User> adapter = new ArrayAdapter<User>(this, android.R.layout.simple_list_item_1
        //       , android.R.id.text1, myUsers);

        //mListView.setAdapter(adapter);
    }
}




