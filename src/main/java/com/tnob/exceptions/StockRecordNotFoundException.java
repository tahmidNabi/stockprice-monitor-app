package com.tnob.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by tahmid on 3/28/16.
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StockRecordNotFoundException extends RuntimeException {

    public StockRecordNotFoundException(String symbol) {
        super("Could not find a Stock Record in the Database having symbol = " + symbol);
    }
}
