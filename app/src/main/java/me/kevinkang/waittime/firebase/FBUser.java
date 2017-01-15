package me.kevinkang.waittime.firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import me.kevinkang.waittime.model.User;

import static android.R.attr.id;

/**
 * Created by kevink97 on 1/14/17.
 */

public class FBUser implements User {
    private int maxWaitTime;
    private final String UID;
    private static int DEFAULT_WAIT_TIME = 20; // in minutes
    private DatabaseReference db;

    /**
     * Initializes FBUser with its unique ID
     * @param UID unique ID for user generated from Firebase
     */
    public FBUser(String UID) {
        this(UID, DEFAULT_WAIT_TIME);
    }

    /**
     * Initializes FBUser with its unique ID AND max wait time the user wants
     * @param UID unique ID for user genderated from Firebase
     * @param maxWaitTime the max wait time the user wants
     */
    public FBUser(String UID, int maxWaitTime) {
        this.maxWaitTime = maxWaitTime;
        db = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            this.UID = user.getUid();
        } else {
            this.UID = "DEFAULT";
        }

        // add user to firebase if they are new
        if (db.child("users").child(UID) == null) {
            db.child("users").child(UID).setValue(id);
            db.child("users").child(UID).child("max-time").setValue(this.maxWaitTime);
        }

    }

    public FBUser retrieveUser(String UID) {
        if (db.child("users").child(UID) == null) return null;
        FBUser user = new FBUser(UID);
        Query query = db.child("users").child(UID)
        return new FBUser(UID);
    }

    /**
     * gets the UID of user
     *
     * @return UID of user
     */
    @Override
    public String getUID() {
        return this.UID;
    }

    /**
     * sets the max wait time the user wants
     *
     * @param newMaxWaitTime the new max wait time
     */
    @Override
    public void setMaxWaitTime(int newMaxWaitTime) {
        this.maxWaitTime = newMaxWaitTime;
    }

    /**
     * gets the max wait time the user wants
     *
     * @return the max wait time
     */
    @Override
    public int maxWaitTime() {
        return this.maxWaitTime;
    }
}
