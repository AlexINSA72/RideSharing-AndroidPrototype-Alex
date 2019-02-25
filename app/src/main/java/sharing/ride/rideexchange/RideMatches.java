package sharing.ride.rideexchange;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class RideMatches extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_pickup);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        RecyclerView rides = (RecyclerView)findViewById(R.id.rideMatches);
        rides.addItemDecoration(new DividerItemDecoration(rides.getContext(), DividerItemDecoration.VERTICAL));
        String[] rideListings = {"Los Angeles to San Francisco", "Santa Monica to San Francisco",
                "Westwood to San Francisco", "Los Angeles to San Jose", "Los Angeles to Oakland"};

        RideMatchAdapter adapter = new RideMatchAdapter(rideListings);

        rides.setAdapter(adapter);

        rides.setLayoutManager(new LinearLayoutManager(this));
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sanFrancisco = new LatLng(37.773972, -122.431297);
        LatLng losAngeles = new LatLng(34.052235, -118.243683);
        LatLng sanLuisObispo = new LatLng(35.616348, -119.694298);
        mMap.addMarker(new MarkerOptions().position(sanFrancisco).title("San Francisco"));
        mMap.addMarker(new MarkerOptions().position(losAngeles).title("Los Angeles"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sanLuisObispo, 6.0f));
    }
}