package sharing.ride.rideexchange;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

public class DriverSetTimePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_set_time);
        TimePicker leavingTimePicker = (TimePicker) findViewById(R.id.leavingTime);
        TimePicker reachingTimePicker = (TimePicker) findViewById(R.id.reachingTime);
        leavingTimePicker.setIs24HourView(true);
        reachingTimePicker.setIs24HourView(true);
    }

    public void confirm(View view)
    {
        SharedPreferences sp = getSharedPreferences("login",MODE_PRIVATE);
        SharedPreferences reservation = getSharedPreferences("reservation",MODE_PRIVATE);
        int id = sp.getInt("id",0);
        TimePicker leavingTimePicker = (TimePicker) findViewById(R.id.leavingTime);
        reservation.edit().putInt("hour", leavingTimePicker.getHour()).apply();
        reservation.edit().putInt("mins", leavingTimePicker.getMinute()).apply();
        Log.d("tag", id+""); // Just to display in debug mode

        Intent intent = new Intent(this, DriverPickupNumPage.class);
        startActivity(intent);
    }
}
