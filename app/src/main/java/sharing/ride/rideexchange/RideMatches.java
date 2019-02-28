package sharing.ride.rideexchange;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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
//        LatLng sanFrancisco = new LatLng(37.773972, -122.431297);
//        LatLng losAngeles = new LatLng(34.052235, -118.243683);
//        LatLng sanLuisObispo = new LatLng(35.616348, -119.694298);
//        mMap.addMarker(new MarkerOptions().position(sanFrancisco).title("San Francisco"));
//        mMap.addMarker(new MarkerOptions().position(losAngeles).title("Los Angeles"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sanLuisObispo, 6.0f));

        setRideListing();
    }

    public void setRideListing()
    {
        RecyclerView rides = (RecyclerView)findViewById(R.id.rideMatches);
        rides.addItemDecoration(new DividerItemDecoration(rides.getContext(), DividerItemDecoration.VERTICAL));

        /* DATABASE STUFF */

        SharedPreferences sp = getSharedPreferences("login",MODE_PRIVATE);
        SharedPreferences reservation = getSharedPreferences("reservation",MODE_PRIVATE);
        int id = sp.getInt("id",0);
        String departure = reservation.getString("departure","");
        String destination = reservation.getString("destination","");
        int day = reservation.getInt("day",0);
        int month = reservation.getInt("month",0);
        int year = reservation.getInt("year",0);
        int hour = reservation.getInt("hour",0);
        int mins = reservation.getInt("mins",0);
        int nbPassMax = reservation.getInt("num",0);
        //myDB.execSQL("CREATE TABLE IF NOT EXISTS Travel(Departure TEXT, Destination TEXT, Day INT, Month INT, Year INT, Hour INT, Mins INT , idProfileDriver INT, nbPassMax INT, idListPass INT, idTravel INT);");

        SQLiteDatabase myDB = openOrCreateDatabase("rideShareDB",MODE_PRIVATE,null);
        Cursor resultSet =  myDB.rawQuery("Select * from Travel where Departure = \""+departure+"\" and Destination = \""+destination+"\" and Year = \""+year+"\" and Month = \""+month+"\" and Day = \""+day+"\" and Hour >= \""+hour+"\"",null);
        resultSet.moveToFirst();
        Ride[] rideListings = new Ride[resultSet.getCount()];

        String dep;
        String des;
        String dayy;
        String monthh;
        String yearr;
        String hourr;
        String minss;
        String name;
        int idDriver;
        int nbPass;
        int realNbPass;
        int idList;
        int idTravel;
        String phoneNumber;
        for (int i = 0; i < resultSet.getCount(); i++) {
            dep = resultSet.getString(0);
            des = resultSet.getString(1);
            dayy = resultSet.getString(2);
            monthh = resultSet.getString(3);
            yearr = resultSet.getString(4);
            hourr = resultSet.getString(5);
            minss = resultSet.getString(6);
            idDriver = Integer.parseInt(resultSet.getString(7));
            nbPass = Integer.parseInt(resultSet.getString(8));
            idList = Integer.parseInt(resultSet.getString(9));
            idTravel = Integer.parseInt(resultSet.getString(10));

            Cursor driverDB = myDB.rawQuery("Select * from Profile where idProfile = \""+ idDriver +"\"",null);
            driverDB.moveToFirst();
            name = driverDB.getString(1);
            phoneNumber = driverDB.getString(4);

            realNbPass = nbPass - myDB.rawQuery("Select * from ListPass where idTravel = \"" + idTravel + "\"",null).getCount() + 1;

            //rideListings[i] = dep + " to " + des + " " + dayy + "/" + monthh + "/" + yearr + " at " + hourr + ":" + minss + "  " + realNbPass + "/" + nbPass + " places remaining with " + name;
            rideListings[i] = new Ride(dep, des, dayy, monthh, yearr, hourr, minss, name, idDriver, nbPass, realNbPass, idList, idTravel, phoneNumber);

            resultSet.moveToNext();
        }

//        Ride[] rideTest = new Ride[4];
//        rideTest[0] = new Ride("Los Angeles", "Oakland", "3",
//                "3", "3333", "3", "33", "Brad",
//                3, 3, 3, 3, 3);
//        rideTest[1] = new Ride("San Francisco", "San Jose", "3",
//                "3", "3333", "3", "33", "Brad",
//                3, 3, 3, 3, 3);
//        rideTest[2] = new Ride("Santa Monica", "San Jose", "3",
//                "3", "3333", "3", "33", "Brad",
//                3, 3, 3, 3, 3);
//        rideTest[3] = new Ride("Westwood", "San Francisco", "3",
//                "3", "3333", "3", "33", "Brad",
//                3, 3, 3, 3, 3);
        RideMatchAdapter adapter = new RideMatchAdapter(rideListings, mMap);
        //RideMatchAdapter adapter = new RideMatchAdapter(rideTest, mMap);

        rides.setAdapter(adapter);

        rides.setLayoutManager(new LinearLayoutManager(this));
    }
}