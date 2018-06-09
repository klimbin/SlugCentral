package com.example.kevin.slugcentral;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;
import android.location.Geocoder;
import android.location.Address;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.view.View;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;

import java.io.IOException;
import java.util.Locale;
import java.util.List;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, ActivityCompat.OnRequestPermissionsResultCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 99);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng campus = new LatLng(36.9916, -122.058);
        //mMap.addMarker(new MarkerOptions().position(campus).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(campus));
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(campus, 15);
        mMap.animateCamera(cameraUpdate);

        ArrayList<Course> arrayList = new ArrayList<Course>();

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = null;

//TODO: get arraylist of users courses for the current term

//        for(int i = 0; i < arrayList.size(); i++) {
//            try {
//                geocoder.getFromLocationName(Course.getClassroom(), 1);
//            }
//            catch(IOException e) {
//                e.printStackTrace();
//            }
//
//            if (addresses.size() > 0) {
//                double latitude = addresses.get(0).getLatitude();
//                double longitude = addresses.get(0).getLongitude();
//                Log.d("LOCATION", "adding marker for class w/ location: " + latitude + longitude);
//                LatLng classPos = new LatLng(latitude, longitude);
//                Marker marker = mMap.addMarker(new MarkerOptions()
//                                        .position(classPos)
//                                        .title(Course.getName())
//                                        .snippet(Course.getClassroom() + "\n" + Course.getMeetingDays()) + " " +
//                                                Course.getStartTime() + " - " + Course.getEndTime());
//            }
//        }

//        try {
//            addresses = geocoder.getFromLocationName("Humn Lecture Hall", 1);
//        }
//        catch(IOException e) {
//            e.printStackTrace();
//        }
//        if (addresses.size() > 0) {
//            double latitude = addresses.get(0).getLatitude();
//            double longitude = addresses.get(0).getLongitude();
//            LatLng classPos = new LatLng(latitude, longitude);
//            Marker marker = mMap.addMarker(new MarkerOptions()
//                    .position(classPos)
//                    .title("CLASS NAME")
//                    .snippet("Humn Lecture Hall" + "\n" + "MoWeFr 1:20 - 2:45"));
//        }

        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {

                Context context = getApplicationContext(); //or getActivity(), YourActivity.this, etc.

                LinearLayout info = new LinearLayout(context);
                info.setOrientation(LinearLayout.VERTICAL);

                TextView title = new TextView(context);
                title.setTextColor(Color.BLACK);
                title.setGravity(Gravity.CENTER);
                title.setTypeface(null, Typeface.BOLD);
                title.setText(marker.getTitle());

                TextView snippet = new TextView(context);
                snippet.setTextColor(Color.GRAY);
                snippet.setText(marker.getSnippet());

                info.addView(title);
                info.addView(snippet);

                return info;
            }
        });

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(Marker marker) {
                marker.showInfoWindow();

                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 15);
                mMap.animateCamera(cameraUpdate);

                return true;
            }

        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        //Log.d(TAG, "callback");
        switch (requestCode) {
            case 99:
                // If the permissions aren't set, then return. Otherwise, proceed.
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                                        Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET}
                                , 10);
                    }
                    //Log.d(TAG, "returning program");
                    return;
                }
                else{
                    // Create Intent to reference MyService, start the Service.
                    //Log.d(TAG, "adding location");
                    mMap.setMyLocationEnabled(true);

                }
                break;
            default:
                break;
        }
    }
}
