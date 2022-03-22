package com.jasminkissingersheduleapp.main.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.jasminkissingersheduleapp.R;
import com.jasminkissingersheduleapp.main.dao.courseCRUD;
import com.jasminkissingersheduleapp.main.database.Repository;
import com.jasminkissingersheduleapp.main.entities.Course;
import com.jasminkissingersheduleapp.main.entities.Term;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class classAdd extends AppCompatActivity {
    Repository repository;

    EditText title;
    EditText start;
    EditText end;
    EditText name;
    EditText phone;
    EditText email;
    EditText note;

    Spinner spinner;
    Spinner spinnerStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_add);

        repository = new Repository(getApplication());
        //Create term spinner
        spinner = findViewById(R.id.spinner);

        List<String> terms = new ArrayList<>();
        for (Term t : repository.getAllTerms()) {
            terms.add(t.getTermTitle());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, terms);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        //Create status spinner
        spinnerStatus = findViewById(R.id.spinner2);

        List<String> statusSpin = new ArrayList<>();
        statusSpin.add("IN PROGRESS");
        statusSpin.add("COMPLETE");
        statusSpin.add("DROPPED");
        statusSpin.add("PLAN TO TAKE");

        ArrayAdapter<String> arrayAdapterStatus = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, statusSpin);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStatus.setAdapter(arrayAdapterStatus);

        //get course title edit text
        title = findViewById(R.id.title);

        //Get course start edit text
        start = findViewById(R.id.start);

        //Get course end edit text
        end = findViewById(R.id.end);

        //Get instructor name edit text
        name = findViewById(R.id.name);

        //Get instructor phone edit text
        phone = findViewById(R.id.phone);


        //Get instructor email edit text
        email = findViewById(R.id.email);

        //Get note edit text
        note = findViewById(R.id.note);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.class_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.main:
                Intent intent = new Intent(classAdd.this, mainMenu.class);
                startActivity(intent);
                return true;
            case R.id.add:
                intent = new Intent(classAdd.this, classAdd.class);
                startActivity(intent);
                return true;
            case R.id.list:
                intent = new Intent(classAdd.this, classList.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void addClass(View view) {
        Course course;

        //Get associated term id
        String titleText = spinner.getSelectedItem().toString();
        int termId;
        Term term = repository.getAllTerms().stream()
                .filter(term1 ->titleText.equals(term1.getTermTitle()))
                .findAny()
                .orElse(null);
        termId = term.getTermId();

        //Optional note
        String optional;
        if(note.getText().toString().equals(null)){
            optional = "";
        } else {
            optional = note.getText().toString();
        }


        int newID = repository.getAllCourses().get(repository.getAllCourses().size() - 1).getCourseId() + 1;
        course = new Course(newID, title.getText().toString(), start.getText().toString(), end.getText().toString(), spinnerStatus.getSelectedItem().toString()
                , name.getText().toString(),
                phone.getText().toString(), email.getText().toString(), note.getText().toString(), termId);
        repository.insert(course);
        Toast.makeText(getApplicationContext(), title.getText().toString() + " was inserted.", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(classAdd.this, classList.class);
        startActivity(intent);
    }


}