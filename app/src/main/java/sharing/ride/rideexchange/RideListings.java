package sharing.ride.rideexchange;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class RideListings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_listings);

        RecyclerView rides = (RecyclerView)findViewById(R.id.rideListings);
        rides.addItemDecoration(new DividerItemDecoration(rides.getContext(), DividerItemDecoration.VERTICAL));
        String[] rideListings = {"Los Angeles -> San Francisco", "Santa Monica -> San Francisco",
                "Westwood -> San Francisco", "Los Angeles -> San Jose", "Los Angeles -> Oakland"};

        MyAdapter adapter = new MyAdapter(rideListings);

        rides.setAdapter(adapter);

        rides.setLayoutManager(new LinearLayoutManager(this));
    }
}
