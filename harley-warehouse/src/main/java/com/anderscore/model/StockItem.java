package com.anderscore.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by pmoebius on 07.12.2015.
 */
public class StockItem implements Serializable {

    private Long id;
    private String name;
    private Integer quantity;
    private String storageArea;
    private Date productionDate;
    private String batch;
    private List<StockItem> relatedStockItems;

    /**
     * default constructor for creating a new stockitem with generated id and default values
     */
    public StockItem() {
        this(Long.valueOf(new Random().nextInt(9999)), "", 1, "", new Date(), "", null);
    }

    public StockItem(Long id, String name, Integer quantity, String storageArea, Date productionDate, String batch) {
        this(id, name, quantity, storageArea, productionDate, batch, null);
    }

    public StockItem(Long id, String name, Integer quantity, String storageArea, Date productionDate, String batch, List<StockItem> relatedStockItems) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.storageArea = storageArea;
        this.productionDate = productionDate;
        this.batch = batch;
        this.relatedStockItems = relatedStockItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public List<StockItem> getRelatedStockItems() {
        return relatedStockItems;
    }

    public void setRelatedStockItems(List<StockItem> relatedStockItems) {
        this.relatedStockItems = relatedStockItems;
    }

    @Override
    public String toString(){
        return name;
    }
}
