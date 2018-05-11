package com.davidlin54.shopifymobile2018;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.ViewHolder> {

    private List<Order> mOrders;
    private int mMaxDisplayCount;

    public OrderListAdapter (List<Order> orders, int maxDisplayCount) {
        mOrders = orders;
        mMaxDisplayCount = maxDisplayCount;
        if (mOrders != null) {
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order = mOrders.get(position);
        holder.mOrderNumberTextView.setText(holder.mOrderNumberTextView.getResources().getString(
                R.string.order_number, order.getOrderNumber()));
        holder.mNameTextView.setText(order.getShippingAddress() == null ? holder.mOrderNumberTextView.getResources().getString(
                R.string.name_placeholder) : order.getShippingAddress().getName());
        holder.mPriceTextView.setText(holder.mPriceTextView.getResources().getString(R.string.price, order.getTotalPrice()));
        holder.mStatusTextView.setText(order.getFinancialStatus());
    }

    @Override
    public int getItemCount() {
        return mOrders == null ? 0 : mOrders.size() > mMaxDisplayCount ? mMaxDisplayCount : mOrders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mOrderNumberTextView;
        public TextView mNameTextView;
        public TextView mPriceTextView;
        public TextView mStatusTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mOrderNumberTextView = itemView.findViewById(R.id.tv_order_number);
            mNameTextView = itemView.findViewById(R.id.tv_name);
            mPriceTextView = itemView.findViewById(R.id.tv_price);
            mStatusTextView = itemView.findViewById(R.id.tv_status);
        }
    }
}
