package me.kevinkang.waittime.model;

/**
 * Created by kevink97 on 1/14/17.
 */

public interface Restaurant {

    /**
     * gets name of Restaurant
     * @return name of restaurants
     */
    String getName();

    /**
     * Sets popularity of Restaurant.
     * Popularity is set by the amount of check in.
     * @param newPopularity popularity of restaurant
     */
    void setPopularity(int newPopularity);

    /**
     * gets the popularity of Restaurant
     * @return popularity of Restaurant
     */
    int getPopularity();

    /**
     * sets the description of Restaurant
     * @param newDescription new description for restaurant
     */
    void setDescription(String newDescription);

    /**
     * gets the description of Restaurant
     * @return description of restaurant
     */
    String getDescription();

    /**
     * sets Rating of the Restaurant
     * @param newRating new rating for restaurant
     */
    void setRating(double newRating);

    /**
     * gets Rating for restuarant
     * @return rating for restaurant
     */
    double getRating();

    void setTime(int newTime);

    int getTime();

}
