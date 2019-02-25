package sharing.ride.rideexchange;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
        Intent intent = new Intent(this, DriverPickupNumPage.class);
        startActivity(intent);
    }
}
