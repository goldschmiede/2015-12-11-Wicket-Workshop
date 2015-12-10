package com.anderscore.stockitems;

import com.anderscore.model.StockItem;
import de.agilecoders.wicket.core.markup.html.bootstrap.dialog.Modal;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.joda.time.DateTime;

/**
 * Created by pmoebius on 09.12.2015.
 */
public class StockItemModal extends Modal<StockItem> {

    private StockItemPanel modalPanel;

    public StockItemModal(String id, StockItemFormStrategy strategy, WebMarkupContainer container) {
        super(id);
        modalPanel = new StockItemPanel("content", new StockItem(), strategy, container);
        add(modalPanel);
    }

    public void updateContent(StockItem stockItem) {
        modalPanel.updateStockItemForm(stockItem);
    }
}
