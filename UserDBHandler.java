package kaelyntoth.friendfinder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kaelyntoth on 3/08/15.
 */

public class UserDBHandler extends SQLiteOpenHelper {
    //declare db constants so only have one place to change them as updates are made
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "mydatabase.db";

    //declare TABLE constant for single place to make edits
    private static final String TABLE_USERS = "Users";
    public static final String COLUMN_USERID = "user_id";
    public static final String COLUMN_LASTNAME = "lastname";
    public static final String COLUMN_FIRSTNAME = "firstname";

    //constructor
    public UserDBHandler(Context context,
                         String name,
                         SQLiteDatabase.CursorFactory factory,
                         int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    //override etc
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " +
                TABLE_USERS + "(" +
                COLUMN_USERID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FIRSTNAME + " TEXT, " +
                COLUMN_LASTNAME + " TEXT);";
        //EXECUTE SQL COMMANDS
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //DROP TABLE IF EXISTS
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);

        //recreate the table by calling onCreate
        onCreate(db);
    }


    //add a user to the table in db

    public void addUser(User user) {
        //prepare values for new entry
        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRSTNAME, user.getFirstName());
        values.put(COLUMN_LASTNAME, user.getLastName());

        //open db
        SQLiteDatabase db = this.getWritableDatabase();

        //insert into db
        db.insert(TABLE_USERS, null, values);

        //close db
        db.close();
    }

    public boolean deleteUser(String username) {
        //set default return value
        boolean result = false;

        //construct sql string
        String sql_query = "SELECT * FROM " + TABLE_USERS + " WHERE "
                + COLUMN_LASTNAME + " = \"" + username + "\"";

        //open db
        SQLiteDatabase db = this.getWritableDatabase();

        //execute query
        Cursor myCursor = db.rawQuery(sql_query, null);

        //create empty object
        User myUser = new User();

        //if row returned, point to first entry and extract data
        if (myCursor.moveToFirst()) {
            //get ID
            myUser.setID(myCursor.getInt(0));

            //delete entry with userID
            db.delete(TABLE_USERS, COLUMN_USERID + " = ?",
                    new String[]{String.valueOf(myUser.getID())});
            //close cursor
            myCursor.close();
            //set return result to true for successful delete
            result = true;
        }
        //close db
        db.close();
        return result;

    }

    public User findUser(String username) {
        //construct sql string
        String sql_query = "SELECT * FROM " + TABLE_USERS +
                " WHERE " + COLUMN_LASTNAME + " = \"" +
                username + "\"";
        //open db
        SQLiteDatabase db = this.getWritableDatabase();

        //execute query, store returned rows
        Cursor myCursor = db.rawQuery(sql_query, null);

        //create empty user object
        User myUser = new User();

        //if row returned, point to 1st entry, extract data
        if (myCursor.moveToFirst()) {
            myUser.setID(myCursor.getInt(0));
            myUser.setFirstName(myCursor.getString(1));
            myUser.setLastName(myCursor.getString(2));
            myCursor.close();
        } else
            //nothing returned
            myUser = null;

        //close db
        db.close();
        return myUser;
    }

    public User[] displayUsers(int number) {
        //construct SQL string: SELECT * FROM TABLE
        String sql_query = "SELECT * FROM " + TABLE_USERS;

        //open db
        SQLiteDatabase db = this.getWritableDatabase();

        //execute query, story returned row
        Cursor myCursor = db.rawQuery(sql_query, null);
        User[] myUsers;

        if (myCursor != null) {
            //set number of items to get to be number or number in database if less than number

            int count = myCursor.getCount();
            int length = count;
            if (length > number)
                length = number;

            //create array of User objects
            myUsers = new User[length];


            //if at least # users exist, set index to start at correct spot, otherwise index starts at 0
            int index = count - length;
            myCursor.moveToFirst();
            myCursor.move(index);

            for (int i = 0; i < length; i++) {
                myUsers[i] = new User();
                myUsers[i].setID(myCursor.getInt(0));
                myUsers[i].setFirstName(myCursor.getString(1));
                myUsers[i].setLastName(myCursor.getString(2));
                myCursor.moveToNext();

            }

            myCursor.close();

        }
        else {
            myUsers = new User[1];
            myUsers[0] = new User();
            myUsers[0].setID(0);
            myUsers[0].setFirstName("empty");
            myUsers[0].setLastName("empty");
        }
        //close db
        db.close();
        return myUsers;
    }
}





