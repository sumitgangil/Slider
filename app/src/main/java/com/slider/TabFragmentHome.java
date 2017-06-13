package com.slider;

import android.os.Bundle;
import android.os.Parcelable;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;


public class TabFragmentHome extends Fragment {

    boolean _areLecturesLoaded = false;
    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 3;

    public int[] tabIcons = {
            R.drawable.my_selector_video,
            R.drawable.my_selector_images,
            R.drawable.my_selector_milestone,


    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View x = inflater.inflate(R.layout.tab_layout, container, false);

        x.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });


        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        tabLayout = (TabLayout) x.findViewById(R.id.tabs);
        viewPager = (ViewPager) x.findViewById(R.id.viewpager);

        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);

                setupTabIcons();
            }


        });

        return x;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && !_areLecturesLoaded) {


            _areLecturesLoaded = true;
        } else {
        }
    }


    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);

    }


    class MyAdapter extends FragmentStatePagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);

        }

        @Override
        public Parcelable saveState() {
            return null;
        }


        @Override
        public Fragment getItem(int position) {

            Bundle bundle = new Bundle();

            switch (position) {

                case 0:
                    HomeFragment fragment1 = new HomeFragment();

                    fragment1.setArguments(bundle);
                    return fragment1;

                case 1:
                    HomeFragment fragment2 = new HomeFragment();

                    fragment2.setArguments(bundle);
                    return fragment2;

                case 2:
                    HomeFragment fragment3 = new HomeFragment();

                    fragment3.setArguments(bundle);
                    return fragment3;


            }
            return null;
        }

        @Override
        public int getCount() {

            return int_items;

        }


        @Override
        public CharSequence getPageTitle(int position) {


            switch (position) {
                case 0:
                    return "VIDEOS";
                case 1:
                    return "IMAGES";
                case 2:
                    return "MILESTONE";

            }
            return null;
        }

    }


}