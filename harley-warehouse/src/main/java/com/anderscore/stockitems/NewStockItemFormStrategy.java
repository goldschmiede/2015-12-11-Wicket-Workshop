package com.anderscore.stockitems;

import com.anderscore.model.StockItem;

import java.util.List;

/**
 * Created by dkraemer on 10.12.15.
 */
public class NewStockItemFormStrategy implements StockItemFormStrategy{

    private List<StockItem> itemList;

    public NewStockItemFormStrategy(List<StockItem> itemList){
        this.itemList = itemList;
    }

    @Override
    public void onSubmit(StockItem stockItem) {
        itemList.add(stockItem);
    }
}
