package me.kevinkang.waittime.model;

/**
 * Created by kevink97 on 1/14/17.
 */

public interface User {

    /**
     * gets the UID of user
     * @return UID of user
     */
    String getUID();

    /**
     * sets the max wait time the user wants
     * @param newMaxWaitTime the new max wait time
     */
    void setMaxWaitTime(int newMaxWaitTime);

    /**
     * gets the max wait time the user wants
     * @return the max wait time
     */
    int maxWaitTime();

}
