package com.adtst.ngo.Adapter.viewPager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.adtst.ngo.fragment.AboutFragment;
import com.adtst.ngo.fragment.MenuFragment;
import com.adtst.ngo.fragment.ReviewFragment;

import java.util.ArrayList;

/**
 * Created by lcom151-one on 1/24/2018.
 */

public class HotelMainMenuAdapter extends FragmentStatePagerAdapter {
    ArrayList<String> menuTitle;

    public HotelMainMenuAdapter(FragmentManager fm, ArrayList<String> menuTitle) {
        super(fm);
        this.menuTitle = menuTitle;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return  new MenuFragment();
            case 1:
                return new AboutFragment();
            case 2:
                return new ReviewFragment();
            default:
                return new MenuFragment();
        }
    }

    @Override
    public int getCount() {
        return menuTitle.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return menuTitle.get(position);
    }
}
