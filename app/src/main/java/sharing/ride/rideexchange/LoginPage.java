package sharing.ride.rideexchange;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;


public class LoginPage extends AppCompatActivity {

    public EditText mail;
    public EditText passwo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mail = (EditText) findViewById(R.id.email);
        passwo = (EditText) findViewById(R.id.password);
    }

    public void tryConnect(View view)
    {
        SQLiteDatabase myDB = openOrCreateDatabase("rideShareDB",MODE_PRIVATE,null);
        int test =  myDB.rawQuery("Select * from Profile where MailAddress = \""+mail.getText().toString()+"\" and Password = \""+ passwo.getText().toString()+"\"",null).getCount();
        if(test == 1)
        {
            Log.d("tag", "LOGGED"); // Just to display in debug mode

            Cursor resultSet = myDB.rawQuery("Select * from Profile where MailAddress = \""+mail.getText().toString()+"\" and Password = \""+ passwo.getText().toString()+"\"",null);
            resultSet.moveToFirst();
            SharedPreferences sp = getSharedPreferences("login",MODE_PRIVATE);
            sp.edit().putInt("id", Integer.parseInt(resultSet.getString(5))).apply();
            if(resultSet.getString(0).equals("1") == true)
            {
                sp.edit().putInt("driver", 1).apply();
                Intent intent = new Intent(this, RideListings.class);
                startActivity(intent);
            }
            else
            {
                sp.edit().putInt("driver", 0).apply();
                Intent intent = new Intent(this, RideListings.class);
                startActivity(intent);
            }
        }
    }
}

