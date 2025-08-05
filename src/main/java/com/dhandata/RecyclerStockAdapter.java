package com.dhandata;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerStockAdapter  extends RecyclerView.Adapter<RecyclerStockAdapter.ViewHolder> {
    Context context;
    ArrayList<StockModel>stockModelArrayList;
    private android.view.View View;

    RecyclerStockAdapter(Context context, ArrayList<StockModel>stockModelArrayList){
        this.context=context;
        this.stockModelArrayList=stockModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View v=  LayoutInflater.from(context).inflate(R.layout.item_stock,parent,false);
       ViewHolder viewHolder = new ViewHolder(View);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {//here we mention kaha pe konse pe data ko set karna hai like textview
        final StockModel temp=stockModelArrayList.get(position);

        holder.symbol.setText(stockModelArrayList.get(position).symbol );
        holder.stockname.setText(stockModelArrayList.get(position).stockname );
        holder.price.setText(stockModelArrayList.get(position).price );
        holder.ltp.setText(stockModelArrayList.get(position).ltp );

        holder.stockname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Intent intent = new Intent(context, Stock_details.class);
                intent.putExtra("Symbol",temp.symbol);
                intent.putExtra("Stock Name",temp.stockname);
                intent.putExtra("Price",temp.price);
                intent.putExtra("ltp",temp.ltp);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return stockModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView symbol,stockname,price,ltp;
        public ViewHolder(View itemView) {
            super(itemView);
            symbol =itemView.findViewById(R.id.st_symbol);
            stockname =itemView.findViewById(R.id.st_name);
            price =itemView.findViewById(R.id.st_price);
            ltp =itemView.findViewById(R.id.st_ltpchange);

        }
    }
}
