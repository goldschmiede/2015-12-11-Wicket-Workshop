package com.anderscore.stockitems;

import java.util.List;

import com.anderscore.model.StockItemsModel;
import com.anderscore.persistence.PersistenceFacade;
import com.anderscore.stockitems.modal.AddStockItemModalStrategy;
import com.anderscore.stockitems.modal.EditStockItemModalStrategy;
import com.anderscore.stockitems.modal.StockItemModal;
import com.anderscore.persistence.SimpleStockItemDAO;
import com.anderscore.persistence.StockItemDAO;
import de.agilecoders.wicket.core.markup.html.bootstrap.navigation.ajax.BootstrapAjaxPagingNavigator;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.devutils.debugbar.DebugBar;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.*;
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

    private StockItem currentStockItem;

    private final WebMarkupContainer stockItemsTable;
    private final DataView<StockItem> dataView;
    private final StockItemModal stockItemModal;


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

        this.dataView = new DataView<StockItem>("stockItems", new StockItemDataProvider()) {

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
        add(stockItemsTable);

        add(new BootstrapAjaxPagingNavigator("pagingNavigator", dataView));

        add(this.stockItemModal = new StockItemModal("stockItemModal", new PropertyModel<StockItem>(this, "currentStockItem"), new StockItemsModel()) {
            @Override
            protected void onChanged(AjaxRequestTarget target) {
                target.add(stockItemsTable);
            }
        });
    }
}
