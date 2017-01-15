package me.kevinkang.waittime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import me.kevinkang.waittime.firebase.FBRestaurant;

public class DetailActivity extends AppCompatActivity {

    private TextView tvName;
    private TextView tvDesc;
    private TextView tvTime;
    private TextView tvPop;
    private TextView tvRat;

    private FBRestaurant restaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        restaurant = getIntent().getParcelableExtra("rest");

        tvName = (TextView) findViewById(R.id.tvName);
        tvDesc = (TextView) findViewById(R.id.tvDesc);
        tvPop = (TextView) findViewById(R.id.tvPop);
        tvTime = (TextView) findViewById(R.id.tvTime);
        tvRat = (TextView) findViewById(R.id.tvRati);

        tvName.setText(restaurant.getName());
        tvDesc.setText(restaurant.getDescription());
        tvPop.setText(restaurant.getPopularity());
        tvTime.setText(restaurant.getTime());
        tvRat.setText(restaurant.getRating() + "");
    }
}
