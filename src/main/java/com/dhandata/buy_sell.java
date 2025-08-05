package com.dhandata;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class buy_sell extends Fragment {
Button buy,sell;
EditText quantity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buy.findViewById(R.id.btn_buy);
        sell.findViewById(R.id.btn_sell);
        quantity.findViewById(R.id.edittext_qty);


        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = 1; // replace this with the quantity variable in your code

                if (quantity > 1 || quantity == 1) {
                    // do something if the quantity is greater than 1 or equal to 1
                } else {
                    // do something if the quantity is not greater than 1 and not equal to 1
                }
            }
        });
        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}
