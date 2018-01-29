package com.adtst.ngo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adtst.ngo.Adapter.recyclerView.MainCourseAdapter;
import com.adtst.ngo.R;
import com.adtst.ngo.utils.Json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainCourseFragment extends Fragment {

    RecyclerView rvMenuCourse;
    MainCourseAdapter adapter;
    JSONArray items;

    public MainCourseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_main_course, container, false);
        rvMenuCourse=view.findViewById(R.id.rv_menu_main_course);
        Json j=new Json();
        try {
            items=j.getHotelMenu().getJSONArray("menu");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter=new MainCourseAdapter(items);
        rvMenuCourse.setLayoutManager(new GridLayoutManager(getContext(),1));
        rvMenuCourse.setAdapter(adapter);
        return view;
    }

}
