package com.example.skantoro.deliveryapp_testing;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.skantoro.myapplication.backend.myApi.model.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by skantoro on 10/13/15.
 */
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

        List<Order> mItems;

        public OrderAdapter() {
            super();
            mItems = new ArrayList<Order>();
            Order nature = new Order();
            nature.setOrderName("The Great Barrier Reef");
            nature.setStatus(1);
            mItems.add(nature);


            nature = new Order();
            nature.setOrderName("Order 2");
            nature.setStatus(2);
            mItems.add(nature);

            nature = new Order();
            nature.setOrderName("Order 3");
            nature.setStatus(3);
            mItems.add(nature);


            nature = new Order();
            nature.setOrderName("Order 4");
            nature.setStatus(4);
            mItems.add(nature);



            nature = new Order();
            nature.setOrderName("Order 5");
            nature.setStatus(5);
            mItems.add(nature);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.card_view_orders, viewGroup, false);
            ViewHolder viewHolder = new ViewHolder(v);
            return viewHolder;
        }


    @Override
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            Order nature = mItems.get(i);
            viewHolder.orderName.setText(nature.getOrderName());
            viewHolder.statusText.setText(nature.getStatus().toString());
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{

            public TextView orderName;
            public TextView statusText;

            public ViewHolder(View itemView) {
                super(itemView);
                orderName = (TextView)itemView.findViewById(R.id.orderName);
                statusText = (TextView)itemView.findViewById(R.id.status);
            }
        }
    }
