package com.ahmetazizov.android_ip_calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.net.InetAddress;


public class MainActivity extends AppCompatActivity {

    Button calculateButton;
    TextInputEditText numberOfUsers;
    TextInputEditText ipAddress;
    TextInputLayout ipAddressLayout;
    static ConstraintLayout parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculateButton = findViewById(R.id.calculateButton);
        numberOfUsers = findViewById(R.id.numberOfUsers);
        ipAddress = findViewById(R.id.ipAddress);
        ipAddressLayout = findViewById(R.id.ipAddressLayout);
        parent = findViewById(R.id.parent);

        ipAddressLayout.setError(null);

        calculateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String numberOfUsersString = numberOfUsers.getText().toString();
                String ipAddressString = ipAddress.getText().toString();

                if (ipAddressLayout.getError() == null) {
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    intent.putExtra("numberOfUsers", numberOfUsersString);
                    intent.putExtra("ipAddress", ipAddressString);
                    startActivity(intent);
                } else {
                    showSnackbar(parent, ipAddress);
                }
            }
        });


        ipAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String ipAddressString = ipAddress.getText().toString();

                if (ipValidation(ipAddressString)) {
                    ipAddressLayout.setError(null);
                } else {
                    ipAddressLayout.setError("This is an invalid IP address");
                }
            }
        });
    }

        public static boolean ipValidation (String ip){
            try {
                if (ip == null || ip.isEmpty()) {
                    return false;
                }

                String[] parts = ip.split("\\.");
                if (parts.length != 4) {
                    return false;
                }

                for (String s : parts) {
                    int i = Integer.parseInt(s);
                    if ((i < 0) || (i > 255)) {
                        return false;
                    }

                    if (s.length() > 3)
                        return false;
                }
                if (ip.endsWith(".")) {
                    return false;
                }

                return true;
            } catch (NumberFormatException nfe) {
                return false;
            }
        }

        public static void showSnackbar (ConstraintLayout parent, TextInputEditText editText) {
            final Snackbar mySnackbar = Snackbar.make(parent, "Entered IP Address is invalid", Snackbar.LENGTH_INDEFINITE);


            mySnackbar.setAction("Retry", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editText.setText(null);
                }
            });

            mySnackbar.show();
        }
}