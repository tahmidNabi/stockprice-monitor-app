package com.tnob.controllers;

import com.tnob.domain.StockRecord;
import com.tnob.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

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

    @RequestMapping("/stocks")
    public Collection<StockRecord> getStock() {

        return stockService.listAllSymbols();
    }
}
