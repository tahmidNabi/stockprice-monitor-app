package com.tnob.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
    @JsonIgnore
    private Date lastUpdateTime;

    @Transient
    @JsonProperty("lastUpdateTime")
    private String formattedLastUpdateTime;

    @ManyToOne
    @JoinColumn(name = "STOCK_RECORD_ID", nullable = false)
    @JsonBackReference
    private StockRecord stockRecord;

    public StockPriceHistoryRecord(BigDecimal lastTradePrice, Date lastUpdateTime, StockRecord stockRecord) {
        this.lastTradePrice = lastTradePrice;
        this.lastUpdateTime = lastUpdateTime;
        this.stockRecord = stockRecord;
    }

    public StockPriceHistoryRecord() {
    }

    public BigDecimal getLastTradePrice() {
        return lastTradePrice;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public String getFormattedLastUpdateTime() {
        formattedLastUpdateTime = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(lastUpdateTime);
        return formattedLastUpdateTime;
    }

    public StockRecord getStockRecord() {
        return stockRecord;
    }

    public void setStockRecord(StockRecord stockRecord) {
        this.stockRecord = stockRecord;
    }
}
