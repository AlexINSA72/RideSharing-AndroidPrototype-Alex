package sharing.ride.rideexchange;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DriverSignUpPage extends AppCompatActivity {
    public EditText name;
    public EditText mail;
    public EditText passwo;
    public EditText confirmPassView;
    public EditText phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_sign_up_page);
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
        myDB.execSQL("CREATE TABLE IF NOT EXISTS Profile(Name TEXT, Password TEXT, MailAddress TEXT, phone TEXT, idProfile INT);");
        myDB.execSQL("CREATE TABLE IF NOT EXISTS ListPass(idProfile INT, idTravel INT, idList INT);");
        myDB.execSQL("CREATE TABLE IF NOT EXISTS Travel(Departure TEXT, Destination TEXT, Day INT, Month INT, Year INT, Hour INT, Mins INT , idProfileDriver INT, nbPassMax INT, idListPass INT);");
        int nbProfile =  myDB.rawQuery("Select * from Profile",null).getCount();
        myDB.execSQL("INSERT INTO Profile VALUES('"+name.getText().toString()+"','"+passwo.getText().toString()+"','"+mail.getText().toString()+"','"+phone.getText().toString()+"','"+(nbProfile+1)+"');");
        startActivity(intent);
    }
}
