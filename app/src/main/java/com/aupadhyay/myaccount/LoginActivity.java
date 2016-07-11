package com.aupadhyay.myaccount;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nameEditText, passwordEditText;
    private static final String TRUE = "true";
    private SharedPreferences sharedPreferences;
    private Button loginButton, resetButton;

    public void initViews()
    {
        loginButton = (Button) findViewById(R.id.loginButton);
        resetButton = (Button) findViewById(R.id.resetButton);
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);

        loginButton.setOnClickListener(this);
        resetButton.setOnClickListener(this);
    }

    public void startWelcomeActivity()
    {
        String name = nameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if(name.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin"))
        {
            sharedPreferences.edit().putString(TRUE,"true").apply();
            Intent intent = new Intent(this, WelcomeActivity.class);
            startActivity(intent);
        }
    }

    public void resetLoginActivity()
    {
        nameEditText.setText("");
        passwordEditText.setText("");
        nameEditText.requestFocus();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences("login_info", Context.MODE_PRIVATE);

        String check = sharedPreferences.getString(TRUE,"");
        if(check.equals("true"))
        {
            // Move to other activity
            Intent intent = new Intent(this, WelcomeActivity.class);
            startActivity(intent);
        }
        initViews();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.loginButton:
                startWelcomeActivity();
                break;
            case R.id.resetButton:
                resetLoginActivity();
                break;
        }
    }
}
