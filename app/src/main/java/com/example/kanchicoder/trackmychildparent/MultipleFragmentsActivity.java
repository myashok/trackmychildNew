package com.example.kanchicoder.trackmychildparent;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.astuetz.PagerSlidingTabStrip;

/**
 * Created by FamilyAngel on 11/8/2016.
 */
public class MultipleFragmentsActivity extends FragmentActivity {

    public Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multiple_fragments);
        student = (Student)getIntent().getSerializableExtra("student");
        ViewPager pager = (ViewPager) findViewById(R.id.viewPager);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        pager.setOffscreenPageLimit(3);
        // Bind the tabs to the ViewPager
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(pager);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        private final String[] TITLES = { "Current Details", "Expected Schedule", "Previous Logs"};

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int pos) {
            switch(pos) {
                case 0: return new FirstFragment();
                case 1: return new SecondFragment();
                case 2: return  new ThirdFragment();
                default: return  new SecondFragment();
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }
    }
}