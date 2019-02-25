package sharing.ride.rideexchange;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ChooseRoleSignUpPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_role_sign_up_page);
    }

    public void passengerSignUp(View view)
    {
        Intent intent = new Intent(this, PassengerSignUpPage.class);
        startActivity(intent);
    }

    public void driverSignUp(View view)
    {
        Intent intent = new Intent(this, DriverSignUpPage.class);
        startActivity(intent);
    }
}
