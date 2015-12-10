package com.anderscore.stockitems;

import com.anderscore.model.StockItem;
import de.agilecoders.wicket.core.markup.html.bootstrap.dialog.Modal;
import org.joda.time.DateTime;

/**
 * Created by pmoebius on 09.12.2015.
 */
public class EditStockItemModal extends Modal<StockItem> {

    private EditStockItemPanel modalPanel;

    public EditStockItemModal(String id, StockItem stockItem) {
        super(id);
        add(this.modalPanel = new EditStockItemPanel("content", stockItem));
    }

    public EditStockItemModal(String id) {
        this(id, new StockItem(1L, "Dummy", 1, "A", DateTime.now(), "1"));
    }

    public void updateContent(StockItem stockItem) {
        modalPanel.updateStockItemForm(stockItem);
    }
}
