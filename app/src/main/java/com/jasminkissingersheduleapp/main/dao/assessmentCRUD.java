package com.jasminkissingersheduleapp.main.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.jasminkissingersheduleapp.main.entities.Assessment;
import com.jasminkissingersheduleapp.main.entities.Course;
import com.jasminkissingersheduleapp.main.entities.Term;

import java.util.List;

@Dao
public interface assessmentCRUD {
        @Insert(onConflict = OnConflictStrategy.IGNORE)
        void insert(Assessment assessment);

        @Update
        void update(Assessment assessment);

        @Delete
        void delete(Assessment assessment);

        @Query("SELECT * FROM assessment ORDER BY assessId ASC")
        List<Assessment> getAllAssessments();
}
