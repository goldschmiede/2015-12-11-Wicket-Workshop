package com.anderscore.stockitems;

import com.anderscore.model.StockItem;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dkraemer on 14.12.15.
 */
public interface StockItemDAO extends Serializable {

    List<StockItem> getStockItems();
    void delete (StockItem stockItem);
}
