package com.adtst.ngo.Adapter.viewPager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.adtst.ngo.fragment.AppetizerFragment;
import com.adtst.ngo.fragment.DessertFragment;
import com.adtst.ngo.fragment.MainCourseFragment;
import com.adtst.ngo.fragment.MenuFragment;
import com.adtst.ngo.fragment.SoupFragment;

import java.util.ArrayList;

/**
 * Created by lcom151-one on 1/24/2018.
 */

public class HotelSubMenuAdapter extends FragmentStatePagerAdapter {

    ArrayList<String> subMenu;

    public HotelSubMenuAdapter(FragmentManager fm,ArrayList<String> subMenu) {
        super(fm);
        this.subMenu=subMenu;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new MainCourseFragment();
            case 1:
                return new AppetizerFragment();
            case 2:
                return new DessertFragment();
            case 3:
                return new SoupFragment();
            default:
                return new MainCourseFragment();
        }
    }

    @Override
    public int getCount() {
        return subMenu.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return subMenu.get(position);
    }
}
