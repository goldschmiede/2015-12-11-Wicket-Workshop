package com.anderscore.model;

import com.anderscore.persistence.PersistenceFacade;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.model.LoadableDetachableModel;

import java.util.List;

/**
 * Created by dkraemer on 15.12.15.
 */
public class StockItemsModel extends LoadableDetachableModel<List<StockItem>> {
    @Override
    protected List<StockItem> load() {
        return PersistenceFacade.getStockItemDAO().getAll();
    }
}
