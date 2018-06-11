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
    ArrayList<Course> allData = MainActivity.allData;
    public static ArrayList<Course> courses = new ArrayList<Course>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Locate the ListView in list_view_item.xml
        list = (ListView) findViewById(R.id.listview);
        list.setVisibility(View.INVISIBLE);

        courses.clear();

        for(int i = 0; i < allData.size(); i++) {
            courses.add(allData.get(i));
        }

        // Pass results to ListViewAdapter Class
        adapter = new ListViewAdapter(this, courses);

        // Binds the Adapter to the ListView
        list.setAdapter(adapter);

        final Context context = this;
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Course c = courses.get(position);
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
