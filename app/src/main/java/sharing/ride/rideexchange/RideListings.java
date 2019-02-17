package sharing.ride.rideexchange;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class RideListings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_listings);

        RecyclerView rides = (RecyclerView)findViewById(R.id.rideListings);
        String[] rideListings = {"One", "Two", "Three", "Four", "Five", "Six"};

        MyAdapter adapter = new MyAdapter(rideListings);

        rides.setAdapter(adapter);

        rides.setLayoutManager(new LinearLayoutManager(this));
    }
}
