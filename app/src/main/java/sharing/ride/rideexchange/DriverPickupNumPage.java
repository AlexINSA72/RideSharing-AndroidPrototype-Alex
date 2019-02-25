package sharing.ride.rideexchange;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class DriverPickupNumPage extends AppCompatActivity {
    int minteger = 2;
    FloatingActionButton increaseBtn;
    FloatingActionButton decreaseBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_pickup);
        increaseBtn = (FloatingActionButton) findViewById(R.id.plusButton);
        decreaseBtn = (FloatingActionButton) findViewById(R.id.minusButton);
    }

    public void increaseNum(View view){

        minteger = minteger + 1;
        display(minteger);
        if (minteger==3){
            increaseBtn.setEnabled(false);
        }
    }

    public void decreaseNum(View view){;

        minteger = minteger - 1;
        display(minteger);
        if (minteger==1){
            decreaseBtn.setEnabled(false);
        }
    }

    private void display(int number) {
        TextView displayInteger = (TextView) findViewById(R.id.pickupNum);
        displayInteger.setText("" + number);
        if (minteger<=3){
            increaseBtn.setEnabled(true);
        }
        if (minteger>=1){
            decreaseBtn.setEnabled(true);
        }
    }

    public void confirm(View view)
    {
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
        int nbPassMax = minteger;

        SQLiteDatabase myDB = openOrCreateDatabase("rideShareDB",MODE_PRIVATE,null);
        int nbList =  myDB.rawQuery("Select * from ListPass",null).getCount();
        int nbTravel =  myDB.rawQuery("Select * from Travel",null).getCount();

        myDB.execSQL("INSERT INTO ListPass VALUES('"+id+"', '"+(nbTravel+1)+"', '"+(nbList+1)+"');");

        myDB.execSQL("INSERT INTO Travel VALUES('"+departure+"', '"+destination+"', '"+day+"', '"+month+"', '"+year+"', '"+hour+"', '"+mins+"', '"+id+"', '"+nbPassMax+"', '"+(nbList+1)+"', '"+(nbTravel+1)+"');");

        Log.d("tag","INSERT INTO Travel VALUES('"+departure+"', '"+destination+"', '"+day+"', '"+month+"', '"+year+"', '"+hour+"', '"+mins+"', '"+id+"', '"+nbPassMax+"', '"+(nbList+1)+"', '"+(nbTravel+1)+"');");

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
