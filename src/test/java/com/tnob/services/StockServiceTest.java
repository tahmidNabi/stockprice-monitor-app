package com.tnob.services;

import com.tnob.Application;
import com.tnob.domain.StockRecord;
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

        StockRecord dummyStockRecordOne = new StockRecord("dummyOne");
        StockRecord dummyStockRecordTwo = new StockRecord("dummyTwo");

        List<StockRecord> dummyStockRecords = new ArrayList<StockRecord>();
        dummyStockRecords.add(dummyStockRecordOne);
        dummyStockRecords.add(dummyStockRecordTwo);

        Mockito.when(mockStockRepository.findAll()).thenReturn(dummyStockRecords);


        Collection<StockRecord> stockRecords = stockService.listAllSymbols();

        Assert.assertNotNull(stockRecords);
        Assert.assertEquals(2, stockRecords.size());

    }

    @Test
    public void testfindStockRecord() {
        StockRecord dummyStockRecordOne = new StockRecord("dummyOne");
        StockRecord dummyStockRecordTwo = new StockRecord("dummyTwo");

        List<StockRecord> dummyStockRecords = new ArrayList<StockRecord>();
        dummyStockRecords.add(dummyStockRecordOne);
        dummyStockRecords.add(dummyStockRecordTwo);

        Mockito.when(mockStockRepository.findBySymbol("dummyOne")).thenReturn(dummyStockRecordOne);

        StockRecord retrievedRecord = stockService.findStockRecord("dummyOne");

        Assert.assertNotNull(retrievedRecord);
        Assert.assertEquals(retrievedRecord.getSymbol(), "dummyOne");

    }

    @Test
    public void testDeleteStockRecord() {

        StockRecord dummyStockRecordOne = new StockRecord("dummyOne");
        StockRecord dummyStockRecordTwo = new StockRecord("dummyTwo");

        List<StockRecord> dummyStockRecords = new ArrayList<StockRecord>();
        dummyStockRecords.add(dummyStockRecordOne);
        dummyStockRecords.add(dummyStockRecordTwo);

        Mockito.when(mockStockRepository.findBySymbol("dummyOne")).thenReturn(dummyStockRecordOne);

        stockService.deleteStockRecord("dummyOne");

        /*check if stockservice uses the repository to find and then delete a stock record
          by calling the correct methods
        */
        Mockito.verify(mockStockRepository).findBySymbol("dummyOne");
        Mockito.verify(mockStockRepository).delete(dummyStockRecordOne);

        Mockito.when(mockStockRepository.findBySymbol("dummyThree")).thenReturn(null);

        stockService.deleteStockRecord("dummyThree");

        StockRecord nullRecord = null;

        Mockito.verify(mockStockRepository).findBySymbol("dummyThree");
        Mockito.verify(mockStockRepository, Mockito.times(0)).delete(nullRecord);



    }
}
