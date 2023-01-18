package com.example.myapplication.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.PlaceOrder;
import com.example.myapplication.R;
import com.example.myapplication.model.Orders;
import com.example.myapplication.model.Product;

import java.util.ArrayList;

public class PlaceQuantity extends BaseAdapter {



    PlaceOrder placeOrder=new PlaceOrder();
    int order_qty;
    Context con;
    ArrayList <Orders>storelistNew = new ArrayList<>();
    ArrayList <Orders>storeBuyItems;
    private String stringVal;
    ArrayList<Product>arrayList;

   public PlaceQuantity(Context con, ArrayList<Product>arrayList, ArrayList<Orders> storelistNew){
        this.con=con;
        this.arrayList=arrayList;
        this.storelistNew=storelistNew;
    }
    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            LayoutInflater inflater=(LayoutInflater)con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    convertView=inflater.inflate(R.layout.mycustomlistview,null);
            TextView name=(TextView)convertView.findViewById(R.id.od_product_name);
            TextView price=(TextView)convertView.findViewById(R.id.od_product_price);
            TextView stocks=(TextView)convertView.findViewById(R.id.od_product_stock);
            TextView quantity=(TextView)convertView.findViewById(R.id.od_product_qty);
            TextView tot =(TextView)convertView.findViewById(R.id.od_product_total);
            Button increment_btn=(Button)convertView.findViewById(R.id.od_product_incrementbtn);

            Product product =arrayList.get(position);

            name.setText(product.getProductName());
            price.setText(product.getPrice());
            stocks.setText(product.getProductUnits());

            if(storelistNew.size()>0){

                for(Orders se_item :storelistNew){
                    if(product.getProductID() == se_item.getProductId()){
                        quantity.setText(se_item.getPoductQty());
                        tot.setText(se_item.getValue_tot());
                        stocks.setText(se_item.getStocks());

                    }

                }
            }

            increment_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean update_status=false;
                    Product product = arrayList.get(position);
                    order_qty=0;

                    if(storelistNew!= null && storelistNew.size()>0){
                        for(Orders se_item : storelistNew){

                        if(product.getProductID() == se_item.getProductId()){
                        order_qty = Integer.parseInt(se_item.getPoductQty());
                        update_status= true;
                        }
                    }
                }


                            int dec = Integer.parseInt(stocks.getText().toString());
                            if (dec != 0) {
                                dec--;

                                String setDec = String.valueOf(dec);
                                product.setProductUnits(setDec);
                                stocks.setText(setDec);
                            }
                            int basePrice = Integer.parseInt(product.getPrice());
                            if (dec != 0) {
                                order_qty++;
                                stringVal = Integer.toString(order_qty);
                                quantity.setText(stringVal);


                            } else {
                                Toast.makeText(con, "Out of Stocks", Toast.LENGTH_SHORT).show();
                            }

                            int Price = basePrice * order_qty;
                            String setnewPrice = String.valueOf(Price);
                            tot.setText(setnewPrice);

                            Orders od = new Orders();
                            od.setProductId(product.getProductID());
                            od.setProductName(product.getProductName());
                            od.setStocks(stocks.getText().toString());
                            od.setProductPrice(product.getPrice());
                            od.setPoductQty(quantity.getText().toString());
                            od.setValue_tot(tot.getText().toString());
                            od.setFull_amount(Integer.parseInt(tot.getText().toString()));

                            if(update_status){

                            if(storelistNew!= null && storelistNew.size()>0){
                             for(Orders se_item : storelistNew){
                                 if(product.getProductID() == se_item.getProductId()){
                                    se_item.setPoductQty(od.getPoductQty());
                                    se_item.setValue_tot(od.getValue_tot());
                                    se_item.setStocks(od.getStocks());
                                    se_item.setFull_amount(od.getFull_amount());
                                }
                             }
                            }
                            }else{
                              storelistNew.add(od);
                            }

                        }
                    });
            }

       return convertView;

    }

}



