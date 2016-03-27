package com.tnob.services;

import com.tnob.domain.Stock;
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
    public void addNewStockSymbol(Stock stock) {
        stockRepository.save(stock);
    }

    @Override
    public void deleteStockSymbol(Stock stock) {

    }

    @Override
    public Collection<Stock> listAllSymbols() {
        Iterable<Stock> stocks = stockRepository.findAll();
        List<Stock> stockList = new ArrayList<Stock>();

        stocks.forEach(stockList::add);

        return stockList;
    }
}
