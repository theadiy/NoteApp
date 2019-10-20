package org.terna.noteapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText username;
    EditText password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.usernameEditText);
        password = findViewById(R.id.passwordEditText);
        username.setText("");
        password.setText("");
        login = findViewById(R.id.loginButton);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String user = username.getText().toString();
        String pass = password.getText().toString();

        if(user.compareTo("admin")==0 && pass.compareTo("admin")==0){
            Intent newActivity = new Intent(this,MainActivity.class);
            startActivity(newActivity);
            Toast.makeText(getApplicationContext(),"Logged in ",Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(getApplicationContext(),"Incorrect username or password ",Toast.LENGTH_SHORT).show();
        }
    }
}
