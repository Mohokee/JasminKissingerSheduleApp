package com.jasminkissingersheduleapp.main.dao;

import static androidx.room.OnConflictStrategy.IGNORE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.jasminkissingersheduleapp.main.entities.Term;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface termCRUD {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Term term);

    @Update
    void update(Term term);

    @Delete
    void delete(Term term);

    @Query("SELECT * FROM term ORDER BY termId ASC")
    List<Term> getAllTerms();

    @Query("SELECT * FROM term WHERE termId = :id")
    public Term getTermById(int id);


}
