package com.anderscore.stockitems;

import com.anderscore.authenticate.AuthenticatedPage;
import com.anderscore.model.StockItem;
import com.anderscore.model.StockItemsModel;
import com.anderscore.persistence.PersistenceFacade;
import com.anderscore.stockitems.modal.AddStockItemModalStrategy;
import com.anderscore.stockitems.modal.EditStockItemModalStrategy;
import com.anderscore.stockitems.modal.StockItemModal;
import com.googlecode.wicket.jquery.ui.markup.html.link.AjaxLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.navigation.ajax.BootstrapAjaxPagingNavigator;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.devutils.debugbar.DebugBar;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortStateLocator;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * Created by pmoebius on 07.12.2015.
 */
public class StockItemsPage extends AuthenticatedPage {

    private static final long serialVersionUID = 6729342242088892987L;
    private final WebMarkupContainer stockItemsTable;
    private final DataView<StockItem> dataView;
    private final StockItemModal stockItemModal;
    private StockItem currentStockItem;


    public StockItemsPage(final PageParameters parameters) {
        super(parameters);

        this.currentStockItem = new StockItem();

        add(new DebugBar("debug"));

        add(new AjaxLink<Void>("addButton") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                currentStockItem = new StockItem();
                stockItemModal.header(Model.of("Add new stock item"));
                stockItemModal.setStrategy(new AddStockItemModalStrategy());
                stockItemModal.show(target);
            }
        });

        SortableStockItemDataProvider stockItemDataProvider = new SortableStockItemDataProvider();

        this.dataView = new DataView<StockItem>("stockItems", stockItemDataProvider) {

            @Override
            protected void populateItem(final Item<StockItem> item) {
                item.setDefaultModel(new CompoundPropertyModel<>(item.getModel()));
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
                        PersistenceFacade.getStockItemDAO().delete(item.getModelObject());
                        target.add(stockItemsTable);
                    }
                });
            }
        };

        dataView.setItemsPerPage(5);

        this.stockItemsTable = new WebMarkupContainer("stockItemsTable");

        stockItemsTable.setOutputMarkupId(true);
        stockItemsTable.add(dataView);

        stockItemsTable.add(createOrderByBorder("id", stockItemDataProvider));
        stockItemsTable.add(createOrderByBorder("name", stockItemDataProvider));

        add(stockItemsTable);

        add(new BootstrapAjaxPagingNavigator("pagingNavigator", dataView));

        add(this.stockItemModal = new StockItemModal("stockItemModal", new PropertyModel<StockItem>(this, "currentStockItem"), new StockItemsModel()) {
            @Override
            protected void onChanged(AjaxRequestTarget target) {
                target.add(stockItemsTable);
            }
        });
    }

    private OrderByBorder<String> createOrderByBorder(String property, ISortStateLocator<String> sortStateLocator) {
        String wicketId = "orderBy" + property.substring(0, 1).toUpperCase() + property.substring(1);
        return new OrderByBorder<String>(wicketId, property, sortStateLocator) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSortChanged() {
                dataView.setCurrentPage(0);
            }
        };
    }
}
