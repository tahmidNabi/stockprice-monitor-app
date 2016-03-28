package com.tnob.repositories;

import com.tnob.domain.StockPriceHistoryRecord;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by tahmid on 3/27/16.
 */
public interface StockPriceRecordRepository extends CrudRepository<StockPriceHistoryRecord, Long> {
}
