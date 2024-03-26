package com.example.a7_practice;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private Intent musicServiceIntent;
    private Button alertDialogButton, datePickerButton, timePickerButton, customDialogButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button playButton = findViewById(R.id.playButton);
        Button stopButton = findViewById(R.id.stopButton);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMusicService();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopMusicService();
            }
        });

        alertDialogButton = findViewById(R.id.alertDialogButton);
        datePickerButton = findViewById(R.id.datePickerButton);
        timePickerButton = findViewById(R.id.timePickerButton);
        customDialogButton = findViewById(R.id.customDialogButton);


        alertDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });

        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        timePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });

        customDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog();
            }
        });


    }

    private void startMusicService() {
        musicServiceIntent = new Intent(MainActivity.this, MusicService.class);
        startService(musicServiceIntent);
    }

    private void stopMusicService() {
        if (musicServiceIntent != null) {
            stopService(musicServiceIntent);
        }
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("AlertDialog");
        builder.setMessage("This is an AlertDialog message.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                        intent.putExtra("selectedDate", selectedDate);
                        startActivity(intent);
                    }
                }, year, month, dayOfMonth);
        datePickerDialog.show();
    }

    private void showTimePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String selectedTime = hourOfDay + ":" + minute;

                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                        intent.putExtra("selectedTime", selectedTime);
                        startActivity(intent);
                    }
                }, hourOfDay, minute, true);
        timePickerDialog.show();
    }

    private void showCustomDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog);

        final EditText customEditText = dialog.findViewById(R.id.customEditText);
        Button okButton = dialog.findViewById(R.id.customDialogButton);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userInput = customEditText.getText().toString();

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                intent.putExtra("userInput", userInput);

                startActivity(intent);
                dialog.dismiss();
            }
        });

        dialog.show();
    }

}

