package com.tnob.services;

import com.tnob.domain.StockRecord;

import java.util.Map;

/**
 * Created by tahmid on 3/27/16.
 */
public interface StockRecordUtilService {

    String[] getListOfStockSymbols(Iterable<StockRecord> stockRecords);

    Map<String, StockRecord> getStockRecordSymbolMap(Iterable<StockRecord> stockRecords);
}
