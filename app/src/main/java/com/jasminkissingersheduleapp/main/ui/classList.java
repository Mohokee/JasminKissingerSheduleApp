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
import com.jasminkissingersheduleapp.main.entities.Course;
import com.jasminkissingersheduleapp.main.entities.Term;

import java.util.List;


public class classList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Repository repository=new Repository(getApplication());
        List<Course> allCourses=repository.getAllCourses();
        RecyclerView recyclerView=findViewById(R.id.recyclerView);
        final classAdapter classAdapter=new classAdapter(this);
        recyclerView.setAdapter(classAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        classAdapter.setCourses(allCourses);
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
                Intent intent = new Intent(classList.this, mainMenu.class);
                startActivity(intent);
                return true;
            case R.id.add:
                intent = new Intent(classList.this, classAdd.class);
                startActivity(intent);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void addClass(View view) {
        Intent intent = new Intent(classList.this, classAdd.class);
        startActivity(intent);
    }

    public void detailedView(View view) {
        Intent intent = new Intent(classList.this, classDetailed.class);
        startActivity(intent);
    }
}
