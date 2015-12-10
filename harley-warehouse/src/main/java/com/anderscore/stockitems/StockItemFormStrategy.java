package com.anderscore.stockitems;

import com.anderscore.model.StockItem;

/**
 * Created by dkraemer on 10.12.15.
 */
public interface StockItemFormStrategy {

    public void onSubmit(StockItem stockItem);
}
