package com.example.mpr.tutorials.tut2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mpr.R;

public class Tut2_3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tut2_3);

        EditText usd = findViewById(R.id.usd);
        ImageView img = findViewById(R.id.imageView13);
        Button btnOk = findViewById(R.id.convert);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Tut2_3.this,
                        "Result: " + Double.parseDouble(usd.getText().toString()) * 23609 + " VND",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}