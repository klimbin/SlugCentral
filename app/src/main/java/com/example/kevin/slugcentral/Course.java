package com.example.kevin.slugcentral;

// class for courses and sections/labs
public class Course {
    private int courseId, startTime, endTime;
    private String name, instructor, location, meetingDays;

    public Course(){

    }

    //course times are specified in this format 00:00
    //course meetingDays (e.g. Monday, Wednesday, Friday)
    public Course(int courseId, int startTime, int endTime, String name, String instructor, String location, String meetingDays) {
        this.courseId = courseId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.name = name;
        this.instructor = instructor;
        this.location = location;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // public int getColor() {
    //     return mColor;
    // }

    // public void setColor(int color) {
    //     this.mColor = color;
    // }
}
