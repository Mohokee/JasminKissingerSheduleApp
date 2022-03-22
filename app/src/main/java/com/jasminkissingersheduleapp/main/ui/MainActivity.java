package com.jasminkissingersheduleapp.main.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jasminkissingersheduleapp.R;
import com.jasminkissingersheduleapp.main.database.Repository;
import com.jasminkissingersheduleapp.main.entities.Assessment;
import com.jasminkissingersheduleapp.main.entities.Course;
import com.jasminkissingersheduleapp.main.entities.Term;
import com.jasminkissingersheduleapp.main.ui.termList;

public class MainActivity extends AppCompatActivity {
    public static int numAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Repository repo=new Repository(getApplication());
        Term term= new Term(2,"Spring 2022", "03/08/22","05/12/22");
        Term termbo= new Term(3,"Fall 2022", "06/1/22","11/12/22");
        repo.insert(term);
        repo.insert(termbo);
        Course course = new Course(2,"Basket Weaving,Underwater","03/06/22","03/19/22","In Progress","sss","ss","ss","May be taken after y165",2);
        repo.insert(course);
        Assessment assess=new Assessment(1,"Weaving","01/01/22","05/05/22",2,"Objective");
        repo.insert(assess);
    }
    //Go to main menu
    public void viewMenu(View view) {
        Intent intent = new Intent(MainActivity.this, mainMenu.class);
        startActivity(intent);
    }


}