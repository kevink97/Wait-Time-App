package me.kevinkang.waittime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import me.kevinkang.waittime.firebase.FBRestaurant;

public class DetailActivity extends AppCompatActivity {

    private TextView tvName;
    private TextView tvDesc;
    private TextView tvTime;
    private TextView tvPop;
    private TextView tvRat;

    private EditText et;

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

        et = (EditText) findViewById(R.id.editText);

        tvName.setText(restaurant.getName());
        tvDesc.setText(restaurant.getDescription());
        tvPop.setText(restaurant.getPopularity() + "");
        tvTime.setText(restaurant.getTime() + "");
        tvRat.setText(restaurant.getRating() + "");
    }

    public void onCheckIn(View view) {
        if (!et.getText().toString().equals("")) {

            int time = Integer.parseInt(et.getText().toString());
            DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("restaurants");
            db = db.child(restaurant.UID).child("time");
            db.setValue(time);
            startActivity(new Intent(DetailActivity.this, MainActivity.class));
        }

    }
}
