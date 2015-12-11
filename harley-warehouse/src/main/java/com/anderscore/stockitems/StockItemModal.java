package com.anderscore.stockitems;

import com.anderscore.model.StockItem;
import de.agilecoders.wicket.core.markup.html.bootstrap.dialog.Modal;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
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
        this(id, new StockItem(), strategy, table);
    }

    public void updateContent(AjaxRequestTarget target, StockItem stockItem) {
    	target.add(this);
        modalPanel.updateStockItemForm(stockItem);
    }
}
