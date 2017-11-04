package com.example.caixiaolei.testdragview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private List<Fragment> fragments;
    private FragmentFirst mFragmentFirst;
    private FragmentSecond mFragmentSecond;
    private FragmentThird mFragmentThird;
    private MyFragmentPagerAdapter mFragmentPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.content);
        initData();

    }

    private void initData() {
        List<Fragment> listsFragments = getSupportFragmentManager().getFragments();
        if (listsFragments != null) {
            for (int i = 0; i < listsFragments.size(); i++) {
                Fragment currentFragment = listsFragments.get(i);
                if (currentFragment instanceof FragmentFirst) {
                    mFragmentFirst = (FragmentFirst) currentFragment;
                } else if (currentFragment instanceof FragmentSecond) {
                    mFragmentSecond = (FragmentSecond) currentFragment;
                } else if (currentFragment instanceof FragmentThird) {
                    mFragmentThird = (FragmentThird) currentFragment;
                }
            }
        }

        if (mFragmentFirst == null) {
            mFragmentFirst = new FragmentFirst();
        }

        if (mFragmentSecond == null) {
            mFragmentSecond = new FragmentSecond();
        }

        if (mFragmentThird == null) {
            mFragmentThird = new FragmentThird();
        }

        fragments = new ArrayList<Fragment>();
        fragments.add(mFragmentFirst);
        fragments.add(mFragmentSecond);
        fragments.add(mFragmentThird);

        mFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(mFragmentPagerAdapter);
    }


    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        List<Fragment> list;

        public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
}
