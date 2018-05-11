package com.davidlin54.shopifymobile2018;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProvinceFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private OrderSummaryAdapter mAdapter;
    private TextView mExpandTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_province, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mExpandTextView = view.findViewById(R.id.tv_expand_categories);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new OrderSummaryAdapter(false, Integer.MAX_VALUE);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mAdapter.updateOrders(OrderSummaryPresenterImpl.getInstance().getOrdersByProvince());

        mExpandTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mExpandTextView.getText().equals(view.getResources().getString(R.string.expand_categories))) {
                    mExpandTextView.setText(view.getResources().getString(R.string.collapse_categories));
                    mAdapter.setExpanded(true);
                } else {
                    mExpandTextView.setText(view.getResources().getString(R.string.expand_categories));
                    mAdapter.setExpanded(false);
                }
            }
        });
        return view;
    }
}
