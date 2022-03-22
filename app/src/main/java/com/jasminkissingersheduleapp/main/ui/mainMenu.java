package com.jasminkissingersheduleapp.main.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jasminkissingersheduleapp.R;

public class mainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    //TERM
    //view
    public void viewTerm(View view) {
        Intent intent = new Intent(mainMenu.this, termList.class);
        startActivity(intent);
    }

    //CLASS
    //view
    public void viewClass(View view) {
        Intent intent = new Intent(mainMenu.this, classList.class);
        startActivity(intent);
    }

    //ASSESSMENT
    //view
    public void viewAssessment(View view) {
        Intent intent = new Intent(mainMenu.this, assessmentList.class);
        startActivity(intent);
    }

    public void goSearch(View view) {
        Intent intent = new Intent(mainMenu.this, search.class);
        startActivity(intent);
    }
}