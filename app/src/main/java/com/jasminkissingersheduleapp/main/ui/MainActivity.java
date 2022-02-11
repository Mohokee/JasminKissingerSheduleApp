package com.jasminkissingersheduleapp.main.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jasminkissingersheduleapp.R;
import com.jasminkissingersheduleapp.main.ui.termList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //TERM
    //view
    public void viewTerm(View view) {
        Intent intent = new Intent(MainActivity.this, termList.class);
        startActivity(intent);
    }

    //CLASS
    //view
    public void viewClass(View view) {
        Intent intent = new Intent(MainActivity.this, classList.class);
        startActivity(intent);
    }

    //ASSESSMENT
    //view
    public void viewAssessment(View view) {
        Intent intent = new Intent(MainActivity.this, assessmentList.class);
        startActivity(intent);
    }


}