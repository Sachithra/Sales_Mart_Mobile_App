package com.example.myapplication.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.myapplication.R;
import com.example.myapplication.model.Orders;
import java.util.ArrayList;

public class OrderViewAdptor extends ArrayAdapter<Orders> {

    public OrderViewAdptor(@NonNull Context context, ArrayList<Orders> arrayList) {
        super(context,0, arrayList);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View currentItemView = convertView;


        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate
                    (R.layout.customproductlistview, parent, false);
        }

        Orders currentNumberPosition = getItem(position);

        TextView namep_txt = currentItemView.findViewById(R.id.namep_txt);
        namep_txt.setText(currentNumberPosition.getProductName());

        TextView pricep_txt = currentItemView.findViewById(R.id.pricep_txt);
        pricep_txt.setText(currentNumberPosition.getProductPrice());

        TextView qtyp_txt = currentItemView.findViewById(R.id.qtyp_txt);
        qtyp_txt.setText(currentNumberPosition.getPoductQty());

        TextView totp_txt = currentItemView.findViewById(R.id.totp_txt);
        totp_txt.setText(currentNumberPosition.getValue_tot());


        return currentItemView;
    }

}