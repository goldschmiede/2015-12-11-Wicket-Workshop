package com.anderscore.stockitems.modal;

import com.anderscore.model.StockItem;

import java.io.Serializable;

/**
 * Created by dkraemer on 10.12.15.
 */
public interface StockItemModalStrategy extends Serializable{

    StockItemModalStrategy NOOP = new NoopStockItemModalStrategy();

    void onSubmit(StockItem stockItem);
}