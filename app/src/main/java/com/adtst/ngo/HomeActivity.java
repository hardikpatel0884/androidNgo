package com.adtst.ngo;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.adtst.ngo.Adapter.recyclerView.HotelListAdapter;
import com.adtst.ngo.interfaces.HotelClickInterface;
import com.adtst.ngo.utils.GPSTracker;
import com.adtst.ngo.utils.Json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeActivity extends AppCompatActivity implements HotelClickInterface {

    RecyclerView rvHotelList;
    private DrawerLayout drawerLayout;
    private android.support.v7.widget.Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;

    BottomSheetDialog bottomSheet;
    ImageView ivClose;
    HotelListAdapter adapter;
    JSONArray hotels;
    private static final String TAG = HomeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        rvHotelList = findViewById(R.id.rv_hotel_list);
        try {
            toolbar = findViewById(R.id.layout_top);
            setSupportActionBar(toolbar);
            drawerLayout = findViewById(R.id.drawer_layout);
            nvDrawer = findViewById(R.id.nvView);
            setupNavigation(nvDrawer);
            drawerToggle = setupDrawerToggel();
            drawerLayout.addDrawerListener(drawerToggle);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
               /* Rect rectangle = new Rect();
                Window window = getWindow();
                window.getDecorView().getWindowVisibleDisplayFrame(rectangle);
                int statusBarHeight = rectangle.top;
                int contentViewTop =
                        window.findViewById(Window.ID_ANDROID_CONTENT).getTop();
                int titleBarHeight= contentViewTop - statusBarHeight;*/

                final float scale = getResources().getDisplayMetrics().density;
                int padding = (int) (10 / scale + 0.5f);
                int px = (int) (padding * scale + 5.0f);

                Log.i("*** Elenasys :: ", "dp= " + padding + " : px= " + px);
//                Log.i("*** Elenasys :: ", "StatusBar Height= " + statusBarHeight + " , TitleBar Height = " + titleBarHeight);
            }


            Json j = new Json();
            JSONObject jobj = j.getJeson();
            hotels = jobj.getJSONArray("HotelList");
            adapter = new HotelListAdapter(hotels, this);
            rvHotelList.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
            rvHotelList.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onHotelSelect(int position) throws JSONException {
        Log.e(TAG, "onHotelSelect: " + hotels.get(position));
        Intent showHotel = new Intent(HomeActivity.this, HotelActivity.class);
        showHotel.putExtra("hotel", hotels.get(position).toString());
        startActivity(showHotel);
    }

    @Override
    public void onSelectLocation(int position) throws JSONException {
        Log.e(TAG, "onHotelSelect: " + hotels.get(position));
        Intent showHotel = new Intent(HomeActivity.this, MapsActivity.class);
        showHotel.putExtra("hotel", hotels.get(position).toString());
        startActivity(showHotel);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_hotel,menu);
        try{
            SearchManager manager= (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            SearchView searchView= (SearchView) menu.findItem(R.id.search).getActionView();
            searchView.setSearchableInfo(manager.getSearchableInfo(getComponentName()));
//            searchView.setIconifiedByDefault(false);


//            searchView.setQueryHint("Search your hint");
//            int search=searchView.getContext().getResources().getIdentifier("android:id/search_plate",null,null);
//            View  view=searchView.findViewById(search);
//            if (view!=null) {
//                int searchTxt=view.getContext().getResources().getIdentifier("android:id/search_src_text",null,null);
//                TextView seTextView=view.findViewById(searchTxt);
//                if(seTextView!=null){
//                    seTextView.setHint("search hint");
////                    seTextView.setTextColor(Color.WHITE);
////                    seTextView.setHintTextColor(Color.WHITE);
//                }
//            }

        }catch (Exception e){e.printStackTrace();}
        return true;
    }

    private void setupNavigation(NavigationView view) {
        view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Toast.makeText(HomeActivity.this, "Home", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.navigation_profile:
                        Toast.makeText(HomeActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.navigation_setting:
                        Toast.makeText(HomeActivity.this, "Setting", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }

    private ActionBarDrawerToggle setupDrawerToggel() {
        return new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_open);
    }
}
