package com.anderscore.stockitems;

import com.anderscore.StockItem;
import de.agilecoders.wicket.core.markup.html.bootstrap.tabs.BootstrapTabbedPanel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;

/**
 * Created by dkraemer on 08.12.15.
 */
public class StockItemModal extends Panel {

    /**
     * @param id
     */
    public StockItemModal(String id, StockItem stockItem)
    {
        super(id);
        add(new EditStockItemForm("stockItemForm", stockItem));
    }

    static public final class EditStockItemForm extends Form<StockItem>
    {

        public EditStockItemForm(final String id, final StockItem stockItem)
        {
            super(id, new CompoundPropertyModel<>(stockItem));
            
            add(new TextField<>("id"));
            add(new TextField<>("name"));
            add(new TextField<>("quantity"));
            add(new TextField<>("storageArea"));
            add(new TextField<>("productionDate"));
            add(new TextField<>("batch"));
        }

        @Override
        public final void onSubmit()
        {
            System.out.println("Test");
            setResponsePage(StockItemsPage.class);
        }
    }
}
