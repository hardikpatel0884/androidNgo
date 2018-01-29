package com.adtst.ngo;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.adtst.ngo.Adapter.viewPager.HotelMainMenuAdapter;
import com.adtst.ngo.utils.GPSTracker;
import com.adtst.ngo.utils.NonSwipeableViewPager;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HotelActivity extends AppCompatActivity implements LocationListener {

    JSONObject objHotel;
    TextView tvHeaderTitle, tvHotelName, tvAddress;
    ImageView ivClose;
    CheckBox ivFavorite;
    private static final String TAG = HotelActivity.class.getSimpleName();
    ArrayList<String> menuTitle = new ArrayList<>();
    HotelMainMenuAdapter menuAdapter;
    NonSwipeableViewPager vpMenu;
    TabLayout tlMenu;
    GPSTracker tracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_hotel);
            tvHeaderTitle = findViewById(R.id.tv_top_hotel_name);
            tvHotelName = findViewById(R.id.tv_hotel_name);
            tvAddress = findViewById(R.id.tv_hotel_address);
            ivClose = findViewById(R.id.iv_header_close);
            ivFavorite = findViewById(R.id.iv_header_favorite);
            vpMenu = findViewById(R.id.vp_hotel_menu_main);
            tlMenu = findViewById(R.id.tl_hotel_main_menu);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            tracker = new GPSTracker(HotelActivity.this);
            Intent hotel = getIntent();
            String strHotel = hotel.getStringExtra("hotel");
            objHotel = new JSONObject(strHotel);
            this.setLocationText(tracker.getLocation());
            tvHeaderTitle.setText(objHotel.getString("hotel_name"));
            tvHotelName.setText(objHotel.getString("hotel_name"));
            tvAddress.setMovementMethod(LinkMovementMethod.getInstance());
            tvAddress.setHighlightColor(Color.TRANSPARENT);
            ivClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            ivFavorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        Toast.makeText(HotelActivity.this, "Favorite", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(HotelActivity.this, "Unfavorite", Toast.LENGTH_LONG).show();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        /** creating menu with viewpager */
        try {
            menuTitle.add(0, "Menu");
            menuTitle.add(1, "About");
            menuTitle.add(2, "Review");

            menuAdapter = new HotelMainMenuAdapter(getSupportFragmentManager(), menuTitle);
            vpMenu.setAdapter(menuAdapter);
            tlMenu.setupWithViewPager(vpMenu);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setLocationText(Location location) {
        try {
            /** get distance to hotel from current location */

            Location end = new Location("start");
            end.setLatitude(Double.parseDouble(objHotel.getString("lat")));
            end.setLongitude(Double.parseDouble(objHotel.getString("lng")));

            Location start = new Location("end");
            start.setLatitude(location.getLatitude());
            start.setLongitude(location.getLongitude());

            double dis = start.distanceTo(end);
            /** get distance in dis variable in meeter */
            String strAddress = (objHotel.getString("address") + "- " + Math.round((dis / 1000)) + "km away");
            /** Spannable String */
            SpannableString ss = new SpannableString(strAddress);
            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    Intent showHotel = new Intent(HotelActivity.this, MapsActivity.class);
                    showHotel.putExtra("hotel", objHotel.toString());
                    startActivity(showHotel);
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    ds.setUnderlineText(false);
                    ds.setColor(getResources().getColor(R.color.font_hotel));
                }
            };
            ss.setSpan(clickableSpan, (objHotel.getString("address").length()) + 2, strAddress.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            /** /Spannable String */
            tvAddress.setText(ss); // set Spannable String
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        try {
            Toast.makeText(HotelActivity.this, "onLocationChange", Toast.LENGTH_LONG).show();
            this.setLocationText(location);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JSONObject getHotel(){
        return objHotel;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
