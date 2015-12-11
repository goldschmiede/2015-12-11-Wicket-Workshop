package com.anderscore.stockitems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.devutils.debugbar.DebugBar;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
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
    private StockItem currentStockItem = new StockItem();


    @SuppressWarnings("serial")
	public StockItemsPage(final PageParameters parameters) {
        super(parameters);
        
        add(new DebugBar("debug"));
        
       
        this.stockItemsTable = new WebMarkupContainer("stockItemsTable");
        IModel<? extends List<StockItem>> items = new LoadableDetachableModel<List<StockItem>>() {

			@Override
			protected List<StockItem> load() {
				return stockItems;
			}
		};

//        this.listView = new ListView<StockItem>("stockItems", new ListModel<StockItem>(stockItems)) 
//        this.listView = new PropertyListView<StockItem>("stockItems", new ListModel<StockItem>(stockItems)) {
        	this.listView = new PropertyListView<StockItem>("stockItems", items) {
            @Override
            protected void populateItem(final ListItem<StockItem> item) {
            	
//                item.add(new Label("id", new PropertyModel<StockItem>(item.getModel(), "id")));
//                item.add(new Label("name", new PropertyModel<StockItem>(item.getModel(), "name")));
//                item.add(new Label("quantity", new PropertyModel<StockItem>(item.getModel(), "quantity")));
//                item.add(new Label("storageArea", new PropertyModel<StockItem>(item.getModel(), "storageArea")));
//                item.add(new Label("productionDate", new PropertyModel<StockItem>(item.getModel(), "productionDate")));
//                item.add(new Label("batch", new PropertyModel<StockItem>(item.getModel(), "batch")));
            	
                item.add(new Label("id"));
                // Wicket stuff 
//                item.add(new Label("name", model(on(item.getModel()).getName())));
                // Wicket 8
//                item.add(new Label("quantity", new LambdaModel(StockItem::getName())));
                item.add(new Label("name"));
                item.add(new Label("quantity"));
                item.add(new Label("storageArea"));
                item.add(new Label("productionDate"));
                item.add(new Label("batch"));


                item.add(new AjaxLink("editButton"){
                    @Override
                    public void onClick(AjaxRequestTarget target)
                    {
//                        editStockItemModal.updateContent(target, item.getModelObject());
                    	currentStockItem = item.getModelObject();
                        editStockItemModal.show(target);
                    }
                });

                item.add(new AjaxLink("deleteButton") {

                    @Override
                    public void onClick(AjaxRequestTarget target) {
                    	
                        stockItems.remove(item.getModelObject());
                        listView.getModel().detach();
                        target.add(stockItemsTable);
                    }
                });
            
            }
            
        };
        this.stockItemsTable.setOutputMarkupId(true);
        this.stockItemsTable.add(listView);

        add(stockItemsTable);
        add(this.editStockItemModal = new StockItemModal("editStockItemModal", new PropertyModel<StockItem>(this, "currentStockItem"), new EditStockItemFormStrategy(), stockItemsTable) {
        	@Override
        	protected void onChanged(AjaxRequestTarget target) {
        		target.add(stockItemsTable);
        	}
        });
        editStockItemModal.header(Model.<String>of("Bearbeiten"));


        add(this.addStockItemModal = new StockItemModal("addStockItemModal", new PropertyModel<StockItem>(this, "currentStockItem"), new NewStockItemFormStrategy(stockItems), stockItemsTable) {
        	@Override
        	protected void onChanged(AjaxRequestTarget target) {
        		target.add(stockItemsTable);
        	}
        }); 
        addStockItemModal.header(Model.<String>of("Hinzufügen"));
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
            	currentStockItem = new StockItem();
//                addStockItemModal.updateContent(target, new StockItem());
                addStockItemModal.show(target);
            }
        });
	}
}
