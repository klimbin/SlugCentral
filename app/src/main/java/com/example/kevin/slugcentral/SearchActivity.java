package com.example.kevin.slugcentral;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;


public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    ListView list;
    ListViewAdapter adapter;
    SearchView editSearch;
    Course[] courseNameList;
    ArrayList<Course> arrayList = new ArrayList<Course>();
    String tURL = "https://01b94458.ngrok.io/api/v1.0/courses/all/2000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setContentView(R.layout.activity_search);
        //https://stackoverflow.com/questions/4308554/simplest-way-to-read-json-from-a-url-in-java?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        //https://stackoverflow.com/questions/4308554/simplest-way-to-read-json-from-a-url-in-java?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa


        try {
            //https://stackoverflow.com/questions/4328711/read-url-to-string-in-few-lines-of-java-code?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
            String jsonString = new Scanner(new URL(tURL).openStream(), "UTF-8").useDelimiter("\\A").next();

            JSONArray jsonarray = new JSONArray(jsonString);

            Course[] tempArray = new Course[jsonarray.length()];

            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject jsonobject = jsonarray.getJSONObject(i);

                String date;
                int startHour;
                int startMin;
                int endHour;
                int endMin;
                StringTokenizer dateTime = new StringTokenizer(jsonobject.getString("date_time")," ");
                if(dateTime.countTokens() < 3)
                {
                    date = dateTime.nextToken();
                    String time = dateTime.nextToken();
                    StringTokenizer timeSplit = new StringTokenizer(time, "-");
                    String sStartTime = timeSplit.nextToken();
                    String sEndTime = timeSplit.nextToken();
                    // 11:00AM-03:00PM
                    startHour = Integer.parseInt(sStartTime.substring(0,2));
                    startMin = Integer.parseInt(sStartTime.substring(3,5));

                    if(sStartTime.substring(5,7).equals("PM"));
                    {
                        startHour += 12;
                    }

                    endHour = Integer.parseInt(sEndTime.substring(0,2));
                    endMin = Integer.parseInt(sEndTime.substring(3,5));

                    if(sEndTime.substring(5,7).equals("PM"));
                    {
                        endHour += 12;
                    }

                }
                else{
                    date = "TBA";
                    startHour = -1;
                    startMin = -1;
                    endHour = -1;
                    endMin = -1;

                }
                String classID = jsonobject.getString("class_id");
                String className = jsonobject.getString("class_name");
                String location = jsonobject.getString("location");
                String instructor = jsonobject.getString("instructor");
                String enrolled = jsonobject.getString("enrolled");
                String status = jsonobject.getString("status");

                Course temp = new Course(classID, startHour, startMin, endHour, endMin, className, instructor, location, date, enrolled, status);


                tempArray[i] = temp;
            }
            courseNameList = tempArray;
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch(JSONException e){
            e.printStackTrace();
        }


        // Locate the ListView in list_view_item.xml
        list = (ListView) findViewById(R.id.listview);
        list.setVisibility(View.INVISIBLE);

        for (int i = 0; i < courseNameList.length; i++) {
            Course course = new Course(courseNameList[i].getName());
            // Binds all strings into an array
            arrayList.add(course);
        }

        // Pass results to ListViewAdapter Class
        adapter = new ListViewAdapter(this, arrayList);

        // Binds the Adapter to the ListView
        list.setAdapter(adapter);

        final Context context = this;
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(context, "Clicked " + arrayList.get(position).getName(), Toast.LENGTH_SHORT).show();
//                // Create an Intent to reference our new activity, then call startActivity
//                // to transition into the new Activity.
//                Intent detailIntent = new Intent(context, DetailView.class);
//
//                // pass some key value pairs to the next Activity (via the Intent)
//                detailIntent.putExtra("title", selected.title);
//                detailIntent.putExtra("time", selected.time);
//                detailIntent.putExtra("date", selected.date);
//                detailIntent.putExtra("location", selected.location);
//                detailIntent.putExtra("description", selected.description);
//
//                detailIntent.putExtra("position", position);
//
//                startActivity(detailIntent);
            }

        });

        // Locate the EditText in list_view_item.xml
        editSearch = (SearchView) findViewById(R.id.search);
        editSearch.setOnQueryTextListener(this);

    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        list.setVisibility(View.VISIBLE);
        String text = newText;
        adapter.filter(text);
        return false;
    }
}
