package com.anderscore.stockitems.modal;

import com.anderscore.model.StockItem;
import com.anderscore.persistence.PersistenceFacade;
import com.anderscore.persistence.SimpleStockItemDAO;
import com.anderscore.persistence.StockItemDAO;

import java.util.List;

/**
 * Created by dkraemer on 10.12.15.
 */
public class AddStockItemModalStrategy implements StockItemModalStrategy {

    @Override
    public void onSubmit(StockItem stockItem) {
        PersistenceFacade.getStockItemDAO().create(stockItem);
    }
}
