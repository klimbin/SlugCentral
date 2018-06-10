package com.example.kevin.slugcentral;

// class for courses and sections/labs
public class Course {
    private int  startHour, startMinute, endHour, endMinute;
    private String name, instructor, classroom, meetingDays, courseId, enrolled, status;

    public Course(){

    }

//    TODO: remove this shit constructor and use main one
    public Course(String name){
        this.name = name;
    }

    //course times are specified in this format 00:00
    //course meetingDays (e.g. Monday, Wednesday, Friday)
    public Course(String courseId, int startHour, int startMinute, int endHour, int endMinute, String name, String instructor, String classroom, String meetingDays, String enrolled, String status) {
        this.courseId = courseId;
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.endHour = endHour;
        this.endMinute = endMinute;
        this.name = name;
        this.instructor = instructor;
        this.classroom = classroom;
        this.meetingDays = meetingDays;
        this.enrolled = enrolled;
        this.status = status;
    }

    public String getId() {
        return courseId;
    }

    public void setId(String id) {
        this.courseId = id;
    }

    public int getStartHour() {
        return startHour;
    }

    public int getStartMinute() {
        return startMinute;
    }

    public void setStartHour(int time) {
        this.startHour = time;
    }

    public void setStartMinute(int time) {
        this.startMinute = time;
    }

    public int getEndHour() { return endHour; }

    public int getEndMinute() { return endMinute; }

    public void setEndHour(int time) {
        this.endHour = time;
    }

    public void setEndMinute(int time) {
        this.endMinute = time;
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

    public String getEnrolled(){return enrolled;}

    public void setEnrolled(String enrolled){ this.enrolled = enrolled;}

    public String getStatus(){return status;}

    public void setStatus(String status){ this.status = status;}


    // for detail view
    // public boolean timeConflicts(Course A, Course B) { return true; }

    // public int getColor() {
    //     return mColor;
    // }

    // public void setColor(int color) {
    //     this.mColor = color;
    // }
}
