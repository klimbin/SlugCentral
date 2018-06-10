package com.example.kevin.slugcentral;

import android.util.Log;

import com.alamkanak.weekview.WeekViewEvent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;

public class ScheduleActivity extends BasicActivity {

    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        // Populate the week view with some events.
        List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();


        //course times are specified in this format 00:00
        //course meetingDays (e.g. Monday, Wednesday, Friday)
//
//        String date;
//        int startHour;
//        int startMin;
//        int endHour;
//        int endMin;
//        StringTokenizer dateTime = new StringTokenizer(jsonobject.getString("date_time")," ");
//        if(dateTime.countTokens() == 2)
//        {
//            date = dateTime.nextToken();
//            if(date.equals("Cancelled"))
//            {
//                date = "Cancelled";
//                startHour = -1;
//                startMin = -1;
//                endHour = -1;
//                endMin = -1;
//            }
//            else{
//                String time = dateTime.nextToken();
//                Log.d("Test", jsonobject.getString("class_id") + " " + time);
//                StringTokenizer timeSplit = new StringTokenizer(time, "-");
//                String sStartTime = timeSplit.nextToken();
//                String sEndTime = timeSplit.nextToken();
//                // 11:00AM-03:00PM
//                startHour = Integer.parseInt(sStartTime.substring(0,2));
//                startMin = Integer.parseInt(sStartTime.substring(3,5));
//
//                if(sStartTime.substring(5,7).equals("PM"));
//                {
//                    startHour += 12;
//                }
//
//                endHour = Integer.parseInt(sEndTime.substring(0,2));
//                endMin = Integer.parseInt(sEndTime.substring(3,5));
//
//                if(sEndTime.substring(5,7).equals("PM"));
//                {
//                    endHour += 12;
//                }
//
//            }
//
//        }
//        else{
//            date = "TBA";
//            startHour = -1;
//            startMin = -1;
//            endHour = -1;
//            endMin = -1;
//
//        }


//        ArrayList<Course> arrayList = new ArrayList<Course>();
//        for(int i = 0; i < arrayList.size(); i++) {
//
//
//            //check class meetingdays and add weekviewevents for those days
//            //arrayList.get(i).getMeetingDays()
//            WeekViewEvent event = new WeekViewEvent(arrayList.get(i).getId(), arrayList.get(i).getName(), arrayList.get(i).getClassroom()
//                                                  , arrayList.get(i).getStartTime(), arrayList.get(i).endTime());
//        }

        Calendar startTime = Calendar.getInstance();
        startTime.set(2018,6, 9);
        startTime.set(Calendar.HOUR_OF_DAY, 8);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        Calendar endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR, 1);
        endTime.set(Calendar.MONTH, newMonth-1);
        WeekViewEvent event = new WeekViewEvent(1, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_01));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(2018,6, 9);
        startTime.set(Calendar.HOUR_OF_DAY, 11);
        startTime.set(Calendar.MINUTE, 40);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 12);
        endTime.set(Calendar.MINUTE, 40);
        endTime.set(Calendar.MONTH, newMonth-1);
        event = new WeekViewEvent(10, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_02));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(2018,6, 9);
        startTime.set(Calendar.HOUR_OF_DAY, 13);
        startTime.set(Calendar.MINUTE, 20);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 14);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(10, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_03));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(2018,6, 9);
        startTime.set(Calendar.HOUR_OF_DAY, 14);
        startTime.set(Calendar.MINUTE, 40);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 1);
        endTime.set(Calendar.MONTH, newMonth-1);
        event = new WeekViewEvent(2, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_04));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(2018,6, 9);
        startTime.set(Calendar.HOUR_OF_DAY, 10);
        startTime.set(Calendar.MINUTE, 45);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        startTime.add(Calendar.DATE, 1);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 1);
        endTime.set(Calendar.MONTH, newMonth - 1);
        event = new WeekViewEvent(3, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.colorAccent));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(2018,6, 9);
        startTime.set(Calendar.DAY_OF_MONTH, 15);
        startTime.set(Calendar.HOUR_OF_DAY, 3);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 3);
        event = new WeekViewEvent(4, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.colorAccent));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(2018,6, 9);
        startTime.set(Calendar.DAY_OF_MONTH, 1);
        startTime.set(Calendar.HOUR_OF_DAY, 3);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 3);
        event = new WeekViewEvent(5, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.colorAccent));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(2018,6, 9);
        startTime.set(Calendar.DAY_OF_MONTH, startTime.getActualMaximum(Calendar.DAY_OF_MONTH));
        startTime.set(Calendar.HOUR_OF_DAY, 15);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 3);
        event = new WeekViewEvent(5, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.colorAccent));
        events.add(event);

        return events;
    }
}