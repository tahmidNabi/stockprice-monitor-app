package com.tnob.domain;

import javax.persistence.*;

/**
 * Created by tahmid on 3/26/16.
 */

@Entity
public class StockRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;




    @Version
    private Integer version;

    private String symbol;
    private int price;

    public StockRecord() {
    }

    public StockRecord(String symbol, int price) {
        this.symbol = symbol;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "StockRecord{" +
                "symbol='" + symbol + '\'' +
                ", price=" + price +
                '}';
    }
}
