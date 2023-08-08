package com.example.mpr.tutorials.tut2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mpr.R;

public class Tut2_2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tut2_2);


        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        Button btnOk = findViewById(R.id.login);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
                    Toast.makeText(Tut2_2.this, "Welcome back!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Tut2_2.this, "Incorrect username or password!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

//    public void onBtnClick(View view) {
//        Log.i("Info","Clicked!");
//        Toast.makeText(this,"Hi there!",Toast.LENGTH_LONG).show();
//    }
}
