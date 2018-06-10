package com.example.kevin.slugcentral;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
    ArrayList<Course> arrayList = new ArrayList<Course>();
    String tURL = "https://221c682f.ngrok.io/api/v1.0/courses/all/2000";

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

                String classID = jsonobject.getString("class_id");
                String dayTimes = jsonobject.getString("date_time");
                String className = jsonobject.getString("class_name");
                String location = jsonobject.getString("location");
                String instructor = jsonobject.getString("instructor");
                String enrolled = jsonobject.getString("enrolled");
                String status = jsonobject.getString("status");

                Course temp = new Course(classID, dayTimes, className, instructor, location, enrolled, status);

                arrayList.add(temp);
            }
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

        // Pass results to ListViewAdapter Class
        adapter = new ListViewAdapter(this, arrayList);

        // Binds the Adapter to the ListView
        list.setAdapter(adapter);

        final Context context = this;
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Course c = arrayList.get(position);
//                Toast.makeText(context, "Clicked " + c.getName(), Toast.LENGTH_SHORT).show();
                // Create an Intent to reference our new activity, then call startActivity
                // to transition into the new Activity.
                Intent detailIntent = new Intent(context, DetailActivity.class);

                // pass some key value pairs to the next Activity (via the Intent)
                detailIntent.putExtra("name", c.getName());
                detailIntent.putExtra("num", c.getId());
                detailIntent.putExtra("instructor", c.getInstructor());
                detailIntent.putExtra("location", c.getClassroom());
                detailIntent.putExtra("daysAndTime", c.getDaysTimes());
                detailIntent.putExtra("status", c.getStatus());
                detailIntent.putExtra("seats", c.getEnrolled());

                detailIntent.putExtra("position", position);

                detailIntent.putExtra("caller", "SearchActivity");
                startActivity(detailIntent);
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
