package com.tnob.services;

import com.tnob.domain.StockRecord;
import com.tnob.repositories.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by tahmid on 3/26/16.
 */

@Service
public class StockServiceImpl implements StockService {

    private StockRepository stockRepository;

    private static final Logger log = LoggerFactory.getLogger(StockServiceImpl.class);

    @Autowired
    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    @Transactional
    public StockRecord addNewStockRecord(String symbol) {
        log.info("Adding stock record {}", symbol);
        StockRecord stockRecord = new StockRecord(symbol);
        stockRepository.save(stockRecord);
        return stockRecord;
    }

    @Override
    @Transactional
    public void deleteStockRecord(String symbol) {
        log.info("Deleting stock record {}", symbol);
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
