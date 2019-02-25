package sharing.ride.rideexchange;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

public class PassengerSetDatePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_set_date);
        DatePicker simpleDatePicker = (DatePicker) findViewById(R.id.datePicker); // initiate a date picker
        simpleDatePicker.setMinDate(System.currentTimeMillis() - 1000);//disable past days
        int day = simpleDatePicker.getDayOfMonth(); // get the selected day of the month
        int month = simpleDatePicker.getMonth();
        int year = simpleDatePicker.getYear();
    }

    public void confirm(View view)
    {
        Intent intent = new Intent(this, PassengerSetTimePage.class);
        startActivity(intent);
    }
}
