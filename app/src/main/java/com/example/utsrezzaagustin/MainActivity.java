package com.example.utsrezzaagustin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.utsrezzaagustin.biodata.BiodataActivity;

public class MainActivity extends AppCompatActivity {
    Button btnLogin;
    TextView textRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btn_login);
        textRegister = findViewById(R.id.pilihregister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(MainActivity.this, BiodataActivity.class);
                startActivity(login);
            }
        });

        textRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(register);
            }
        });
    }
}