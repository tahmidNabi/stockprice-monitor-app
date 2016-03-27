package com.tnob.repositories;

import com.tnob.domain.StockRecord;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by tahmid on 3/26/16.
 */


public interface StockRepository extends CrudRepository<StockRecord, Long> {
}
