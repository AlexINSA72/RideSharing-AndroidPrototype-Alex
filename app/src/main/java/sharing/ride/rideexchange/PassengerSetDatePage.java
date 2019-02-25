package sharing.ride.rideexchange;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
        SharedPreferences sp = getSharedPreferences("login",MODE_PRIVATE);
        SharedPreferences reservation = getSharedPreferences("reservation",MODE_PRIVATE);
        int id = sp.getInt("id",0);
        DatePicker simpleDatePicker = (DatePicker) findViewById(R.id.datePicker);
        reservation.edit().putInt("day", simpleDatePicker.getDayOfMonth()).apply();
        reservation.edit().putInt("month", simpleDatePicker.getMonth()).apply();
        reservation.edit().putInt("year", simpleDatePicker.getYear()).apply();
        Log.d("tag", id+""); // Just to display in debug mode

        Intent intent = new Intent(this, PassengerSetTimePage.class);
        startActivity(intent);
    }
}
