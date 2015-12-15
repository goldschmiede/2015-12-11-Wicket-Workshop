package com.anderscore.stockitems.modal;

import com.anderscore.model.StockItem;

/**
 * Created by dkraemer on 14.12.15.
 */
public class NoopStockItemModalStrategy implements StockItemModalStrategy {

    @Override
    public void onSubmit(StockItem stockItem) {
    }
}
