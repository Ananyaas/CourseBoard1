package com.example.courseboard.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PageAdapter extends FragmentStatePagerAdapter {
    int numoftabs;
    public PageAdapter(@NonNull FragmentManager fm, int numoftabs) {
        super(fm,numoftabs);
        this.numoftabs=numoftabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                CoursesFragment coursesFragment=new CoursesFragment();
                return coursesFragment;
            case 1:
                GithubFragment githubFragment=new GithubFragment();
                return githubFragment;
            case 2:
                YoutubeFragment youtubeFragment=new YoutubeFragment();
                return youtubeFragment;
            default:return null;
        }
    }

    @Override
    public int getCount() {
        return numoftabs;
    }
}
