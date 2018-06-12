package com.example.kevin.slugcentral;

import android.content.Intent;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.alamkanak.weekview.WeekViewEvent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;

public class ScheduleActivity extends BasicActivity {

    public JSONObject jo = null;
    public JSONArray ja = null;

    // Populate the week view with some events.
    public static List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();

    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {

        // api sets up 3 months but we only want one month to avoid 3 duplicate events
        if(newMonth != 6)
            return (new ArrayList<WeekViewEvent>());

        // Read the file

        try {
            File f = new File(getFilesDir(), "file.ser");
            FileInputStream fi = new FileInputStream(f);
            ObjectInputStream o = new ObjectInputStream(fi);
            // Notice here that we are de-serializing a String object (instead of
            // a JSONObject object) and passing the String to the JSONObject's
            // constructor. That's because String is serializable and
            // JSONObject is not. To convert a JSONObject back to a String, simply
            // call the JSONObject's toString method.
            String j = null;
            try {
                j = (String) o.readObject();
            }
            catch (ClassNotFoundException c) {
                c.printStackTrace();
            }
            try {
                jo = new JSONObject(j);
                ja = jo.getJSONArray("data");
                // make all the week view events for each class
                for (int i = 0; i < ja.length(); i++) {
                    JSONObject temp = ja.getJSONObject(i);
                    if(!inSchedule(events, temp))
                        events = addClass(temp, events);
                }
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
        catch (IOException e) {
            // Here, initialize a new JSONObject
            jo = new JSONObject();
            ja = new JSONArray();
            try {
                jo.put("data", ja);
            }
            catch (JSONException j) {
                j.printStackTrace();
            }
        }

        return events;
    }

    public List<WeekViewEvent> addClass(JSONObject j, List<WeekViewEvent> events) {

        String eventTitle, id, location, daysTimes;
        eventTitle = id = location = daysTimes = "N/A";
        try {
            eventTitle = j.getString("name");
            id = j.getString("num");
            location = j.getString("location");
            daysTimes = j.getString("daysAndTime");
        }
        catch(JSONException e) {
            e.printStackTrace();
        }
        StringTokenizer st = new StringTokenizer(location,":");
        st.nextToken();
        location = st.nextToken();
        String date;
        int startHour;
        int startMin;
        int endHour;
        int endMin;
        StringTokenizer dateTime = new StringTokenizer(daysTimes," ");
        if(dateTime.countTokens() == 2)
        {
            date = dateTime.nextToken();
            if(date.equals("Cancelled"))
            {
                date = "Cancelled";
                startHour = -1;
                startMin = -1;
                endHour = -1;
                endMin = -1;
            }
            else{
                String time = dateTime.nextToken();
                StringTokenizer timeSplit = new StringTokenizer(time, "-");
                String sStartTime = timeSplit.nextToken();
                String sEndTime = timeSplit.nextToken();
                startHour = Integer.parseInt(sStartTime.substring(0,2));
                startMin = Integer.parseInt(sStartTime.substring(3,5));

                Log.d("Adding Class start time", sStartTime + " " + sEndTime);
                if(sStartTime.substring(5,7).equals("PM") && startHour < 12)
                {
                    startHour += 12;
                }

                endHour = Integer.parseInt(sEndTime.substring(0,2));
                endMin = Integer.parseInt(sEndTime.substring(3,5));

                if(sEndTime.substring(5,7).equals("PM") && endHour < 12)
                {
                    endHour += 12;
                }

            }

        }
        else{
            date = "TBA";
            startHour = -1;
            startMin = -1;
            endHour = -1;
            endMin = -1;

        }

        Log.d("Adding Class: ", eventTitle + " " + id + " " + daysTimes);

        switch(date) {
            case "M":
                events.add(makeEvent(9, id, eventTitle, location, startHour, startMin, endHour, endMin, 4));
                break;
            case "Tu":
                events.add(makeEvent(10, id, eventTitle, location, startHour, startMin, endHour, endMin, 0));
                break;
            case "TuTh":
            case"TuThSa":
                events.add(makeEvent(10, id, eventTitle, location, startHour, startMin, endHour, endMin, 0));
                events.add(makeEvent(12, id, eventTitle, location, startHour, startMin, endHour, endMin, 0));
                break;
            case "W":
                events.add(makeEvent(11, id, eventTitle, location, startHour, startMin, endHour, endMin, 1));
                break;
            case "MW":
                events.add(makeEvent(9, id, eventTitle, location, startHour, startMin, endHour, endMin, 2));
                events.add(makeEvent(11, id, eventTitle, location, startHour, startMin, endHour, endMin, 2));
                break;
            case "MWF":
                events.add(makeEvent(9, id, eventTitle, location, startHour, startMin, endHour, endMin, 3));
                events.add(makeEvent(11, id, eventTitle, location, startHour, startMin, endHour, endMin, 3));
                events.add(makeEvent(13, id, eventTitle, location, startHour, startMin, endHour, endMin, 3));
                break;
            default:
                break;
        }

        return events;

    }

    public List<WeekViewEvent> addClass(int position, List<WeekViewEvent> events) {
        //course times are specified in this format 00:00
        //course meetingDays (e.g. Monday, Wednesday, Friday)

        String eventTitle = SearchActivity.courses.get(position).getName();
        String id = SearchActivity.courses.get(position).getId();
        String location = SearchActivity.courses.get(position).getClassroom();
        StringTokenizer st = new StringTokenizer(location,":");
        st.nextToken();
        location = st.nextToken();
        String date;
        int startHour;
        int startMin;
        int endHour;
        int endMin;
        StringTokenizer dateTime = new StringTokenizer(SearchActivity.courses.get(position).getDaysTimes()," ");
        if(dateTime.countTokens() == 2)
        {
            date = dateTime.nextToken();
            if(date.equals("Cancelled"))
            {
                date = "Cancelled";
                startHour = -1;
                startMin = -1;
                endHour = -1;
                endMin = -1;
            }
            else{
                String time = dateTime.nextToken();
                StringTokenizer timeSplit = new StringTokenizer(time, "-");
                String sStartTime = timeSplit.nextToken();
                String sEndTime = timeSplit.nextToken();
                startHour = Integer.parseInt(sStartTime.substring(0,2));
                startMin = Integer.parseInt(sStartTime.substring(3,5));

                Log.d("Adding Class start time", sStartTime + " " + sEndTime);
                if(sStartTime.substring(5,7).equals("PM") && startHour < 12)
                {
                    startHour += 12;
                }

                endHour = Integer.parseInt(sEndTime.substring(0,2));
                endMin = Integer.parseInt(sEndTime.substring(3,5));

                if(sEndTime.substring(5,7).equals("PM") && endHour < 12)
                {
                    endHour += 12;
                }

            }

        }
        else{
            date = "TBA";
            startHour = -1;
            startMin = -1;
            endHour = -1;
            endMin = -1;

        }

        Log.d("Adding Class: ", SearchActivity.courses.get(position).getName() + " " + SearchActivity.courses.get(position).getDaysTimes());
        for(int i = 0; i < events.size(); i ++)
        {
            if(events.get(i).getId() == Long.valueOf(id))
            {
                Toast.makeText(ScheduleActivity.this, "This class has already been added",
                        Toast.LENGTH_LONG).show();
                return events;
            }
        }
        switch(date) {
            case "M":
                events.add(makeEvent(9, id, eventTitle, location, startHour, startMin, endHour, endMin, 4));
                break;
            case "Tu":
                events.add(makeEvent(10, id, eventTitle, location, startHour, startMin, endHour, endMin, 0));
                break;
            case "TuTh":
            case"TuThSa":
                events.add(makeEvent(10, id, eventTitle, location, startHour, startMin, endHour, endMin, 0));
                events.add(makeEvent(12, id, eventTitle, location, startHour, startMin, endHour, endMin, 0));
                break;
            case "W":
                events.add(makeEvent(11, id, eventTitle, location, startHour, startMin, endHour, endMin, 1));
                break;
            case "MW":
                events.add(makeEvent(9, id, eventTitle, location, startHour, startMin, endHour, endMin, 2));
                events.add(makeEvent(11, id, eventTitle, location, startHour, startMin, endHour, endMin, 2));
                break;
            case "MWF":
                events.add(makeEvent(9, id, eventTitle, location, startHour, startMin, endHour, endMin, 3));
                events.add(makeEvent(11, id, eventTitle, location, startHour, startMin, endHour, endMin, 3));
                events.add(makeEvent(13, id, eventTitle, location, startHour, startMin, endHour, endMin, 3));
                break;
            default:
                break;
        }

        return events;
    }

    public List<WeekViewEvent> removeClass(int position, List<WeekViewEvent> events) {
        String id = SearchActivity.courses.get(position).getId();
        for(int i = 0; i < events.size(); i++) {
            if (events.get(i).getId() == Long.valueOf(id))
                events.remove(i);
        }
        return events;
    }

    public boolean inSchedule(List<WeekViewEvent> events, JSONObject j){
        for (int i = 0; i < events.size(); i++) {
            try {
                if (events.get(i).getName().equals(j.getString("name")))
                    return true;
            }
            catch(JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public WeekViewEvent makeEvent(int date, String id, String eventTitle, String location, int startHour, int startMin, int endHour, int endMin, int c) {
        Calendar startTime = Calendar.getInstance();
        startTime.set(2018, 6, date);
        startTime.set(Calendar.HOUR_OF_DAY, startHour);
        startTime.set(Calendar.MINUTE, startMin);
        Calendar endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, endHour);
        endTime.set(Calendar.MINUTE, endMin);

        int[] colors = new int[]{R.color.event_color_01, R.color.event_color_02, R.color.event_color_03, R.color.event_color_04, R.color.colorAccent};

        WeekViewEvent event = new WeekViewEvent(Long.valueOf(id), eventTitle, location, startTime, endTime);
        event.setColor(getResources().getColor(colors[c]));
        Log.d("PLZ PRINT", startTime.toString() + " " + endTime.toString());
        return event;
    }

//        Calendar startTime = Calendar.getInstance();
//        startTime.set(2018,6, 9);
//        startTime.set(Calendar.HOUR_OF_DAY, 8);
//        startTime.set(Calendar.MINUTE, 0);
//        startTime.set(Calendar.MONTH, newMonth-1);
//        startTime.set(Calendar.YEAR, newYear);
//        Calendar endTime = (Calendar) startTime.clone();
//        endTime.add(Calendar.HOUR, 1);
//        endTime.set(Calendar.MONTH, newMonth-1);
//        WeekViewEvent event = new WeekViewEvent(1, getEventTitle(startTime), startTime, endTime);
//        event.setColor(getResources().getColor(R.color.event_color_01));
//        events.add(event);
//
//        startTime = Calendar.getInstance();
//        startTime.set(2018,6, 9);
//        startTime.set(Calendar.HOUR_OF_DAY, 11);
//        startTime.set(Calendar.MINUTE, 40);
//        startTime.set(Calendar.MONTH, newMonth-1);
//        startTime.set(Calendar.YEAR, newYear);
//        endTime = (Calendar) startTime.clone();
//        endTime.set(Calendar.HOUR_OF_DAY, 12);
//        endTime.set(Calendar.MINUTE, 40);
//        endTime.set(Calendar.MONTH, newMonth-1);
//        event = new WeekViewEvent(10, getEventTitle(startTime), startTime, endTime);
//        event.setColor(getResources().getColor(R.color.event_color_02));
//        events.add(event);
//
//        startTime = Calendar.getInstance();
//        startTime.set(2018,6, 9);
//        startTime.set(Calendar.HOUR_OF_DAY, 13);
//        startTime.set(Calendar.MINUTE, 20);
//        startTime.set(Calendar.MONTH, newMonth-1);
//        startTime.set(Calendar.YEAR, newYear);
//        endTime = (Calendar) startTime.clone();
//        endTime.set(Calendar.HOUR_OF_DAY, 14);
//        endTime.set(Calendar.MINUTE, 0);
//        event = new WeekViewEvent(10, getEventTitle(startTime), startTime, endTime);
//        event.setColor(getResources().getColor(R.color.event_color_03));
//        events.add(event);
//
//        startTime = Calendar.getInstance();
//        startTime.set(2018,6, 9);
//        startTime.set(Calendar.HOUR_OF_DAY, 14);
//        startTime.set(Calendar.MINUTE, 40);
//        startTime.set(Calendar.MONTH, newMonth-1);
//        startTime.set(Calendar.YEAR, newYear);
//        endTime = (Calendar) startTime.clone();
//        endTime.add(Calendar.HOUR_OF_DAY, 1);
//        endTime.set(Calendar.MONTH, newMonth-1);
//        event = new WeekViewEvent(2, getEventTitle(startTime), startTime, endTime);
//        event.setColor(getResources().getColor(R.color.event_color_04));
//        events.add(event);
//
//        startTime = Calendar.getInstance();
//        startTime.set(2018,6, 9);
//        startTime.set(Calendar.HOUR_OF_DAY, 10);
//        startTime.set(Calendar.MINUTE, 45);
//        startTime.set(Calendar.MONTH, newMonth-1);
//        startTime.set(Calendar.YEAR, newYear);
//        startTime.add(Calendar.DATE, 1);
//        endTime = (Calendar) startTime.clone();
//        endTime.add(Calendar.HOUR_OF_DAY, 1);
//        endTime.set(Calendar.MONTH, newMonth - 1);
//        event = new WeekViewEvent(3, getEventTitle(startTime), startTime, endTime);
//        event.setColor(getResources().getColor(R.color.colorAccent));
//        events.add(event);
//        return events;
//    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.add:
                Intent i = new Intent(ScheduleActivity.this, SearchActivity.class);
                startActivity(i);
                return true;
            case R.id.map:
                Intent j = new Intent(ScheduleActivity.this, MapsActivity.class);
                startActivity(j);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {
//        Toast.makeText(this, "Clicked " + event.getName(), Toast.LENGTH_SHORT).show();

        Intent detailIntent = new Intent(ScheduleActivity.this, DetailActivity.class);

        Log.d("remove?: ", event.getName());
        detailIntent.putExtra("name", event.getName());
        detailIntent.putExtra("caller", "Schedule");
        startActivity(detailIntent);


    }


    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect) {
//        onEventClick(event, eventRect);
    }
}