package com.anderscore.model;

import java.io.Serializable;
import java.util.Random;

import org.joda.time.DateTime;

/**
 * Created by pmoebius on 07.12.2015.
 */
public class StockItem implements Serializable {
    private String name;
    private Integer quantity;
    private String storageArea;
    private Long id;
    private DateTime productionDate;
    private String batch;

    /**
     * default constructor for creating a new stockitem with generated id and default values
     */
    public StockItem() {
        this(new Random().nextLong(), "", 1, "", DateTime.now(), "");
    }
    
    public StockItem(Long id, String name, Integer quantity, String storageArea, DateTime productionDate, String batch) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.storageArea = storageArea;
        this.productionDate = productionDate;
        this.batch = batch;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getStorageArea() {
        return storageArea;
    }

    public void setStorageArea(String storageArea) {
        this.storageArea = storageArea;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DateTime getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(DateTime productionDate) {
        this.productionDate = productionDate;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
