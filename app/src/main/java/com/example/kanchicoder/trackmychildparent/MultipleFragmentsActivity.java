package com.example.kanchicoder.trackmychildparent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.astuetz.PagerSlidingTabStrip;

/**
 * Created by FamilyAngel on 11/8/2016.
 */
public class MultipleFragmentsActivity extends ActionBarActivity{

    public Student student;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            SessionManager sessionManager = new SessionManager(getApplicationContext());
            sessionManager.logoutUser();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public void callConductor(View view){
        Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "0" + view.findViewById(R.id.conductorContact)));
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    public void callDriver(View view){
        Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "0" + view.findViewById(R.id.driverContact)));
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multiple_fragments);
        student = (Student)getIntent().getSerializableExtra("student");
        ViewPager pager = (ViewPager) findViewById(R.id.viewPager);
        //getActionBar().setTitle(student.getStudentName());
        getSupportActionBar().setTitle(student.getStudentName());
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