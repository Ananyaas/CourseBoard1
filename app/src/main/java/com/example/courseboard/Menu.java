package com.example.courseboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.SearchView;

import com.example.courseboard.Adapters.CoursesFragment;
import com.example.courseboard.Adapters.GithubFragment;
import com.example.courseboard.Adapters.YoutubeFragment;
import com.google.android.material.tabs.TabLayout;

public class Menu extends AppCompatActivity {

    private TabLayout tablayout=null;
    private ViewPager viewPager=null;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        tablayout=findViewById(R.id.tab_layout);
        viewPager=findViewById(R.id.view_pager);
        searchView=findViewById(R.id.search);
        tablayout.addTab(tablayout.newTab().setText("Courses"));
        tablayout.addTab(tablayout.newTab().setText("Github repos"));
        tablayout.addTab(tablayout.newTab().setText("Youtube"));

        final PagerAdapter Adapter=new com.example.courseboard.Adapters.PageAdapter(getSupportFragmentManager(),tablayout.getTabCount());
        viewPager.setAdapter(Adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));

        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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


       searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
           @Override
           public boolean onQueryTextSubmit(String query) {
               Bundle bundle = new Bundle();
               bundle.putString("q", query);
               YoutubeFragment fragobj = new YoutubeFragment();
               GithubFragment fragobj1 = new GithubFragment();
               CoursesFragment fragobj2 = new CoursesFragment();
               fragobj.setArguments(bundle);
               fragobj1.setArguments(bundle);
               fragobj2.setArguments(bundle);
               return true;
           }

           @Override
           public boolean onQueryTextChange(String newText) {
               return false;
           }
       });

    }
}