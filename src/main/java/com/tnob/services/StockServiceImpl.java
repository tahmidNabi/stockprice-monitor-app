package com.tnob.services;

import com.tnob.domain.StockRecord;
import com.tnob.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by tahmid on 3/26/16.
 */

@Service
public class StockServiceImpl implements StockService {

    private StockRepository stockRepository;

    /*@Autowired
    public void setStockRepository(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }*/

    @Autowired
    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public void addNewStockSymbol(StockRecord stockRecord) {
        stockRepository.save(stockRecord);
    }

    @Override
    public void deleteStockRecord(String symbol) {
        StockRecord recordToBeDeleted = stockRepository.findBySymbol(symbol);
        if (recordToBeDeleted != null) {
            stockRepository.delete(recordToBeDeleted);
        }
    }

    @Override
    public StockRecord findStockRecord(String symbol) {
        return stockRepository.findBySymbol(symbol);
    }

    @Override
    public Collection<StockRecord> listAllSymbols() {
        Iterable<StockRecord> stocks = stockRepository.findAll();
        List<StockRecord> stockRecordList = new ArrayList<StockRecord>();

        stocks.forEach(stockRecordList::add);

        return stockRecordList;
    }
}
