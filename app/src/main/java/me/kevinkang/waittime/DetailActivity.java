package me.kevinkang.waittime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import me.kevinkang.waittime.firebase.FBRestaurant;

public class DetailActivity extends AppCompatActivity implements OnMapReadyCallback {

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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        restaurant = getIntent().getParcelableExtra("rest");
        getSupportActionBar().setTitle(restaurant.getName());

        tvDesc = (TextView) findViewById(R.id.tvDesc);
        tvPop = (TextView) findViewById(R.id.tvPop);
        tvTime = (TextView) findViewById(R.id.tvTime);
        tvRat = (TextView) findViewById(R.id.tvRati);

        et = (EditText) findViewById(R.id.editText);

        tvDesc.setText(restaurant.getDescription());
        tvPop.setText(restaurant.getPopularity() + "");
        tvTime.setText(restaurant.getTime() + "");
        tvRat.setText(restaurant.getRating() + "");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng mcDonalds = new LatLng(restaurant.getLatitude(), restaurant.getLongitude());
        googleMap.addMarker(new MarkerOptions().position(mcDonalds).title(restaurant.getName()));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mcDonalds, 19));
    }
}
