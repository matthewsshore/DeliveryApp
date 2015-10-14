package com.example.skantoro.deliveryapp_testing;

import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.skantoro.myapplication.backend.myApi.model.Order;
import com.example.skantoro.myapplication.backend.myApi.model.OrderCollection;
import com.example.skantoro.myapplication.backend.myApi.model.UserCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by skantoro on 10/13/15.
 */
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder>{
        List<Order> mOrders;

        public OrderAdapter(OrderCollection result) {
            super();
            mOrders = new ArrayList<Order>();
            for (int i = 0; i < result.getItems().size(); i++) {
                Log.w("ResultLength", "i: " + i);
                Order addOrder = new Order();
                addOrder.setOrderName(result.getItems().get(i).getOrderName());
                addOrder.setStatus(result.getItems().get(i).getStatus());
                addOrder.setBid(result.getItems().get(i).getBid());
                mOrders.add(addOrder);

            }
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
        String statusText = "Unknown Status";
        Order addOrder = mOrders.get(i);
        viewHolder.orderName.setText(addOrder.getOrderName());
        if (addOrder.getStatus() == 1){
            statusText = "Out for Bid";
        }
        if (addOrder.getStatus() == 2){
            statusText = "Accepted";
        }
        if (addOrder.getStatus() == 3){
            statusText = "En Route";
        }
        if (addOrder.getStatus() == 4){
            statusText = "Delivery";
        }
        viewHolder.statusText.setText(statusText);
        viewHolder.bidText.setText("$"+addOrder.getBid().toString());
        }

        @Override
        public int getItemCount() {
            return mOrders.size();
        }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView orderName;
        public TextView statusText;
        public TextView bidText;
        public View view;

            public ViewHolder(View itemView) {
                super(itemView);
                orderName = (TextView)itemView.findViewById(R.id.orderName);
                statusText = (TextView)itemView.findViewById(R.id.status);
                bidText = (TextView)itemView.findViewById(R.id.bid);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View v) {
                      //  Log.w("Clicked", "Clicked: " + getAdapterPosition());
                        Order clickedOrder = mOrders.get(getAdapterPosition());
                        String orderName = clickedOrder.getOrderName();
                        Log.w("Order", "Order name clicked: " + orderName);
                    }
                });
            }
        }
    }
