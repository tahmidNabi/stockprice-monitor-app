package com.tnob.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by tahmid on 3/27/16.
 */

@Entity
public class StockPriceHistoryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private Integer version;

    private BigDecimal lastTradePrice;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateTime;

    @ManyToOne
    @JoinColumn(name = "STOCK_RECORD_ID", nullable = false)
    private StockRecord stockRecord;

    public BigDecimal getLastTradePrice() {
        return lastTradePrice;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public StockRecord getStockRecord() {
        return stockRecord;
    }

    public void setStockRecord(StockRecord stockRecord) {
        this.stockRecord = stockRecord;
    }
}
