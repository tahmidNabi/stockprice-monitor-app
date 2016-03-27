package com.tnob.repositories;

import com.tnob.domain.Stock;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

/**
 * Created by tahmid on 3/26/16.
 */


public interface StockRepository extends CrudRepository<Stock, Long> {
}
