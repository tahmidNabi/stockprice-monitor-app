package com.tnob.controllers;

import com.tnob.domain.StockPriceHistoryRecord;
import com.tnob.domain.StockRecord;
import com.tnob.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

/**
 * Created by tahmid on 3/26/16.
 */

@RestController
public class StockController {

    private StockService stockService;

    @Autowired
    public void setStockService(StockService stockService) {
        this.stockService = stockService;
    }

    @RequestMapping(value = "/stocks", method = RequestMethod.GET)
    public Collection<StockRecord> getStock() {
        return stockService.listAllSymbols();
    }

    @RequestMapping(value = "/stocks/{symbol}", method = RequestMethod.POST)
    public void addNewStockRecord(@PathVariable String symbol) {
        stockService.addNewStockRecord(symbol);
    }

    @RequestMapping(value = "/stockhistoryrecords/{symbol}", method = RequestMethod.GET)
    public List<StockPriceHistoryRecord> getStockPriceRecordHistory(@PathVariable String symbol) {

        StockRecord stockRecord = stockService.findStockRecord(symbol);

        return stockRecord.getPriceHistoryRecords();

    }

    @RequestMapping(value = "/stocks/{symbol}", method = RequestMethod.DELETE)
    public void deleteStockRecord(@PathVariable String symbol) {
        stockService.deleteStockRecord(symbol);
    }

}
