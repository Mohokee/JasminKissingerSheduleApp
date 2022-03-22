package com.jasminkissingersheduleapp.main.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.jasminkissingersheduleapp.R;
import com.jasminkissingersheduleapp.main.database.Repository;
import com.jasminkissingersheduleapp.main.entities.Term;

import java.util.ArrayList;
import java.util.List;

public class termList extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Repository repository=new Repository(getApplication());
        List<Term> allTerms=repository.getAllTerms();
        RecyclerView recyclerView=findViewById(R.id.recyclerView);
        final termAdapter termAdapter=new termAdapter(this);
        recyclerView.setAdapter(termAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        termAdapter.setTerms(allTerms);

        //Search Functionality
        EditText search = findViewById(R.id.search);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                termAdapter.getFilter().filter(editable);
            }
        });
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
                Intent intent = new Intent(termList.this, mainMenu.class);
                startActivity(intent);
                return true;
            case R.id.add:
                intent = new Intent(termList.this, termAdd.class);
                startActivity(intent);
                return true;
        }
            return super.onOptionsItemSelected(item);
    }

    public void addTerm(View view) {
        Intent intent = new Intent(termList.this, termAdd.class);
        startActivity(intent);
    }

    public void detailedView(View view) {
        Intent intent = new Intent(termList.this, termDetailed.class);
        startActivity(intent);
    }

}