package com.jasminkissingersheduleapp.main.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.jasminkissingersheduleapp.main.dao.assessmentCRUD;
import com.jasminkissingersheduleapp.main.dao.courseCRUD;
import com.jasminkissingersheduleapp.main.entities.Assessment;
import com.jasminkissingersheduleapp.main.entities.Course;
import com.jasminkissingersheduleapp.main.entities.Term;
import com.jasminkissingersheduleapp.main.dao.termCRUD;

@Database(entities = {Term.class, Course.class, Assessment.class}, version = 10,exportSchema = false)
public abstract class ScheduleDatabaseBuilder extends RoomDatabase {
    public abstract termCRUD tCrud();
    public abstract courseCRUD cCrud();
    public abstract assessmentCRUD aCrud();

    private static volatile ScheduleDatabaseBuilder INSTANCE;

    static ScheduleDatabaseBuilder getDatabase(final Context context){
        if(INSTANCE==null){
            synchronized (ScheduleDatabaseBuilder.class){
                if(INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),ScheduleDatabaseBuilder.class,"KissingerScheduleApp.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
