package com.example.msparaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loginByUser(View view)
    {
        EditText usernameEditText = findViewById(R.id.editTextUsername);
        String usernameText = usernameEditText.getText().toString();
        Intent intent;

        switch (usernameText)
        {
            case "1":
                intent = new Intent(this, ClientActivity.class);
                break;
            case "2":
                intent = new Intent(this, EmployeeActivity.class);
                break;
            default :
                intent = new Intent(this, AdminActivity.class);
                break;
        }
        startActivity(intent);
    }
}