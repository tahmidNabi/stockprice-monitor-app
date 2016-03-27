package com.tnob.services;

import com.tnob.Application;
import com.tnob.domain.Stock;
import com.tnob.repositories.StockRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by tahmid on 3/27/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class StockServiceTest {

    @Mock
    private StockRepository mockStockRepository;


    private StockService stockService;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        stockService = new StockServiceImpl(mockStockRepository);

    }

    @Test
    public void testListAllSymbols() {

        Stock dummyStockOne = new Stock("dummyOne", 10);
        Stock dummyStockTwo = new Stock("dummyTwo", 20);

        List<Stock> dummyStocks = new ArrayList<Stock>();
        dummyStocks.add(dummyStockOne);
        dummyStocks.add(dummyStockTwo);

        Mockito.when(mockStockRepository.findAll()).thenReturn(dummyStocks);


        Collection<Stock> stocks = stockService.listAllSymbols();

        Assert.assertNotNull(stocks);
        Assert.assertEquals(2, stocks.size());

    }
}
