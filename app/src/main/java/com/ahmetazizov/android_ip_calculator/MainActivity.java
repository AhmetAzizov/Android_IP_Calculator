package com.ahmetazizov.android_ip_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    Button calculateButton;
    TextInputEditText numberOfUsers;
    TextInputEditText ipAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculateButton = findViewById(R.id.calculateButton);
        numberOfUsers = findViewById(R.id.numberOfUsers);
        ipAddress = findViewById(R.id.ipAddress);


        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numberOfUsersString = numberOfUsers.getText().toString();
                String ipAddressString = ipAddress.getText().toString();

                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("numberOfUsers", numberOfUsersString);
                intent.putExtra("ipAddress", ipAddressString);
                startActivity(intent);
            }
        });

    }
}