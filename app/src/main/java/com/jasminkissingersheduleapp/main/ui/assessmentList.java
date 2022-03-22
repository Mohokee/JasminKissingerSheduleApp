package com.jasminkissingersheduleapp.main.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.jasminkissingersheduleapp.R;
import com.jasminkissingersheduleapp.main.database.Repository;
import com.jasminkissingersheduleapp.main.entities.Assessment;
import com.jasminkissingersheduleapp.main.entities.Term;

import java.util.List;

public class assessmentList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Repository repository=new Repository(getApplication());
        List<Assessment> allAssessments=repository.getAllAssessments();
        RecyclerView recyclerView=findViewById(R.id.recyclerView);
        final assessmentAdapter assessmentAdapter=new assessmentAdapter(this);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        assessmentAdapter.setAssessments(allAssessments);
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
                Intent intent = new Intent(assessmentList.this, mainMenu.class);
                startActivity(intent);
                return true;
            case R.id.add:
                intent = new Intent(assessmentList.this, assessmentAdd.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addAssessment(View view) {
        Intent intent = new Intent(assessmentList.this, assessmentAdd.class);
        startActivity(intent);
    }

    public void detailedView(View view) {
        Intent intent = new Intent(assessmentList.this, assessmentDetailed.class);
        startActivity(intent);
    }
}
