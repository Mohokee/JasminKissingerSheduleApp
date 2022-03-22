package com.jasminkissingersheduleapp.main.ui;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.jasminkissingersheduleapp.R;
import com.jasminkissingersheduleapp.main.database.Repository;
import com.jasminkissingersheduleapp.main.entities.Assessment;
import com.jasminkissingersheduleapp.main.entities.Course;
import com.jasminkissingersheduleapp.main.entities.Term;


import java.util.ArrayList;
import java.util.List;

public class search extends AppCompatActivity {

    SearchView searchView;
    ListView listView;
    ArrayList list;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchView = findViewById(R.id.searchView);
        listView = findViewById(R.id.listView);
        list = new ArrayList<>();
        Repository repository=new Repository(getApplication());
        List<Term> allTerms=repository.getAllTerms();
        List<Assessment> allAssess=repository.getAllAssessments();
        List<Course> allCourse=repository.getAllCourses();

        for (Term t : allTerms)
        {
            String title = t.getTermTitle();
            list.add(title);
        }

        for (Course c : allCourse)
        {
            String title = c.getCourseTitle();
            list.add(title);
        }
        for (Assessment a : allAssess)
        {
            String title = a.getAssessTitle();
            list.add(title);
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(list.contains(query)){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(search.this, "No Match found",Toast.LENGTH_LONG).show();
                }
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }

}
