package com.adtst.ngo.Adapter.recyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.adtst.ngo.R;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by lcom151-one on 1/25/2018.
 */

public class MainCourseAdapter extends RecyclerView.Adapter<MainCourseAdapter.CourseHolder> {

    JSONArray items;
    Context context;

    public MainCourseAdapter(JSONArray items) {
        this.items = items;
    }

    @Override
    public CourseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        return new CourseHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu_course, null, false));
    }

    @Override
    public void onBindViewHolder(CourseHolder holder, int position) {
        try {
            JSONObject item=items.getJSONObject(position);
            Log.e( "onBindViewHolder: ", item.toString());
//            holder.ivImage.setImageDrawable(context.getResources().getDrawable(Integer.parseInt(item.getString("image"))));
            holder.ivImage.setImageDrawable(context.getResources().getDrawable(R.drawable.img));
            holder.tvName.setText(item.getString("item_name"));
            String strPrice=context.getString(R.string.rs)+" "+item.getString("price");
            holder.tvPrice.setText(strPrice);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return items.length();
    }

    public class CourseHolder extends RecyclerView.ViewHolder {

        ImageView ivImage;
        TextView tvName, tvPrice;

        public CourseHolder(View itemView) {
            super(itemView);

            ivImage = itemView.findViewById(R.id.item_image);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvPrice = itemView.findViewById(R.id.tv_item_price);
        }
    }
}
