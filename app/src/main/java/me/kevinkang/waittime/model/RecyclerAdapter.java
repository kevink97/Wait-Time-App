package me.kevinkang.waittime.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

import me.kevinkang.waittime.R;
import me.kevinkang.waittime.firebase.FBRestaurant;

/**
 * Created by Sam on 1/14/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
    private Context mContext;
    private DatabaseReference db;
    private ChildEventListener mChildEventListener;

    private List<String> restaurantIds = new ArrayList<>();
    private List<FBRestaurant> restaurants = new ArrayList<>();

    public RecyclerAdapter(final Context context, DatabaseReference ref) {
        mContext = context;
        db = ref;

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                FBRestaurant restaurant  = dataSnapshot.getValue(FBRestaurant.class);
                restaurantIds.add(dataSnapshot.getKey());
                restaurants.add(restaurant);
                notifyItemInserted(restaurants.size() - 1);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                FBRestaurant restaurant = dataSnapshot.getValue(FBRestaurant.class);
                String rKey = dataSnapshot.getKey();

                int rIndex = restaurantIds.indexOf(rKey);
                if (rIndex > -1) {
                    restaurants.set(rIndex, restaurant);
                    notifyItemChanged(rIndex);
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        ref.addChildEventListener(childEventListener);

        mChildEventListener = childEventListener;
    }
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_layout,parent,false);

        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        FBRestaurant restaurant = restaurants.get(position);
        holder.name.setText(restaurant.getName());
        holder.time.setText(restaurant.getTime() + "");
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView time;

        public RecyclerViewHolder(View view) {
            super(view);
            name = (TextView)view.findViewById(R.id.tx_restaurant_name);
            time = (TextView)view.findViewById(R.id.tx_restaurant_time);

        }
    }
}
