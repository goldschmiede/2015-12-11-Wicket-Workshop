package com.anderscore.stockitems;

import java.util.List;

import com.anderscore.stockitems.modal.AddStockItemModalStrategy;
import com.anderscore.stockitems.modal.EditStockItemModalStrategy;
import com.anderscore.stockitems.modal.StockItemModal;
import com.anderscore.persistence.SimpleStockItemDAO;
import com.anderscore.persistence.StockItemDAO;
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

    private final StockItemDAO stockItemDAO;
    private StockItem currentStockItem;

    private final WebMarkupContainer stockItemsTable;
    private final ListView<StockItem> listView;
    private final StockItemModal stockItemModal;


    @SuppressWarnings("serial")
    public StockItemsPage(final PageParameters parameters) {
        super(parameters);

        this.stockItemDAO = new SimpleStockItemDAO();
        this.currentStockItem = new StockItem();

        add(new DebugBar("debug"));

        add(new AjaxLink<Void>("addButton") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                currentStockItem = new StockItem();
                stockItemModal.header(Model.of("Add new stock item"));
                stockItemModal.setStrategy(new AddStockItemModalStrategy(stockItemDAO.getStockItems()));
                stockItemModal.show(target);
            }
        });

        IModel<? extends List<StockItem>> items = new LoadableDetachableModel<List<StockItem>>() {

            @Override
            protected List<StockItem> load() {
                return stockItemDAO.getStockItems();
            }
        };

        this.listView = new PropertyListView<StockItem>("stockItems", items) {
            @Override
            protected void populateItem(final ListItem<StockItem> item) {
                item.add(new Label("id"));
                // Wicket stuff
                // item.add(new Label("name", model(on(item.getModel()).getName())));
                // Wicket 8
                // item.add(new Label("name", new LambdaModel(StockItem::getName())));
                item.add(new Label("name"));
                item.add(new Label("quantity"));
                item.add(new Label("storageArea"));
                item.add(new Label("productionDate"));
                item.add(new Label("batch"));

                item.add(new AjaxLink("editButton") {
                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        currentStockItem = item.getModelObject();
                        stockItemModal.header(Model.of("Edit stock item"));
                        stockItemModal.setStrategy(new EditStockItemModalStrategy());
                        stockItemModal.show(target);
                    }
                });

                item.add(new AjaxLink("deleteButton") {

                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        stockItemDAO.delete(item.getModelObject());
                        listView.getModel().detach();
                        target.add(stockItemsTable);
                    }
                });
            }
        };
        this.stockItemsTable = new WebMarkupContainer("stockItemsTable");
        stockItemsTable.setOutputMarkupId(true);
        stockItemsTable.add(listView);
        add(stockItemsTable);

        add(this.stockItemModal = new StockItemModal("stockItemModal", new PropertyModel<StockItem>(this, "currentStockItem"), items) {
            @Override
            protected void onChanged(AjaxRequestTarget target) {
                target.add(stockItemsTable);
            }
        });
    }
}
