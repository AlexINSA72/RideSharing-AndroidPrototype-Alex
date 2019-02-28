package sharing.ride.rideexchange;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TimePicker;

public class RideListings extends AppCompatActivity {

    public String selectedDep;
    public String selectedDes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_listings);

        RecyclerView rides = (RecyclerView)findViewById(R.id.rideListings);
        rides.addItemDecoration(new DividerItemDecoration(rides.getContext(), DividerItemDecoration.VERTICAL));
        String[] rideListings = {"Los Angeles", "Santa Monica",
                "Westwood", "San Jose", "Oakland", "San Francisco"};

        MyAdapter adapter = new MyAdapter(rideListings, this, false);

        rides.setAdapter(adapter);

        rides.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView rides2 = (RecyclerView)findViewById(R.id.rideListings2);
        rides.addItemDecoration(new DividerItemDecoration(rides.getContext(), DividerItemDecoration.VERTICAL));
        String[] rideListings2 = {"Los Angeles", "Santa Monica",
                "Westwood", "San Jose", "Oakland", "San Francisco"};

        MyAdapter adapter2 = new MyAdapter(rideListings2, this, true);

        rides2.setAdapter(adapter2);

        rides2.setLayoutManager(new LinearLayoutManager(this));
    }

    public void confirm(View view)
    {
        SharedPreferences sp = getSharedPreferences("login",MODE_PRIVATE);
        SharedPreferences reservation = getSharedPreferences("reservation",MODE_PRIVATE);
        int id = sp.getInt("id",0);
        int driver = sp.getInt("driver", 0);

        // TODO: NEED TO FIND THE REAL PLACE SELECTED IN THE LIST

        reservation.edit().putString("departure", selectedDep).apply();
        reservation.edit().putString("destination", selectedDes).apply();

        if(driver == 1)
        {
            Intent intent = new Intent(this, DriverSetDatePage.class);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(this, PassengerSetDatePage.class);
            startActivity(intent);
        }
    }
}
