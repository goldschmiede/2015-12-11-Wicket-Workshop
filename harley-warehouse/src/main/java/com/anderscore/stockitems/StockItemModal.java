package com.anderscore.stockitems;

import com.anderscore.model.StockItem;
import de.agilecoders.wicket.core.markup.html.bootstrap.dialog.Modal;
import org.apache.wicket.MarkupContainer;
import org.joda.time.DateTime;

/**
 * Created by pmoebius on 09.12.2015.
 */
public class StockItemModal extends Modal<StockItem> {

    private StockItemPanel modalPanel;

    public StockItemModal(String id, StockItem stockItem, StockItemFormStrategy strategy, MarkupContainer table) {
        super(id);
        add(this.modalPanel = new StockItemPanel("content", stockItem, strategy, this, table));
    }

    public StockItemModal(String id, StockItemFormStrategy strategy, MarkupContainer table) {
        this(id, new StockItem(1L, "Dummy", 1, "A", DateTime.now(), "1"), strategy, table);
    }

    public void updateContent(StockItem stockItem) {
        modalPanel.updateStockItemForm(stockItem);
    }
}
