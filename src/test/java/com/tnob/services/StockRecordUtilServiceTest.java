package com.tnob.services;

import com.tnob.domain.StockRecord;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by tahmid on 3/27/16.
 */
public class StockRecordUtilServiceTest {

    private StockRecordUtilService stockRecordUtilService;

    String [] expectedSymbols = {"dummyOne", "dummyTwo"};
    List<StockRecord> dummyStockRecords;

    @Before
    public void setUp() {
        stockRecordUtilService = new StockRecordUtilServiceImpl();
        dummyStockRecords = new ArrayList<StockRecord>();

        for (int i = 0; i < expectedSymbols.length; i++) {
            dummyStockRecords.add(new StockRecord(expectedSymbols[i]));
        }
    }

    @Test
    public void testGetListOfStockSymbols() {

        String [] actualSymbols = stockRecordUtilService.getListOfStockSymbols(dummyStockRecords);

        Assert.assertNotNull(actualSymbols);
        Assert.assertEquals(expectedSymbols.length, actualSymbols.length);

        for (int i = 0; i < expectedSymbols.length; i++) {
            Assert.assertEquals(expectedSymbols[i], actualSymbols[i]);
        }
    }

    @Test
    public void testGetStockRecordSymbolMap() {

        Map<String, StockRecord> stockRecordSymbolMap = stockRecordUtilService.getStockRecordSymbolMap(dummyStockRecords);

        Assert.assertNotNull(stockRecordSymbolMap);
        Assert.assertEquals(stockRecordSymbolMap.size(), dummyStockRecords.size());

        dummyStockRecords.forEach(stockRecord -> {Assert.assertEquals(stockRecordSymbolMap.get(stockRecord.getSymbol()), stockRecord); });

    }



}
