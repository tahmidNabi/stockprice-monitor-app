package com.tnob.services;

import com.tnob.domain.StockPriceHistoryRecord;
import com.tnob.domain.StockRecord;
import com.tnob.repositories.StockPriceRecordRepository;
import com.tnob.repositories.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by tahmid on 3/27/16.
 */

@Component
public class YahooFinanceStockPriceRecordFetchServiceImpl implements StockPriceRecordFetchService {

    private StockRepository stockRepository;
    private StockPriceRecordRepository stockPriceRecordRepository;
    private StockRecordUtilService stockRecordUtilService;

    private static final Logger log = LoggerFactory.getLogger(YahooFinanceStockPriceRecordFetchServiceImpl.class);

    @Autowired
    public void setStockRepository(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Autowired
    public void setStockPriceRecordRepository(StockPriceRecordRepository stockPriceRecordRepository) {
        this.stockPriceRecordRepository = stockPriceRecordRepository;
    }

    @Autowired
    public void setStockRecordUtilService(StockRecordUtilService stockRecordUtilService) {
        this.stockRecordUtilService = stockRecordUtilService;
    }

    @Override
    @Transactional
    @Scheduled(cron = "${stock.monitor.fetch.interval}")
    public void fetchAndUpdateStockPriceRecord() throws Exception {

        log.info("Fetching from Yahoo API at: {}", new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date()));

        Iterable<StockRecord> stockRecords = stockRepository.findAll();
        String[] listOfSymbols = stockRecordUtilService.getListOfStockSymbols(stockRecords);
        Map<String, StockRecord> stockRecordSymbolMap = stockRecordUtilService.getStockRecordSymbolMap(stockRecords);

        Map<String, Stock> yahooFinanceStockRecords = YahooFinance.get(listOfSymbols);

        for (String symbol : yahooFinanceStockRecords.keySet()) {
            Stock yahooFinanceStockRecord = yahooFinanceStockRecords.get(symbol);
            BigDecimal lastTradePrice = yahooFinanceStockRecord.getQuote(true).getPrice();
            Date retrievalTime = new Date();
            StockRecord owningStockRecord = stockRecordSymbolMap.get(symbol);
            log.info("Adding new Stock price record for {}", symbol);
            StockPriceHistoryRecord stockPriceHistoryRecord = new StockPriceHistoryRecord(lastTradePrice,
                    retrievalTime, owningStockRecord);

            stockPriceRecordRepository.save(stockPriceHistoryRecord);

            owningStockRecord.getPriceHistoryRecords().add(stockPriceHistoryRecord);
            stockRepository.save(owningStockRecord);


        }

    }
}
