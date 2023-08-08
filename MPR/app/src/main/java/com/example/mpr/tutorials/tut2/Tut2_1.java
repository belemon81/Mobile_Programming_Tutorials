package com.example.mpr.tutorials.tut2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mpr.R;

public class Tut2_1 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tut2_1);

        EditText input = findViewById(R.id.input);
        Button btnOk = findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Tut2_1.this, "Hello " + input.getText().toString() + "!", Toast.LENGTH_LONG).show();
//                btnOk.setText("Clicked!");
            }
        });

    }

//    public void onBtnClick(View view) {
//        Log.i("Info","Clicked!");
//        Toast.makeText(this,"Hi there!",Toast.LENGTH_LONG).show();
//    }
}
