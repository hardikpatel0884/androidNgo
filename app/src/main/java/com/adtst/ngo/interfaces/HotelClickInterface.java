package com.adtst.ngo.interfaces;

import org.json.JSONException;

/**
 * Created by lcom151-one on 1/24/2018.
 */

public interface HotelClickInterface {
    void onHotelSelect(int position) throws JSONException;
    void onSelectLocation(int position) throws JSONException;
}
