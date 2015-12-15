package com.anderscore.stockitems.modal;

import com.anderscore.model.StockItem;
import com.anderscore.persistence.PersistenceFacade;

/**
 * Created by dkraemer on 10.12.15.
 */
public class EditStockItemModalStrategy implements StockItemModalStrategy {

    @Override
    public void onSubmit(StockItem stockItem) {
        PersistenceFacade.getStockItemDAO().update(stockItem);
    }
}
