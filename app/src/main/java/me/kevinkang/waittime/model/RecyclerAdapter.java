package me.kevinkang.waittime.model;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.kevinkang.waittime.R;

/**
 * Created by Sam on 1/14/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
    String[] restaurant_names;

    public  RecyclerAdapter(String[] restaurant_names){
        this.restaurant_names = restaurant_names;
    }
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout,parent,false);

        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.Tx_Restaurant.setText(restaurant_names[position]);
    }

    @Override
    public int getItemCount() {
        return restaurant_names.length;
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView Tx_Restaurant;

        public RecyclerViewHolder(View view) {
            super(view);
            Tx_Restaurant = (TextView)view.findViewById(R.id.tx_restaurant_name);

        }
    }
}
