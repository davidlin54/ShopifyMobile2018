package com.davidlin54.shopifymobile2018;

import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class OrderSummaryAdapter extends RecyclerView.Adapter<OrderSummaryAdapter.ViewHolder> {

    private List<Category> mOrders;
    private boolean mAreCategoriesExpanded;
    private int mMaxDisplayCount;

    public OrderSummaryAdapter(boolean isExpandedDefault, int maxDisplayCount) {
        mAreCategoriesExpanded = isExpandedDefault;
        mMaxDisplayCount = maxDisplayCount;
    }

    public void setExpanded(boolean isExpanded) {
        mAreCategoriesExpanded = isExpanded;
        notifyItemRangeChanged(0, mOrders.size());
    }

    public void updateOrders(List<Category> orders) {
        mOrders = orders;
        if (orders != null) {
            notifyItemRangeChanged(0, mOrders.size());
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mKeyTextView.setText(
                holder.mKeyTextView.getResources().getQuantityString(
                        R.plurals.order_per_category,
                        mOrders.get(position).getOrders().size(),
                        mOrders.get(position).getOrders().size(),
                        mOrders.get(position).getKey()));

        holder.mOrderRecyclerView.setAdapter(new OrderListAdapter(mOrders.get(position).getOrders(), mMaxDisplayCount));
        holder.mOrderRecyclerView.setVisibility(mAreCategoriesExpanded ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return mOrders == null ? 0 : mOrders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mKeyTextView;
        public RecyclerView mOrderRecyclerView;

        public ViewHolder(View itemView) {
            super(itemView);
            mKeyTextView = itemView.findViewById(R.id.tv_orders);
            mOrderRecyclerView = itemView.findViewById(R.id.rv_orders);
            mOrderRecyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
            mOrderRecyclerView.addItemDecoration(new DividerItemDecoration(itemView.getContext(), DividerItemDecoration.VERTICAL));
        }
    }
}
