package com.tnob.services;

import com.tnob.domain.StockRecord;

import java.util.Collection;

/**
 * Created by tahmid on 3/26/16.
 */
public interface StockService {

    public void addNewStockSymbol(StockRecord stockRecord);

    public void deleteStockRecord(String symbol);

    public StockRecord findStockRecord(String symbol);

    public Collection<StockRecord> listAllSymbols();


}
