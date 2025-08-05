package com.dhandata;

public class StockModel {
    String symbol,stockname,price;
    String ltp;

    public StockModel(String symbol, String stockname, String price, String ltp) {
        this.symbol = symbol;
        this.stockname = stockname;
        this.price = price;
        this.ltp = ltp;
    }
}
