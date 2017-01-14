package me.kevinkang.waittime.firebase;

import me.kevinkang.waittime.model.User;

/**
 * Created by kevink97 on 1/14/17.
 */

public class FBUser implements User {
    private int maxWaitTime;
    private final String UID;
    private int DEFAULT_WAIT_TIME = 20; // in minutes

    /**
     * Initializes FBUser with its unique ID
     * @param UID unique ID for user generated from Firebase
     */
    public FBUser(String UID) {
        this.UID = UID;
        this.maxWaitTime = DEFAULT_WAIT_TIME;
    }

    /**
     * Initializes FBUser with its unique ID AND max wait time the user wants
     * @param UID unique ID for user genderated from Firebase
     * @param maxWaitTime the max wait time the user wants
     */
    public FBUser(String UID, int maxWaitTime) {
        this.UID = UID;
        this.maxWaitTime = maxWaitTime;
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
