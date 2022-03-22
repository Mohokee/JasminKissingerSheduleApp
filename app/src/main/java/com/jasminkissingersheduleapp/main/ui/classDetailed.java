package com.jasminkissingersheduleapp.main.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jasminkissingersheduleapp.R;
import com.jasminkissingersheduleapp.main.database.Repository;
import com.jasminkissingersheduleapp.main.entities.Assessment;
import com.jasminkissingersheduleapp.main.entities.Course;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class classDetailed extends AppCompatActivity {

    int courseId;
    String courseTitle;
    String courseStart;
    String courseEnd;
    String courseStatus;
    String instructorName;
    String instructorPhone;
    String instructorEmail;
    String courseNote;
    int termId;
    Repository repository;

    EditText classNameText;
    EditText classStartText;
    EditText classEndText;
    EditText classStatusText;
    EditText instructorNameText;
    EditText instructorPhoneText;
    EditText instructorEmailText;
    EditText noteText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_detailed);


        //get id's from intent extras
        courseId=getIntent().getIntExtra("courseId",-1);
        termId=getIntent().getIntExtra("termId",-1);

        //get course title from database
        courseTitle = getIntent().getStringExtra("courseTitle");
        classNameText = findViewById(R.id.classNameText);
        classNameText.setText(courseTitle);

        //Get course start from db
        courseStart = getIntent().getStringExtra("courseStart");
        classStartText = findViewById(R.id.classStartText);
        classStartText.setText(courseStart);

        //Get course end from db
        courseEnd = getIntent().getStringExtra("courseEnd");
        classEndText = findViewById(R.id.classEndText);
        classEndText.setText(courseEnd);

        //Get course status from db
        courseStatus = getIntent().getStringExtra("courseStatus");
        classStatusText = findViewById(R.id.classStatusText);
        classStatusText.setText(courseStatus);

        //Get instructor name from db
        instructorName = getIntent().getStringExtra("instructorName");
        instructorNameText = findViewById(R.id.instructorNameText);
        instructorNameText.setText(instructorName);

        //Get instructor phone from db
        instructorPhone = getIntent().getStringExtra("instructorPhone");
        instructorPhoneText = findViewById(R.id.instructorPhoneText);
        instructorPhoneText.setText(instructorPhone);

        //Get instructor email from db
        instructorEmail = getIntent().getStringExtra("instructorEmail");
        instructorEmailText = findViewById(R.id.instructorEmailText);
        instructorEmailText.setText(instructorEmail);

        //Get note from db
        courseNote = getIntent().getStringExtra("courseNote");
        noteText = findViewById(R.id.noteText);
        noteText.setText(courseNote);

        //Get associated course by id
        RecyclerView recyclerView = findViewById(R.id.assessments);
        repository = new Repository(getApplication());
        final assessmentAdapter assessmentAdapter = new assessmentAdapter(this);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Assessment> filteredAssess= new ArrayList<>();
        for(Assessment a:repository.getAllAssessments()){
            if(a.getAssociatedCourseId()==courseId)filteredAssess.add(a);
        }
        assessmentAdapter.setAssessments(filteredAssess);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.class_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.main:
                Intent intent = new Intent(classDetailed.this, mainMenu.class);
                startActivity(intent);
                return true;
            case R.id.add:
                intent = new Intent(classDetailed.this, classAdd.class);
                startActivity(intent);
                return true;
            case R.id.list:
                intent = new Intent(classDetailed.this, classList.class);
                startActivity(intent);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void updateClass(View view) {
        Course course;
        if (courseId == -1) {
            int newID = repository.getAllTerms().get(repository.getAllTerms().size() - 1).getTermId() + 1;
            course = new Course(newID, classNameText.getText().toString(), classStartText.getText().toString(), classEndText.getText().toString(),
                    classStatusText.getText().toString(),instructorNameText.getText().toString(),
                    instructorPhoneText.getText().toString(),instructorEmailText.getText().toString(),noteText.getText().toString(),termId);
            repository.insert(course);
            Toast.makeText(getApplicationContext(),courseTitle +" was inserted.",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(classDetailed.this, classList.class);
            startActivity(intent);
        } else {
            course = new Course(courseId, classNameText.getText().toString(), classStartText.getText().toString(), classEndText.getText().toString(),
                    classStatusText.getText().toString(),instructorNameText.getText().toString(),
                    instructorPhoneText.getText().toString(),instructorEmailText.getText().toString(),noteText.getText().toString(),termId);
            repository.update(course);
            Toast.makeText(getApplicationContext(),courseTitle +" was Updated.",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(classDetailed.this, classList.class);
            startActivity(intent);

        }
    }

    public void deleteCourse(View view) {
        for (Course course : repository.getAllCourses()) {
            if (course.getCourseId() == courseId) {
                repository.delete(course);
                Intent intent = new Intent(classDetailed.this, classList.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),courseTitle +" was deleted.",Toast.LENGTH_LONG).show();
            }
        }
    }

    public boolean mailNote(View view) {
        Intent sendIntent=new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,noteText.getText().toString());
        sendIntent.putExtra(Intent.EXTRA_TITLE,"Note for " + classNameText.getText().toString());
        sendIntent.setType("text/plain");
        Intent shareIntent=Intent.createChooser(sendIntent,null);
        startActivity(shareIntent);
        return true;
    }

    public void notify(View view) {
        String dateFromScreen=classStartText.getText().toString();
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        Date myDate=null;
        try {
            myDate=sdf.parse(dateFromScreen);
            System.out.println(myDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Long trigger=myDate.getTime();
        Intent intent= new Intent(classDetailed.this,MyReceiver.class);
        intent.putExtra("key",classNameText.getText().toString() + " is starting");
        PendingIntent sender=PendingIntent.getBroadcast(classDetailed.this, ++MainActivity.numAlert,intent,0);
        AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
        //Toast.makeText(getApplicationContext(),"You will be notified when " + classNameText.getText().toString() + " is starting",Toast.LENGTH_LONG).show();
    }

    public void notifyEnd(View view) {
        String dateFromScreen=classEndText.getText().toString();
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        Date myDate=null;
        try {
            myDate=sdf.parse(dateFromScreen);
            System.out.println(myDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Long trigger=myDate.getTime();
        Intent intent= new Intent(classDetailed.this,MyReceiver.class);
        intent.putExtra("key",classNameText.getText().toString() + " is ending");
        PendingIntent sender=PendingIntent.getBroadcast(classDetailed.this, ++MainActivity.numAlert,intent,0);
        AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
        //Toast.makeText(getApplicationContext(),"You will be notified when " +classNameText.getText().toString() + " is ending",Toast.LENGTH_LONG).show();
    }
}