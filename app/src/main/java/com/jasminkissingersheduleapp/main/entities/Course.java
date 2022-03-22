package com.jasminkissingersheduleapp.main.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.lang.reflect.Array;
import java.util.Date;


@Entity(tableName = "course")
public class Course {
    @PrimaryKey(autoGenerate = true)
    private int courseId;
    private String courseTitle;
    private String courseStart;
    private String courseEnd;
    private String courseStatus;
    private String instructorName;
    private String instructorPhone;
    private String instructorEmail;
    private String courseNote;
    private int associatedTermId;

    public Course(int courseId, String courseTitle, String courseStart, String courseEnd, String courseStatus, String instructorName, String instructorPhone, String instructorEmail, String courseNote, int associatedTermId) {
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.courseStart = courseStart;
        this.courseEnd = courseEnd;
        this.courseStatus = courseStatus;
        this.instructorName = instructorName;
        this.instructorPhone = instructorPhone;
        this.instructorEmail = instructorEmail;
        this.courseNote = courseNote;
        this.associatedTermId = associatedTermId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseStart() {
        return courseStart;
    }

    public void setCourseStart(String courseStart) {
        this.courseStart = courseStart;
    }

    public String getCourseEnd() {
        return courseEnd;
    }

    public void setCourseEnd(String courseEnd) {
        this.courseEnd = courseEnd;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    public String getCourseNote() {
        return courseNote;
    }

    public void setCourseNote(String courseNote) {
        this.courseNote = courseNote;
    }

    public int getAssociatedTermId() {
        return associatedTermId;
    }

    public void setAssociatedTermId(int associatedTermId) {
        this.associatedTermId = associatedTermId;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getInstructorPhone() {
        return instructorPhone;
    }

    public void setInstructorPhone(String instructorPhone) {
        this.instructorPhone = instructorPhone;
    }

    public String getInstructorEmail() {
        return instructorEmail;
    }

    public void setInstructorEmail(String instructorEmail) {
        this.instructorEmail = instructorEmail;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseTitle='" + courseTitle + '\'' +
                ", courseStart='" + courseStart + '\'' +
                ", courseEnd='" + courseEnd + '\'' +
                ", courseStatus='" + courseStatus + '\'' +
                ", instructorName='" + instructorName + '\'' +
                ", instructorPhone='" + instructorPhone + '\'' +
                ", instructorEmail='" + instructorEmail + '\'' +
                ", courseNote='" + courseNote + '\'' +
                ", associatedTermId=" + associatedTermId +
                '}';
    }
}
