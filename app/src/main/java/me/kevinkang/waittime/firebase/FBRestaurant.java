package me.kevinkang.waittime.firebase;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import me.kevinkang.waittime.model.Restaurant;

/**
 * Created by kevink97 on 1/14/17.
 */

public class FBRestaurant implements Restaurant, Parcelable {
    private String UID;
    private int popularity;
    private String description;
    private double rating;
    private String name;
    private int time; // in minutes

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(UID);
        dest.writeInt(popularity);
        dest.writeString(description);
        dest.writeDouble(rating);
        dest.writeString(name);
        dest.writeInt(time);
    }

    private FBRestaurant(Parcel in) {
        UID = in.readString();
        popularity = in.readInt();
        description = in.readString();
        rating = in.readDouble();
        name = in.readString();
        time = in.readInt();
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<FBRestaurant> CREATOR = new Parcelable.Creator<FBRestaurant>() {
        public FBRestaurant createFromParcel(Parcel in) {
            return new FBRestaurant(in);
        }

        public FBRestaurant[] newArray(int size) {
            return new FBRestaurant[size];
        }
    };

    private DatabaseReference db;

    public FBRestaurant() {}

    /**
     * Sets FBRestaurant with its unique ID From firebase, and the name of the restaurant.
     *
     * @param name name of restaurant. Cannot be empty
     */
    public FBRestaurant(String name) {
        db = FirebaseDatabase.getInstance().getReference();
        this.UID = db.child("restaurants").push().getKey();
        this.popularity = 0;
        this.description = "";
        this.rating = -1;
        this.name = name;
        this.time = 0;

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/restaurants/" + UID, toMap());
        db.updateChildren(childUpdates);
    }

    @Exclude
    private Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("description", description);
        result.put("popularity", popularity);
        result.put("rating", rating);
        result.put("time", time);
        return result;
    }

    /**
     * gets name of Restaurant
     *
     * @return name of restaurants
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Sets popularity of Restaurant.
     * Popularity is set by the amount of check in.
     *
     * @param newPopularity popularity of restaurant
     */
    @Override
    public void setPopularity(int newPopularity) {
        this.popularity = newPopularity;
    }

    /**
     * gets the popularity of Restaurant
     *
     * @return popularity of Restaurant
     */
    @Override
    public int getPopularity() {
        return this.popularity;
    }

    /**
     * sets the description of Restaurant
     *
     * @param newDescription new description for restaurant
     */
    @Override
    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    /**
     * gets the description of Restaurant
     *
     * @return description of restaurant
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * sets Rating of the Restaurant
     *
     * @param newRating new rating for restaurant
     */
    @Override
    public void setRating(double newRating) {
        this.rating = newRating;
    }

    /**
     * gets Rating for restuarant
     *
     * @return rating for restaurant
     */
    @Override
    public double getRating() {
        return this.rating;
    }

    @Override
    public void setTime(int newTime) {
        // TODO: avg out time to remove trolls kek
        time = newTime;
    }

    @Override
    public int getTime() {
        return time;
    }

    @Override
    public int describeContents() {
        return 0;
    }


}
