package com.davidlin54.shopifymobile2018;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class OrderSummaryActivity extends AppCompatActivity implements OrderSummaryView {

    private OrderSummaryPresenter mPresenter;

    private ProgressBar mProgressBar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private OrderSummaryFragmentPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        mViewPager = findViewById(R.id.view_pager);
        mTabLayout = findViewById(R.id.sliding_tabs);
        mProgressBar = findViewById(R.id.progress_bar);
        mProgressBar.setVisibility(View.VISIBLE);

        mPresenter = new OrderSummaryPresenterImpl(this);
        mPresenter.fetchOrders();
    }

    @Override
    public void onOrdersFetched() {
        mAdapter = new OrderSummaryFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        mProgressBar.setVisibility(View.GONE);
    }
}
