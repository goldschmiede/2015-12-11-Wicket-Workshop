package com.anderscore.stockitems;

import com.anderscore.model.StockItem;

import java.io.Serializable;

/**
 * Created by dkraemer on 10.12.15.
 */
public interface StockItemFormStrategy extends Serializable{

    public void onSubmit(StockItem stockItem);
}
