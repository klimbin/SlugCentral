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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;


public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    ListView list;
    ListViewAdapter adapter;
    SearchView editSearch;
    ClassInfo[] courseNameList;
    ArrayList<Course> arrayList = new ArrayList<Course>();
    //This changes each time the server is put up since we are using the free version which gives
    //us a new url each time.
    String tURL = "https://01b94458.ngrok.io/api/v1.0/courses/all/2000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //https://stackoverflow.com/questions/4308554/simplest-way-to-read-json-from-a-url-in-java?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        //https://stackoverflow.com/questions/4308554/simplest-way-to-read-json-from-a-url-in-java?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa



        try {
            //https://stackoverflow.com/questions/4328711/read-url-to-string-in-few-lines-of-java-code?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
            String jsonString = new Scanner(new URL(tURL).openStream(), "UTF-8").useDelimiter("\\A").next();

            JSONArray jsonarray = new JSONArray(jsonString);

            ClassInfo[] tempArray = new ClassInfo[jsonarray.length()];

            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject jsonobject = jsonarray.getJSONObject(i);
                ClassInfo temp = new ClassInfo();
                temp.class_id = jsonobject.getString("class_id");
                temp.class_name = jsonobject.getString("class_name");
                temp.date_time = jsonobject.getString("date_time");
                temp.descriptive_link = jsonobject.getString("descriptive_link");
                temp.enrolled = jsonobject.getString("enrolled");
                temp.instructor = jsonobject.getString("instructor");
                temp.link_sources = jsonobject.getString("link_sources");
                temp.location = jsonobject.getString("location");
                temp.status = jsonobject.getString("status");
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
            Course course = new Course(courseNameList[i].class_name);
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
