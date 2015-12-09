package com.anderscore.stockitems;

import com.anderscore.model.StockItem;
import de.agilecoders.wicket.core.markup.html.bootstrap.dialog.Modal;
import org.apache.wicket.model.IModel;

/**
 * Created by pmoebius on 09.12.2015.
 */
public class StockItemModal extends Modal<StockItem> {

    public StockItemModal(String id) {
        super(id);
    }

    public void setContent(StockItemPanel stockItemPanel) {
        addOrReplace(stockItemPanel.withAjax());
    }
}
