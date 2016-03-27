package com.tnob.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Column(length = 5, unique = true)
    private String symbol;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "stockRecord")
    @JsonManagedReference
    private List<StockPriceHistoryRecord> priceHistoryRecords = new ArrayList<>(0);


    public StockRecord() {
    }

    public StockRecord(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }


    public List<StockPriceHistoryRecord> getPriceHistoryRecords() {
        return priceHistoryRecords;
    }


}
