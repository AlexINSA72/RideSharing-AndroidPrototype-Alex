package sharing.ride.rideexchange;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.facebook.FacebookSdk;
import com.facebook.FacebookException;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.LoginResult;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.AccessToken;
import com.facebook.Profile;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private static final String TAG = "MainActivity.class";


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_main);
        loginButton = (LoginButton)findViewById(R.id.login_button);

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                AccessToken accessToken = loginResult.getAccessToken();
                Profile profile = Profile.getCurrentProfile();

                Intent intent = new Intent(MainActivity.this, PassengerSetDatePage.class);
                startActivity(intent);

                Toast.makeText(getBaseContext(), "Facebook login was successful", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getBaseContext(), "Facebook login was canceled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException e) {
                Toast.makeText(getBaseContext(), "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        setContentView(R.layout.activity_main);
    }

    public void logIn(View view)
    {
        Intent intent = new Intent(this, LoginPage.class);
        SQLiteDatabase myDB = openOrCreateDatabase("rideShareDB",MODE_PRIVATE,null);
        Cursor resultSet = myDB.rawQuery("Select * from Profile",null);
        resultSet.moveToFirst();
        String name;
        String password;
        String mail;
        String id;
        String phone;
        for (int i = 0; i < resultSet.getCount(); i++) {
            name = resultSet.getString(0);
            password = resultSet.getString(1);
            mail = resultSet.getString(2);
            phone = resultSet.getString(3);
            id = resultSet.getString(4);
            Log.d("tag", name+password+mail+phone+id); // Just to display in debug mode
            resultSet.moveToNext();
        }
        startActivity(intent);
    }

    public void signUp(View view)
    {
        Intent intent = new Intent(this, ChooseRoleSignUpPage.class);
        startActivity(intent);
    }

}
