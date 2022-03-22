package com.jasminkissingersheduleapp.main.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jasminkissingersheduleapp.R;
import com.jasminkissingersheduleapp.main.database.Repository;
import com.jasminkissingersheduleapp.main.entities.Term;

public class termAdd extends AppCompatActivity {
    Repository repository;

    EditText title;
    EditText start;
    EditText end;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_term);

        repository = new Repository(getApplication());

        //edit text term title
        title = findViewById(R.id.title);

        //edit text term start
        start = findViewById(R.id.start);

        //edit text term end
        end = findViewById(R.id.end);
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
                Intent intent = new Intent(termAdd.this, mainMenu.class);
                startActivity(intent);
                return true;
            case R.id.list:
                intent = new Intent(termAdd.this, termList.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void addTerm(View view) {
            Term term;
            int newID = repository.getAllTerms().get(repository.getAllTerms().size() - 1).getTermId() + 1;
            term = new Term(newID, title.getText().toString(), start.getText().toString(), end.getText().toString());
            repository.insert(term);
            Intent intent = new Intent(termAdd.this, termList.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext()," was added.",Toast.LENGTH_LONG).show();

    }
}