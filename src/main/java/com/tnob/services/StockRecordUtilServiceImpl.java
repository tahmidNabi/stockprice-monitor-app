package com.tnob.services;

import com.google.common.collect.Iterables;
import com.tnob.domain.StockRecord;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tahmid on 3/27/16.
 */

@Service
public class StockRecordUtilServiceImpl implements StockRecordUtilService{

    public String[] getListOfStockSymbols(Iterable<StockRecord> stockRecords) {
        String [] symbols = new String[Iterables.size(stockRecords)];
        int index = 0;
        for (StockRecord stockRecord : stockRecords) {
            symbols[index++] = stockRecord.getSymbol();
        }
        return symbols;
    }

    public Map<String, StockRecord> getStockRecordSymbolMap(Iterable<StockRecord> stockRecords) {
        Map <String, StockRecord> stockRecordSymbolMap = new HashMap<>();

        stockRecords.forEach(stockRecord -> {stockRecordSymbolMap.put(stockRecord.getSymbol(), stockRecord);});

        return stockRecordSymbolMap;

    }
}
