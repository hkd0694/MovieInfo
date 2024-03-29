package com.example.movieinfo_mvp.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.movieinfo_mvp.Adapter.MainPageAdapter;
import com.example.movieinfo_mvp.R;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TextView textView;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
    private SharedPreferences sf;
    private Date time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setCustomActionBar();
        time = new Date();
        String time1 = format.format(time);
        /*SharedPreferences sf = getSharedPreferences("Movielike", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sf.edit();
        editor.clear();
        editor.commit();*/
        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_calendar));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_search_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_launcher_foreground));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        sf = getSharedPreferences("Movielike",MODE_PRIVATE);

        MainPageAdapter mainPageAdapter = new MainPageAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(mainPageAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        textView.setText("영화 (" + time1 + ")");
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
   }

   public void setCustomActionBar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.custom_actionbar);
        View view = actionBar.getCustomView();
        textView = view.findViewById(R.id.movieDt);
   }

}
