package com.nynelyne.jsoupDemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class NewsHeadlinesActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_headlines);

        viewPager = (ViewPager)findViewById(R.id.viewPager_id);

        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new NewsHeadlinesZambianFragment(),"ZAMBIAN");
        adapter.addFragment(new NewsHeadlinesAfricanFragment(),"AFRICAN");
        adapter.addFragment(new NewsHeadlinesWorldFragment(),"WORLD");

        viewPager.setAdapter(adapter);

        tabLayout = (TabLayout)findViewById(R.id.tabLayout_id);
        tabLayout.setupWithViewPager(viewPager);
    }


}
