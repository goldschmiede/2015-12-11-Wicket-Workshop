package com.anderscore.stockitems;

import com.anderscore.model.StockItem;
import de.agilecoders.wicket.core.markup.html.bootstrap.dialog.Modal;
import org.joda.time.DateTime;

/**
 * Created by pmoebius on 09.12.2015.
 */
public class StockItemModal extends Modal<StockItem> {

    private StockItemPanel modalPanel;

    public StockItemModal(String id, StockItem stockItem, StockItemFormStrategy strategy) {
        super(id);
        add(this.modalPanel = new StockItemPanel("content", stockItem, strategy));
    }

    public StockItemModal(String id, StockItemFormStrategy strategy) {
        this(id, new StockItem(1L, "Dummy", 1, "A", DateTime.now(), "1"), strategy);
    }

    public void updateContent(StockItem stockItem) {
        modalPanel.updateStockItemForm(stockItem);
    }
}
