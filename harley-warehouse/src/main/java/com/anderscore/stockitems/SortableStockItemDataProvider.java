package com.anderscore.stockitems;

import com.anderscore.model.StockItem;
import com.anderscore.model.StockItemModel;
import com.anderscore.persistence.PersistenceFacade;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;

import java.util.Iterator;
import java.util.List;

/**
 * Created by dkraemer on 15.12.15.
 */
public class SortableStockItemDataProvider extends SortableDataProvider<StockItem, String> {

    public SortableStockItemDataProvider() {
        setSort("id", SortOrder.ASCENDING);
    }

    @Override
    public Iterator<? extends StockItem> iterator(long l, long l1) {
        List<StockItem> stockItems = PersistenceFacade.getStockItemDAO().getRangeSorted(l, l + l1, getSort());

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
