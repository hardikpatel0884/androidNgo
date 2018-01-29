package com.adtst.ngo;

import android.content.Intent;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.adtst.ngo.Adapter.recyclerView.HotelListAdapter;
import com.adtst.ngo.interfaces.HotelClickInterface;
import com.adtst.ngo.utils.GPSTracker;
import com.adtst.ngo.utils.Json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeActivity extends AppCompatActivity implements HotelClickInterface {

    RecyclerView rvHotelList;

    BottomSheetDialog bottomSheet;
    ImageView ivClose;
    HotelListAdapter adapter;
    JSONArray hotels;
    private static final String TAG=HomeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        rvHotelList = findViewById(R.id.rv_hotel_list);
        try{
            Json j=new Json();
            JSONObject jobj=j.getJeson();
            hotels=jobj.getJSONArray("HotelList");
            adapter=new HotelListAdapter(hotels,this);
            rvHotelList.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
            rvHotelList.setAdapter(adapter);
        }catch (Exception e){e.printStackTrace();}
    }

    @Override
    public void onHotelSelect(int position) throws JSONException {
        Log.e(TAG, "onHotelSelect: "+hotels.get(position) );
        Intent showHotel=new Intent(HomeActivity.this,HotelActivity.class);
        showHotel.putExtra("hotel",hotels.get(position).toString());
        startActivity(showHotel);
    }

    @Override
    public void onSelectLocation(int position) throws JSONException {
        Log.e(TAG, "onHotelSelect: "+hotels.get(position) );
        Intent showHotel=new Intent(HomeActivity.this,MapsActivity.class);
        showHotel.putExtra("hotel",hotels.get(position).toString());
        startActivity(showHotel);
    }
}
