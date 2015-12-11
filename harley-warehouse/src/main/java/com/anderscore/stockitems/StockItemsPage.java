package com.anderscore.stockitems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.anderscore.authenticate.AuthenticatedPage;
import com.anderscore.model.StockItem;
import com.googlecode.wicket.jquery.ui.markup.html.link.AjaxLink;

/**
 * Created by pmoebius on 07.12.2015.
 */
public class StockItemsPage extends AuthenticatedPage {
	
	private static final long serialVersionUID = 6729342242088892987L;

	//Long id, String name, Integer quantity, String storageArea, DateTime productionDate, String chargeNumber
    public static final List<StockItem> stockItems = new ArrayList<StockItem>(Arrays.asList(
            new StockItem(12311L, "Lighting HF500", 3, "A", new Date(), "C1"),
            new StockItem(35323L, "Lighting AC200", 5, "A", new Date(), "C44"),
            new StockItem(13245L, "Lighting FG", 1, "A", new Date(), "C24"),
            new StockItem(21431L, "Lighting AC201", 0, "A", new Date(), "C2"),
            new StockItem(99999L, "Lighting UL55", 10, "A", new Date(), "CA4"),

            new StockItem(52499L, "Ignition QuickStart 55", 9, "B", new Date(), "C66"),
            new StockItem(12509L, "Ignition FlameBoost", 2, "B", new Date(), "C90"),
            new StockItem(37509L, "Ignition XXX", 0, "B", new Date(), "A0"))
    );

    private final WebMarkupContainer stockItemsTable;
	private final ListView<StockItem> listView;
    private final StockItemModal editStockItemModal;
    private final StockItemModal addStockItemModal;


    @SuppressWarnings("serial")
	public StockItemsPage(final PageParameters parameters) {
        super(parameters);

        this.stockItemsTable = new WebMarkupContainer("stockItemsTable");
//        this.listView = new ListView<StockItem>("stockItems", new ListModel<StockItem>(stockItems)) 
        this.listView = new PropertyListView<StockItem>("stockItems", new ListModel<StockItem>(stockItems)) {
            @Override
            protected void populateItem(final ListItem<StockItem> item) {
            	
//                item.add(new Label("id", new PropertyModel<StockItem>(item.getModel(), "id")));
//                item.add(new Label("name", new PropertyModel<StockItem>(item.getModel(), "name")));
//                item.add(new Label("quantity", new PropertyModel<StockItem>(item.getModel(), "quantity")));
//                item.add(new Label("storageArea", new PropertyModel<StockItem>(item.getModel(), "storageArea")));
//                item.add(new Label("productionDate", new PropertyModel<StockItem>(item.getModel(), "productionDate")));
//                item.add(new Label("batch", new PropertyModel<StockItem>(item.getModel(), "batch")));
            	
                item.add(new Label("id"));
                item.add(new Label("name"));
                item.add(new Label("quantity"));
                item.add(new Label("storageArea" ));
                item.add(new Label("productionDate"));
                item.add(new Label("batch"));


                item.add(new AjaxLink("editButton"){
                    @Override
                    public void onClick(AjaxRequestTarget target)
                    {
                        editStockItemModal.updateContent(target, item.getModelObject());
                        editStockItemModal.show(target);
                    }
                });

                item.add(new AjaxLink("deleteButton") {

                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        stockItems.remove(item.getModelObject());
                        target.add(stockItemsTable);
                    }
                });
            
            }
            
        };
        this.stockItemsTable.setOutputMarkupId(true);
        this.stockItemsTable.add(listView);

        add(stockItemsTable);
        add(this.editStockItemModal = new StockItemModal("editStockItemModal", new EditStockItemFormStrategy(), stockItemsTable));

        add(this.addStockItemModal = new StockItemModal("addStockItemModal", new NewStockItemFormStrategy(stockItems), stockItemsTable));
//
//        addStockItemModal = new ModalWindow("addStockItemModal");
//        addStockItemModal.setCookieName("addStockItemModalCookie");
//        addStockItemModal.setPageCreator(new ModalWindow.PageCreator()
//        {
//            public Page createPage()
//            {
//                return new AddStockItemPage(addStockItemModal, stockItems);
//            }
//        });
//
//        add(addStockItemModal);
        
//		Link link = new Link("addButton") {
//			@Override
//
//			public void onClick() {
//				setResponsePage(new AddStockItemPage(addStockItemModal, stockItems));
//			}
//		};
//
//		add(link);
		
		add(new AjaxLink<Void>("addButtonModal")
        {
            @Override
            public void onClick(AjaxRequestTarget target)
            {
                addStockItemModal.updateContent(target, new StockItem());
                addStockItemModal.show(target);
            }
        });
	}
}
