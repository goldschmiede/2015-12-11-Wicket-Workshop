package com.anderscore.stockitems;

import com.anderscore.model.StockItem;
import com.anderscore.model.StockItemModel;
import com.anderscore.persistence.PersistenceFacade;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;

import java.util.Iterator;
import java.util.List;

/**
 * Created by dkraemer on 15.12.15.
 */
public class StockItemDataProvider implements IDataProvider<StockItem> {
    @Override
    public Iterator<? extends StockItem> iterator(long l, long l1) {
        List<StockItem> stockItems = PersistenceFacade.getStockItemDAO().getRange(l, l + l1);
        return stockItems != null ? stockItems.iterator() : null;
    }

    @Override
    public long size() {
        return PersistenceFacade.getStockItemDAO().countAll();
    }

    @Override
    public IModel<StockItem> model(StockItem stockItem) {
        return new StockItemModel(stockItem);
    }

    @Override
    public void detach() {
    }
}
