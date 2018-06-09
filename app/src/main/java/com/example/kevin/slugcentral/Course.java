package com.example.kevin.slugcentral;

// class for courses and sections/labs
public class Course {
    private int courseId, startTime, endTime;
    private String name, instructor, classroom, meetingDays;

    public Course(){

    }

    public Course(String name){
        this.name = name;
    }

    //course times are specified in this format 00:00
    //course meetingDays (e.g. Monday, Wednesday, Friday)
    public Course(int courseId, int startTime, int endTime, String name, String instructor, String classroom, String meetingDays) {
        this.courseId = courseId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.name = name;
        this.instructor = instructor;
        this.classroom = classroom;
        this.meetingDays = meetingDays;
    }

    public int getId() {
        return courseId;
    }

    public void setId(int id) {
        this.courseId = id;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int time) {
        this.startTime = time;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int time) {
        this.endTime = time;
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

    public String getMeetingDays() {
        return meetingDays;
    }

    public void setMeetingDays(String meetingDays) {
        this.meetingDays = meetingDays;
    }

    // for detail view
    // public boolean timeConflicts(Course A, Course B) { return true; }

    // public int getColor() {
    //     return mColor;
    // }

    // public void setColor(int color) {
    //     this.mColor = color;
    // }
}
