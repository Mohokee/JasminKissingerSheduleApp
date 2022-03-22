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
import com.jasminkissingersheduleapp.main.database.Repository;
import com.jasminkissingersheduleapp.main.entities.Assessment;
import com.jasminkissingersheduleapp.main.entities.Course;
import com.jasminkissingersheduleapp.main.entities.Term;

import java.util.ArrayList;
import java.util.List;

public class assessmentAdd extends AppCompatActivity {



    Repository repository;

    EditText title;
    EditText start;
    EditText end;


    Spinner spinner;
    Spinner type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_add);

        repository = new Repository(getApplication());
        //Create spinner for courses
        spinner = findViewById(R.id.spinner);

        List<String> course= new ArrayList<>();
        for(Course c:repository.getAllCourses()){
            course.add(c.getCourseTitle());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, course);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        //Create spinner for type
        //Create status spinner
        type = findViewById(R.id.type);

        List<String> statusSpin = new ArrayList<>();
        statusSpin.add("Objective");
        statusSpin.add("Performance");

        ArrayAdapter<String> arrayAdapterStatus = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, statusSpin);
        arrayAdapterStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(arrayAdapterStatus);


        //get course title edit text
        title = findViewById(R.id.title);

        //Get course start edit text
        start = findViewById(R.id.start);

        //Get course end edit text
        end = findViewById(R.id.end);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.assessment_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.main:
                Intent intent = new Intent(assessmentAdd.this, mainMenu.class);
                startActivity(intent);
                return true;
            case R.id.add:
                intent = new Intent(assessmentAdd.this, assessmentAdd.class);
                startActivity(intent);
                return true;
            case R.id.list:
                intent = new Intent(assessmentAdd.this, assessmentList.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addAssessment(View view) {
        Assessment assessment;
        String classText = spinner.getSelectedItem().toString();
        int classId;
        Course course = repository.getAllCourses().stream()
                .filter(c ->classText.equals(c.getCourseTitle()))
                .findAny()
                .orElse(null);
        classId = course.getCourseId();


        int newID = repository.getAllAssessments().get(repository.getAllAssessments().size() - 1).getAssessId() + 1;
        assessment = new Assessment(newID, title.getText().toString(), start.getText().toString(), end.getText().toString(), classId, type.getSelectedItem().toString());
        repository.insert(assessment);
        Toast.makeText(getApplicationContext(), title.getText().toString() + " was inserted.", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(assessmentAdd.this, assessmentList.class);
        startActivity(intent);
    }
}