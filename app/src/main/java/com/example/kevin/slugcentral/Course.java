package com.example.kevin.slugcentral;

import java.io.Serializable;

// class for courses and sections/labs
public class Course implements Serializable{
    private String name, daysTimes, instructor, classroom, courseId, enrolled, status;

    public Course(){

    }

    public Course(String courseId, String daysTimes, String name, String instructor, String classroom, String enrolled, String status) {
        this.courseId = courseId;
        this.daysTimes = daysTimes;
        this.name = name;
        this.instructor = instructor;
        this.classroom = classroom;
        this.enrolled = enrolled;
        this.status = status;
    }

    public String getId() {
        return courseId;
    }

    public void setId(String id) {
        this.courseId = id;
    }

    public String getDaysTimes() {
        return daysTimes;
    }

    public void setDaysTimes(String daysTimes) {
        this.daysTimes = daysTimes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String name) {
        this.instructor = name;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getEnrolled(){return enrolled;}

    public void setEnrolled(String enrolled){ this.enrolled = enrolled;}

    public String getStatus(){return status;}

    public void setStatus(String status){ this.status = status;}



}
