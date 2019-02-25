package sharing.ride.rideexchange;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
}
