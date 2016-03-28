package com.tnob.services;

import com.tnob.domain.StockRecord;

import java.util.List;

/**
 * Created by tahmid on 3/27/16.
 */
public interface StockPriceRecordFetchService {

    void fetchAndUpdateStockPriceRecord() throws Exception;

}
