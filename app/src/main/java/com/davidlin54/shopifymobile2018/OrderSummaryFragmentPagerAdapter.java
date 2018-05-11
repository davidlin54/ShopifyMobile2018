package com.davidlin54.shopifymobile2018;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class OrderSummaryFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] mTabPageTitles = new String[]{ "By Province", "By Year" };

    public OrderSummaryFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ProvinceFragment();
            case 1:
                return new YearFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        return mTabPageTitles.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTabPageTitles[position];
    }
}
