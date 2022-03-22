package com.jasminkissingersheduleapp.main.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.jasminkissingersheduleapp.main.dao.termCRUD;
import com.jasminkissingersheduleapp.main.database.Repository;
import com.jasminkissingersheduleapp.main.entities.Course;
import com.jasminkissingersheduleapp.main.entities.Term;

import java.util.ArrayList;
import java.util.List;

public class termDetailed extends AppCompatActivity {
    int termId;
    String termTitle;
    String termStart;
    String termEnd;

    //Text view imports
    EditText termNameText;
    EditText termStartText;
    EditText termEndText;

    Repository repository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_detailed);

        //get term id from database
        termId = getIntent().getIntExtra("id",-1);

        //get term title from database
        termTitle = getIntent().getStringExtra("termTitle");
        termNameText = findViewById(R.id.termNameText);
        termNameText.setText(termTitle);

        //get term start from database
        termStart = getIntent().getStringExtra("termStart");
        termStartText = findViewById(R.id.termStartText);
        termStartText.setText(termStart);

        //get term end from database
        termEnd = getIntent().getStringExtra("termEnd");
        termEndText = findViewById(R.id.termEndText);
        termEndText.setText(termEnd);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Get associated courses by term id
        RecyclerView recyclerView = findViewById(R.id.termAssociatedCourses);
        repository = new Repository(getApplication());
        final classAdapter classAdapter = new classAdapter(this);
        recyclerView.setAdapter(classAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Course> filteredCourses= new ArrayList<>();
        for(Course c:repository.getAllCourses()){
            if(c.getAssociatedTermId()==termId)filteredCourses.add(c);
        }
        classAdapter.setCourses(filteredCourses);

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.term_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()) {
            case android.R.id.home:
               this.finish();
               return true;
            case R.id.main:
                Intent intent = new Intent(termDetailed.this, mainMenu.class);
                startActivity(intent);
                return true;
            case R.id.add:
                intent = new Intent(termDetailed.this, termAdd.class);
                startActivity(intent);
                return true;
            case R.id.list:
                intent = new Intent(termDetailed.this, termList.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //update term

    public void updateTerm(View view) {
        Term term;
        if (termId == -1) {
            int newID = repository.getAllTerms().get(repository.getAllTerms().size() - 1).getTermId() + 1;
            term = new Term(newID, termNameText.getText().toString(), termStartText.getText().toString(), termEndText.getText().toString());
            repository.insert(term);
            Intent intent = new Intent(termDetailed.this, termList.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(),termTitle +" was inserted.",Toast.LENGTH_LONG).show();

        } else {
            term = new Term(termId, termNameText.getText().toString(), termStartText.getText().toString(), termEndText.getText().toString());
            repository.update(term);
            Intent intent = new Intent(termDetailed.this, termList.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(),termTitle +" was updated.",Toast.LENGTH_LONG).show();

        }
    }

    //Delete term
    public void deleteTerm(View view) {
        List associatedCourses = new ArrayList<>();
        for (Course course : repository.getAllCourses()) {
            associatedCourses.add(course.getCourseId());
        }
        if (associatedCourses.contains(termId)) {
            Toast.makeText(getApplicationContext(), "Terms with associated courses cannot be deleted", Toast.LENGTH_LONG).show();
        } else {
            for (Term term : repository.getAllTerms()) {
                if (term.getTermId() == termId) {
                    System.out.println(term);
                    repository.delete(term);
                    Intent intent = new Intent(termDetailed.this, termList.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), termTitle + " was deleted.", Toast.LENGTH_LONG).show();
                }
            }
        }
        }
    }


