package com.adtst.ngo.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.adtst.ngo.Adapter.viewPager.HotelSubMenuAdapter;
import com.adtst.ngo.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment {

    ArrayList<String> subMenues=new ArrayList<>();
    ViewPager vpSubMenu;
    TabLayout tlSubMenu;
HotelSubMenuAdapter adapter;

    public MenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_menu, container, false);

        subMenues.add(0,"Main Course");
        subMenues.add(1,"Appetizer");
        subMenues.add(2,"Dessert");
        subMenues.add(3,"Soup");

        vpSubMenu = view.findViewById(R.id.vp_sub_menu);
        tlSubMenu = view.findViewById(R.id.tl_sub_menu);

        adapter=new HotelSubMenuAdapter(getFragmentManager(),subMenues);
        vpSubMenu.setAdapter(adapter);
        tlSubMenu.setupWithViewPager(vpSubMenu);

        return view;
    }

}
