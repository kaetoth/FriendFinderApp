package kaelyntoth.friendfinder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class Email extends ActionBarActivity {

    private EditText mMessage;
    private EditText mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        mMessage = (EditText) findViewById(R.id.smsBody);
        mEmail = (EditText) findViewById(R.id.emailAddress);

    }



    public void onEmailSend(View view){
        String emailAddress = mEmail.getText().toString();
        String msgBody = mMessage.getText().toString();

        if(mMessage.length()!=0 && mEmail.length() != 0) {

            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.setType("text/plain");

            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String [] {emailAddress});
            emailIntent.putExtra(Intent.EXTRA_TEXT, msgBody);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Email from my app!");

            try{
                startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                //clear address and msg
                mEmail.setText("");
                mMessage.setText("");

            } catch(Exception e){
                Toast.makeText(getApplicationContext(),
                        "problem sending email",
                        Toast.LENGTH_LONG).show();
            }

        }else
            Toast.makeText(getApplicationContext(),
                    "please enter both email address and message",
                    Toast.LENGTH_LONG).show();

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
