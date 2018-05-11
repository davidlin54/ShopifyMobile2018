package com.davidlin54.shopifymobile2018;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderSummaryPresenterImpl implements OrderSummaryPresenter {
    private OrderNumberComparator mOrderNumberComparator = new OrderNumberComparator();
    private List<Category> mProvinceOrderList;
    private List<Category> mYearOrderList;

    private WeakReference<OrderSummaryView> mView;

    private static OrderSummaryPresenterImpl mInstance;

    public OrderSummaryPresenterImpl(OrderSummaryView view) {
        mView = new WeakReference<>(view);
        mInstance = this;
    }

    public static OrderSummaryPresenterImpl getInstance() {
        return mInstance;
    }

    @Override
    public void fetchOrders() {
        Call<Orders> call = OrderSummaryApplication.mService.listOrders(OrderSummaryApplication.ACCESS_TOKEN);
        call.enqueue(new Callback<Orders>() {
            @Override
            public void onResponse(Call<Orders> call, Response<Orders> response) {
                Orders orders = response.body();
                if (orders == null) {
                    return;
                }

                Map<String, TreeSet<Order>> provinceOrderMap = new TreeMap<>();
                Map<Integer, TreeSet<Order>> yearOrderMap = new TreeMap<>(new YearComparator());

                for (Order order : orders.getOrders()) {
                    // build order mappings

                    // province
                    if (order.getShippingAddress() != null &&
                            order.getShippingAddress().getProvince() != null) {
                        String province = order.getShippingAddress().getProvince();
                        TreeSet<Order> ordersByProvince = provinceOrderMap.get(province) == null ?
                                new TreeSet<>(mOrderNumberComparator) : provinceOrderMap.get(province);
                        ordersByProvince.add(order);
                        provinceOrderMap.put(province, ordersByProvince);
                    }

                    // year
                    if (order.getProcessedAt() != null) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(order.getProcessedAt());

                        int year = calendar.get(Calendar.YEAR);
                        TreeSet<Order> ordersByYear = yearOrderMap.get(year) == null ?
                                new TreeSet<>(mOrderNumberComparator) : yearOrderMap.get(year);
                        ordersByYear.add(order);
                        yearOrderMap.put(year, ordersByYear);
                    }
                }

                // convert to lists to allow random access
                mProvinceOrderList = new ArrayList<>();
                mYearOrderList = new ArrayList<>();
                for (Map.Entry<String, TreeSet<Order>> entry : provinceOrderMap.entrySet()) {
                    List<Order> ordersPerCategory = new ArrayList<>();
                    ordersPerCategory.addAll(entry.getValue());
                    mProvinceOrderList.add(new Category(entry.getKey(), ordersPerCategory));
                }

                for (Map.Entry<Integer, TreeSet<Order>> entry : yearOrderMap.entrySet()) {
                    List<Order> ordersPerCategory = new ArrayList<>();
                    ordersPerCategory.addAll(entry.getValue());
                    mYearOrderList.add(new Category(String.valueOf(entry.getKey()), ordersPerCategory));
                }

                if (mView.get() != null) {
                    mView.get().onOrdersFetched();
                }
            }

            @Override
            public void onFailure(Call<Orders> call, Throwable t) {

            }
        });
    }

    @Override
    public List<Category> getOrdersByYear() {
        return mYearOrderList;
    }

    @Override
    public List<Category> getOrdersByProvince() {
        return mProvinceOrderList;
    }
}
