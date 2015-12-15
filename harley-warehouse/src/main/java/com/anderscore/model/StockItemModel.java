package com.anderscore.model;

import com.anderscore.persistence.PersistenceFacade;
import org.apache.wicket.model.LoadableDetachableModel;

/**
 * Created by dkraemer on 15.12.15.
 */
public class StockItemModel extends LoadableDetachableModel<StockItem>{

    private Long id;

    public StockItemModel(StockItem stockItem){
        this.id = stockItem.getId();
    }

    @Override
    protected StockItem load() {
        return PersistenceFacade.getStockItemDAO().getById(id);
    }
}
