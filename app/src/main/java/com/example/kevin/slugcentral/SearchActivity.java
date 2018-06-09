package com.example.kevin.slugcentral;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;


public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    ListView list;
    ListViewAdapter adapter;
    SearchView editSearch;
    String[] courseNameList;
    ArrayList<Course> arrayList = new ArrayList<Course>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        courseNameList = new String[]{"CMPS 121 - Mobile Applications", "CMPE 107 - Probability and Statistics for Engineers", "CMPE 16 - Discrete Math",
                "AMS 147", "CMPS 130", "CMPS 102", "CMPE 110", "CMPS 183",
                "CMPS 115"};

        // Locate the ListView in list_view_item.xml
        list = (ListView) findViewById(R.id.listview);
        list.setVisibility(View.INVISIBLE);

        for (int i = 0; i < courseNameList.length; i++) {
            Course course = new Course(courseNameList[i]);
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
