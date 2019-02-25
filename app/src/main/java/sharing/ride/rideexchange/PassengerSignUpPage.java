package sharing.ride.rideexchange;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * A login screen that offers login via email/password.
 */
public class PassengerSignUpPage extends AppCompatActivity{
    public EditText name;
    public EditText mail;
    public EditText passwo;
    public EditText confirmPassView;
    public EditText phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_sign_up);
        name = (EditText) findViewById(R.id.name);
        mail = (EditText) findViewById(R.id.email);
        passwo = (EditText) findViewById(R.id.password);
        confirmPassView = (EditText) findViewById(R.id.passwordConfirm);
        phone = (EditText) findViewById(R.id.phone);
    }

    public void putDB(View view)
    {
        Intent intent = new Intent(this, LoginPage.class);
        SQLiteDatabase myDB = openOrCreateDatabase("rideShareDB",MODE_PRIVATE,null);
        int nbProfile =  myDB.rawQuery("Select * from Profile",null).getCount();
        myDB.execSQL("INSERT INTO Profile VALUES(0, '"+name.getText().toString()+"','"+passwo.getText().toString()+"','"+mail.getText().toString()+"','"+phone.getText().toString()+"','"+(nbProfile+1)+"');");
        startActivity(intent);
    }
}

