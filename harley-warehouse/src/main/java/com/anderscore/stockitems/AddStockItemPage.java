package com.anderscore.stockitems;

import java.util.List;

import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;

import com.anderscore.authenticate.AuthenticatedPage;
import com.anderscore.model.StockItem;

/**
 * Created by dkraemer on 08.12.15.
 */
public class AddStockItemPage extends AuthenticatedPage {

	private static final long serialVersionUID = 6884760133844113941L;

    public AddStockItemPage(String id, List<StockItem> stockItems)
    {  
        add(new FeedbackPanel("feedback"));
        StockItem stockItem = new StockItem();
        add(new AddStockItemForm("addStockItemForm", stockItem, stockItems));
    }

    class AddStockItemForm extends Form<StockItem>
    {
		private static final long serialVersionUID = -2967242861401242317L;
		private StockItem stockItem;
		private List<StockItem> stockItems;

		public AddStockItemForm(final String id, StockItem stockItem, final List<StockItem> stockItems)
        {
            super(id, new CompoundPropertyModel<>(stockItem));
            this.stockItem = stockItem;
            this.stockItems = stockItems;
            
           
            
            
            add(new TextField<>("name"));
            add(new TextField<>("quantity"));
            add(new TextField<>("storageArea"));
//            add(new DateTextField("productionDate"));
            add(new TextField<>("batch"));
            
            
        }

        @Override
        protected void onSubmit()
        {
            System.out.println("TestAddStockItemPage");
            stockItems.add(stockItem);
            
            setResponsePage(StockItemsPage.class);
        }
    }
}
