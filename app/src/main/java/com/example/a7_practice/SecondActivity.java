package com.example.a7_practice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        String userInput = intent.getStringExtra("userInput");
        String selectedDate = intent.getStringExtra("selectedDate");
        String selectedTime = intent.getStringExtra("selectedTime");


        TextView textView = findViewById(R.id.SecondView);


        StringBuilder stringBuilder = new StringBuilder();
        if (userInput != null) {
            stringBuilder.append("User Input: ").append(userInput).append("\n");
        }
        if (selectedDate != null) {
            stringBuilder.append("Selected Date: ").append(selectedDate).append("\n");
        }
        if (selectedTime != null) {
            stringBuilder.append("Selected Time: ").append(selectedTime).append("\n");
        }

        textView.setText(stringBuilder.toString());
    }


    public void backToMenu(View view){
        finish();
    }
}
