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
import android.widget.TextView;
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

public class assessmentDetailed extends AppCompatActivity {

    Repository repository;

    int assessId;
    String assessTitle;
    String assessStart;
    String assessEnd;
    int associatedCourseId;
    String status;

    EditText title;
    EditText start;
    EditText end;
    EditText type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_detailed);

        //get term id from database
        assessId = getIntent().getIntExtra("assessId",-1);

        //get assess' course id from database
        associatedCourseId = getIntent().getIntExtra("associatedCourseId",-1);

        //get assess title from database
        assessTitle = getIntent().getStringExtra("assessTitle");
        title = findViewById(R.id.title);
        title.setText(assessTitle);

        //get assess start from database
        assessStart = getIntent().getStringExtra("assessStart");
        start = findViewById(R.id.start);
        start.setText( assessStart);

        //get assess end from database
        assessEnd = getIntent().getStringExtra("assessEnd");
        end = findViewById(R.id.end);
        end.setText(assessEnd);

        //get assess status from database
        status = getIntent().getStringExtra("status");
        type = findViewById(R.id.type);
        type.setText(status);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Get associated course by id
        RecyclerView recyclerView = findViewById(R.id.associatedCourses);
        repository = new Repository(getApplication());
        final classAdapter classAdapter = new classAdapter(this);
        recyclerView.setAdapter(classAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Course> filteredCourses= new ArrayList<>();
        for(Course c:repository.getAllCourses()){
            if(c.getCourseId()==associatedCourseId)filteredCourses.add(c);
        }
        classAdapter.setCourses(filteredCourses);
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
                Intent intent = new Intent(assessmentDetailed.this, mainMenu.class);
                startActivity(intent);
                return true;
            case R.id.add:
                intent = new Intent(assessmentDetailed.this, assessmentAdd.class);
                startActivity(intent);
                return true;
            case R.id.list:
                intent = new Intent(assessmentDetailed.this, assessmentList.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void updateAssessment(View view) {
        Assessment assess;
        if (assessId == -1) {
            int newID = repository.getAllTerms().get(repository.getAllTerms().size() - 1).getTermId() + 1;
            assess = new Assessment(newID, title.getText().toString(), start.getText().toString(),
                    end.getText().toString(),associatedCourseId,type.getText().toString());
            repository.insert(assess);
            Intent intent = new Intent(assessmentDetailed.this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(),assessTitle +" was inserted.",Toast.LENGTH_LONG).show();

        } else {
            assess = new Assessment(assessId, title.getText().toString(), start.getText().toString(),
                    end.getText().toString(),associatedCourseId,type.getText().toString());
            repository.update(assess);
            Intent intent = new Intent(assessmentDetailed.this, assessmentList.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(),assessTitle +" was Updated.",Toast.LENGTH_LONG).show();

        }
    }

    public void deleteAssessment(View view) {
        System.out.println(assessId);
        for (Assessment a : repository.getAllAssessments()) {
            if (a.getAssessId() == assessId) {
                repository.delete(a);
                Intent intent = new Intent(assessmentDetailed.this, mainMenu.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),assessTitle +" was deleted.",Toast.LENGTH_LONG).show();
            }
        }
    }

    public void notify(View view) {
        String dateFromScreen=start.getText().toString();
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
        Intent intent= new Intent(assessmentDetailed.this,MyReceiver.class);
        intent.putExtra("key",title.getText().toString() + " " + type.getText().toString() + " Assessment is starting");
        PendingIntent sender=PendingIntent.getBroadcast(assessmentDetailed.this, ++MainActivity.numAlert,intent,0);
        AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
        //Toast.makeText(getApplicationContext(),"You will be notified when " +title.getText().toString() + " " + type.getText().toString() + "Assessment is starting",Toast.LENGTH_LONG).show();
    }

    public void notifyEnd(View view) {
        String dateFromScreen=end.getText().toString();
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
        Intent intent= new Intent(assessmentDetailed.this,MyReceiver.class);
        intent.putExtra("key",title.getText().toString() + " " + type.getText().toString() + " Assessment is ending");
        PendingIntent sender=PendingIntent.getBroadcast(assessmentDetailed.this, ++MainActivity.numAlert,intent,0);
        AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
        //Toast.makeText(getApplicationContext(),"You will be notified when " +title.getText().toString() + " " + type.getText().toString() + "Assessment is ending",Toast.LENGTH_LONG).show();
    }
}