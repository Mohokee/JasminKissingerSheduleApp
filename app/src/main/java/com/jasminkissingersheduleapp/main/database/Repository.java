package com.jasminkissingersheduleapp.main.database;

import android.app.Application;

import com.jasminkissingersheduleapp.main.dao.assessmentCRUD;
import com.jasminkissingersheduleapp.main.dao.courseCRUD;
import com.jasminkissingersheduleapp.main.dao.termCRUD;
import com.jasminkissingersheduleapp.main.entities.Assessment;
import com.jasminkissingersheduleapp.main.entities.Course;
import com.jasminkissingersheduleapp.main.entities.Term;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private termCRUD mTermCRUD;
    private courseCRUD mCourseCRUD;
    private assessmentCRUD mAssessmentCRUD;
    private ArrayList<Term> mTerms;
    private List<Term> mAllTerms;
    private List<Course> mAllCourses;
    private List<Assessment> mAllAssessments;

    private static int NUMBER_OF_THREADS=4;
    static final ExecutorService databaseExecutor= Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application){
        ScheduleDatabaseBuilder db=ScheduleDatabaseBuilder.getDatabase(application);
        mTermCRUD=db.tCrud();
        mCourseCRUD=db.cCrud();
        mAssessmentCRUD=db.aCrud();
    }

    //TERM


    public List<Term>getAllTerms(){
        databaseExecutor.execute(()->{
            mAllTerms=mTermCRUD.getAllTerms();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllTerms;
    }
    public void insert(Term term){
        databaseExecutor.execute(()->{
            mTermCRUD.insert(term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void update(Term term){
        databaseExecutor.execute(()->{
            mTermCRUD.update(term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void delete(Term term){
        databaseExecutor.execute(()->{
            mTermCRUD.delete(term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //COURSE
    public List<Course>getAllCourses(){
        databaseExecutor.execute(()->{
            mAllCourses=mCourseCRUD.getAllCourses();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllCourses;
    }

    public void insert(Course course){
        databaseExecutor.execute(()->{
            mCourseCRUD.insert(course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void update(Course course){
        databaseExecutor.execute(()->{
            mCourseCRUD.update(course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void delete(Course course){
        databaseExecutor.execute(()->{
            mCourseCRUD.delete(course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //ASSESSMENTS
    public List<Assessment>getAllAssessments(){
        databaseExecutor.execute(()->{
            mAllAssessments=mAssessmentCRUD.getAllAssessments();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllAssessments;
    }

    public void insert(Assessment assessment){
        databaseExecutor.execute(()->{
            mAssessmentCRUD.insert(assessment);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void update(Assessment assessment){
        databaseExecutor.execute(()->{
            mAssessmentCRUD.update(assessment);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void delete(Assessment assessment){
        databaseExecutor.execute(()->{
            mAssessmentCRUD.delete(assessment);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
