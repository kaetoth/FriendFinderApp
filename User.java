package kaelyntoth.friendfinder;

/**
 * Created by kaelyntoth on 3/10/15.
 */

public class User {
    private int mID;
    private String mFirstName;
    private String mLastName;

    public User(){

    }

    public User(int id, String fname, String lname){
        mID = id;
        mFirstName = fname;
        mLastName = lname;
    }
    public User( String fname, String lname){

        mFirstName = fname;
        mLastName = lname;
    }
    public int getID() {
        return mID;
    }

    public void setID(int ID) {
        mID = ID;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    @Override
    public String toString(){
        return mFirstName + " " + mLastName;
    }
}
