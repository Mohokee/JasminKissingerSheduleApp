package com.jasminkissingersheduleapp.main.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.jasminkissingersheduleapp.main.entities.Course;
import com.jasminkissingersheduleapp.main.entities.Term;

import java.util.List;

@Dao
public interface courseCRUD {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Course course);

    @Update
    void update(Course course);

    @Delete
    void delete(Course course);

    @Query("SELECT * FROM course ORDER BY courseId ASC")
    List<Course> getAllCourses();
}
