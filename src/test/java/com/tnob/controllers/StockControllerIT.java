package com.tnob.controllers;

import com.jayway.restassured.RestAssured;
import com.tnob.Application;
import com.tnob.domain.StockPriceHistoryRecord;
import com.tnob.domain.StockRecord;
import com.tnob.repositories.StockPriceRecordRepository;
import com.tnob.repositories.StockRepository;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import static com.jayway.restassured.RestAssured.when;

/**
 * Created by tahmid on 3/28/16.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class StockControllerIT {

    public static final double dummyPriceOne = 10.0;
    public static final double dummyPriceTwo = 15.0;
    @Autowired
    StockRepository stockRepository;

    @Autowired
    StockPriceRecordRepository stockPriceRecordRepository;

    @Value("${local.server.port}")
    int port;

    StockRecord dummyOne;
    StockRecord dummyTwo;

    final String dummySymbolOne = "ONE";
    final String dummySymbolTwo = "TWO";
    final String nonExistentSymbol = "Blah";

    @Before
    public void setUp() {
        dummyOne = new StockRecord(dummySymbolOne);
        dummyTwo = new StockRecord(dummySymbolTwo);

        StockPriceHistoryRecord dummyOneHistoryOne = new StockPriceHistoryRecord(new BigDecimal(dummyPriceOne), new Date(), dummyOne);
        StockPriceHistoryRecord dummyOneHistoryTwo = new StockPriceHistoryRecord(new BigDecimal(dummyPriceTwo), new Date(), dummyOne);

        //stockPriceRecordRepository.save(dummyOneHistoryOne);
        //stockPriceRecordRepository.save(dummyOneHistoryTwo);

        dummyOne.getPriceHistoryRecords().add(dummyOneHistoryOne);
        dummyOne.getPriceHistoryRecords().add(dummyOneHistoryTwo);


        stockRepository.deleteAll();
        stockRepository.save(Arrays.asList(dummyOne, dummyTwo));
        stockPriceRecordRepository.save(Arrays.asList(dummyOneHistoryOne, dummyOneHistoryTwo));

        RestAssured.port = port;
    }

    @Test
    public void testGetAllStockRecords() {

        when().
                get("/stocks").
                then().
                statusCode(HttpStatus.SC_OK).
                body("symbol", Matchers.hasItems(dummySymbolOne, dummySymbolTwo));
    }

    @Test
    public void testGetStockPriceRecordHistory() {
        when().
                get("/stockhistoryrecords/" + dummySymbolOne).
                then().
                statusCode(HttpStatus.SC_OK).
                body("lastTradePrice", Matchers.hasSize(2));

        when().
                get("/stockhistoryrecords/" + nonExistentSymbol).
                then().
                statusCode(HttpStatus.SC_NOT_FOUND);

    }

    @After
    public void tearDown() {
        stockRepository.deleteAll();
    }

}
