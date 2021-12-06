package com.example.simpleinstagram;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";
    private EditText etusername;
    private EditText etpassword;
    private Button btnLogin;
    private Button btnSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(ParseUser.getCurrentUser() != null)
        {
            goMainActivity();
        }

        etusername = findViewById(R.id.etusername);
        etpassword = findViewById(R.id.etpassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick login button");
                String username = etusername.getText().toString();
                String password = etpassword.getText().toString();
                loginUser(username, password);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick Sign Up button");
                String username = etusername.getText().toString();
                String password = etpassword.getText().toString();
                SignUpUser(username, password);
            }
        });
    }

    private void SignUpUser(String username,String password)
    {
        Log.i(TAG, "Attempting to SignUp user " + username);

        if(username.isEmpty() || password.isEmpty())
        {
            Toast.makeText(LoginActivity.this,"username or password can not be empty",Toast.LENGTH_SHORT).show();
            return;
        }
        createUser(username,password);
        goMainActivity();
        Toast.makeText(LoginActivity.this, "Success!", Toast.LENGTH_SHORT);
    }

    private void createUser(String username,String password)
    {
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e != null) {
                    Log.e(TAG,"Error while creating ",e);
                    return;
                }
                Log.i(TAG,"User save was successful");
            }
        });

    }

    private void loginUser(String username, String password) {
        Log.i(TAG, "Attempting to login user " + username);
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with login", e);
                    return;
                }
                goMainActivity();
                Toast.makeText(LoginActivity.this, "Success!", Toast.LENGTH_SHORT);
            }

        });
    }




    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

}

