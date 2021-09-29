package com.example.room;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateActivity extends AppCompatActivity {

    public static final String DESTINATION_ADDED = "destination_added";
    public static final String COUNTRY_ADDED = "country_added";

    private EditText etDestination;
    private EditText etCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        etDestination = findViewById(R.id.etDestination);
        etCountry = findViewById(R.id.etCountry);

        Button button = findViewById(R.id.bAdd);
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                Intent resultIntent = new Intent();

                if (TextUtils.isEmpty(etDestination.getText()) || TextUtils.isEmpty(etCountry.getText()))
                {
                    setResult(RESULT_CANCELED, resultIntent);
                }
                else
                {
                    String destination = etDestination.getText().toString();
                    resultIntent.putExtra(DESTINATION_ADDED, destination);
                    String country = etCountry.getText().toString();
                    resultIntent.putExtra(COUNTRY_ADDED, country);
                    setResult(RESULT_OK, resultIntent);
                }

                finish();
            }
        });
    }
}