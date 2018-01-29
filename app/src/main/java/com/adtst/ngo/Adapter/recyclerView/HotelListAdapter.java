package com.adtst.ngo.Adapter.recyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adtst.ngo.R;
import com.adtst.ngo.interfaces.HotelClickInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lcom151-one on 1/19/2018.
 */

public class HotelListAdapter extends RecyclerView.Adapter<HotelListAdapter.HotelViewHolder> {

    JSONArray hotelList;
    Context context;
    HotelClickInterface clickInterface;

    public HotelListAdapter(JSONArray hotelList, HotelClickInterface clickInterface) {
        this.hotelList = hotelList;
        this.clickInterface = clickInterface;
    }

    @Override
    public HotelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new HotelViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_hotel_list, null, false));
    }

    @Override
    public void onBindViewHolder(HotelViewHolder holder, final int position) {
        try {
            JSONObject hotel = hotelList.getJSONObject(position);

//            holder.llMain.setBackgroundDrawable(context.getResources().getDrawable(hotel.getInt("image")));
//            Log.e("onBindViewHolder: ",hotel.getInt("image")+"");
            holder.llMain.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.hotel_1));
            holder.tvHotelName.setText(hotel.getString("hotel_name"));
            holder.tvInfoOne.setText(hotel.getString("food_info"));
            holder.tvInfoTwo.setText(hotel.getString("star_info"));

            holder.llMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        clickInterface.onHotelSelect(position);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

            holder.ivHotelLocation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        clickInterface.onSelectLocation(position);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return hotelList.length();
    }

    public class HotelViewHolder extends RecyclerView.ViewHolder {
        LinearLayout llMain;
        TextView tvHotelName, tvInfoOne, tvInfoTwo;
        ImageView ivHotelLocation;

        public HotelViewHolder(View itemView) {
            super(itemView);

            llMain = itemView.findViewById(R.id.main);
            tvHotelName = itemView.findViewById(R.id.tv_hotel_name);
            tvInfoOne = itemView.findViewById(R.id.tv_hotel_info_one);
            tvInfoTwo = itemView.findViewById(R.id.tv_hotel_two);
            ivHotelLocation = itemView.findViewById(R.id.iv_hotel_location);
        }
    }
}
