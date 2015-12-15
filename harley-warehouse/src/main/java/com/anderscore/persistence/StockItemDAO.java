package com.anderscore.persistence;

import com.anderscore.model.StockItem;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dkraemer on 14.12.15.
 */
public interface StockItemDAO extends Serializable {

    StockItem getById(Long id);
    void create(StockItem stockItem);
    void update(StockItem stockItem);
    void delete (StockItem stockItem);
    List<StockItem> getAll();
    List<StockItem> getRange(long start, long end);
    long countAll();
}
