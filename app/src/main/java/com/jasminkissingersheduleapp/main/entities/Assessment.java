package com.jasminkissingersheduleapp.main.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "assessment")
public class Assessment {
    @PrimaryKey(autoGenerate = true)
    private int assessId;
    private String assessTitle;
    private String assessStart;
    private String assessEnd;
    private int associatedCourseId;
    private String status;

    public Assessment(int assessId, String assessTitle, String assessStart, String assessEnd,int associatedCourseId,String status) {
        this.assessId = assessId;
        this.assessTitle = assessTitle;
        this.assessStart = assessStart;
        this.assessEnd = assessEnd;
        this.associatedCourseId = associatedCourseId;
        this.status=status;
    }

    public int getAssessId() {
        return assessId;
    }

    public void setAssessId(int assessId) {
        this.assessId = assessId;
    }

    public String getAssessTitle() {
        return assessTitle;
    }

    public void setAssessTitle(String assessTitle) {
        this.assessTitle = assessTitle;
    }

    public String getAssessStart() {
        return assessStart;
    }

    public void setAssessStart(String assessStart) {
        this.assessStart = assessStart;
    }

    public String getAssessEnd() {
        return assessEnd;
    }

    public void setAssessEnd(String assessEnd) {
        this.assessEnd = assessEnd;
    }

    public int getAssociatedCourseId() {
        return associatedCourseId;
    }

    public void setAssociatedCourseId(int associatedCourseId) {
        this.associatedCourseId = associatedCourseId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Assessment{" +
                "assessId=" + assessId +
                ", assessTitle='" + assessTitle + '\'' +
                ", assessStart='" + assessStart + '\'' +
                ", assessEnd='" + assessEnd + '\'' +
                ", status='" + status + '\'' +
                ", associatedCourseId=" + associatedCourseId +
                '}';
    }
}
