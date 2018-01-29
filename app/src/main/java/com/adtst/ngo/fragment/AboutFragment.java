package com.adtst.ngo.fragment;


import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adtst.ngo.HotelActivity;
import com.adtst.ngo.MapsActivity;
import com.adtst.ngo.R;
import com.adtst.ngo.utils.GPSTracker;
import com.adtst.ngo.utils.MapHttpConnection;
import com.adtst.ngo.utils.PathJSONParser;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    JSONObject objHotel;
    SupportMapFragment mapFragment;
    GPSTracker tracker;

    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_about, container, false);
        objHotel=((HotelActivity)getActivity()).getHotel();
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                try {
                    // user location
                    LatLng userLocation = new LatLng(tracker.getLatitude(), tracker.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(userLocation).title("You"));

                    // hotel location
                    LatLng latlngHotel = new LatLng(Double.parseDouble(objHotel.getString("lat")), Double.parseDouble(objHotel.getString("lng")));
                    mMap.addMarker(new MarkerOptions().position(latlngHotel).title(objHotel.getString("hotel_name")));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latlngHotel));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlngHotel, 12.0f));

                    // drawing way
                    String url = getMapsApiDirectionsUrl(latlngHotel, userLocation);
                    ReadTask downloadTask = new ReadTask();
                    downloadTask.execute(url);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        tracker = new GPSTracker(getContext());

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

    private String getMapsApiDirectionsUrl(LatLng origin, LatLng dest) {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;


        // Sensor enabled
        String sensor = "sensor=false";

        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;


        return url;

    }

    private class ReadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... url) {
            String data = "";
            try {
                MapHttpConnection http = new MapHttpConnection();
                data = http.readUr(url[0]);


            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            new ParserTask().execute(result);
        }

    }

    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(
                String... jsonData) {
            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;
            try {
                jObject = new JSONObject(jsonData[0]);
                PathJSONParser parser = new PathJSONParser();
                routes = parser.parse(jObject);


            } catch (Exception e) {
                e.printStackTrace();
            }
            return routes;
        }

        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> routes) {
            ArrayList<LatLng> points = null;
            PolylineOptions polyLineOptions = null;

            // traversing through routes
            for (int i = 0; i < routes.size(); i++) {
                points = new ArrayList<LatLng>();
                polyLineOptions = new PolylineOptions();
                List<HashMap<String, String>> path = routes.get(i);

                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                polyLineOptions.addAll(points);
                polyLineOptions.width(4);
                polyLineOptions.color(Color.BLUE);
            }

            mMap.addPolyline(polyLineOptions);

        }
    }
}
