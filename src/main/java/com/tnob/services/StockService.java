package com.tnob.services;

import com.tnob.domain.Stock;

import java.util.Collection;
import java.util.List;

/**
 * Created by tahmid on 3/26/16.
 */
public interface StockService {

    public void addNewStockSymbol(Stock stock);

    public void deleteStockSymbol(Stock stock);

    public Collection<Stock> listAllSymbols();


}
