package com.anderscore.stockitems;

import com.anderscore.StockItem;
import com.anderscore.authenticate.AuthenticatedPage;
import com.googlecode.wicket.jquery.ui.form.button.Button;
import com.googlecode.wicket.jquery.ui.markup.html.link.AjaxLink;
import com.googlecode.wicket.jquery.ui.markup.html.link.Link;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapLink;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.validation.SimpleMessageValidation;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.joda.time.DateTime;

import java.util.Arrays;
import java.util.List;

/**
 * Created by pmoebius on 07.12.2015.
 */
public class StockItemsPage extends AuthenticatedPage {

    public StockItemsPage(final PageParameters parameters) {
        super(parameters);

        final SimpleMessageValidation validation = new SimpleMessageValidation();
        validation.getConfig().appendToParent(true);
        add(validation);

        final ModalWindow modal;
        add(modal = new ModalWindow("modalWindow"));

        //Long id, String name, Integer quantity, String storageArea, DateTime productionDate, String chargeNumber
        final List<StockItem> stockItems = Arrays.asList(
                new StockItem(12311L, "Lighting HF500", 3, "A", DateTime.now(), "C1"),
                new StockItem(35323L, "Lighting AC200", 5, "A", DateTime.now(), "C44"),
                new StockItem(13245L, "Lighting FG", 1, "A", DateTime.now(), "C24"),
                new StockItem(21431L, "Lighting AC201", 0, "A", DateTime.now(), "C2"),
                new StockItem(99999L, "Lighting UL55", 10, "A", DateTime.now(), "CA4"),

                new StockItem(52499L, "Ignition QuickStart 55", 9, "B", DateTime.now(), "C66"),
                new StockItem(12509L, "Ignition FlameBoost", 2, "B", DateTime.now(), "C90"),
                new StockItem(37509L, "Ignition XXX", 0, "B", DateTime.now(), "A0")
        );

        add(new ListView<StockItem>("stockItems", new ListModel<StockItem>(stockItems)) {
            @Override
            protected void populateItem(ListItem<StockItem> item) {
                item.add(new Label("id", new PropertyModel(item.getModelObject(), "id")));
                item.add(new Label("name", new PropertyModel(item.getModelObject(), "name")));
                item.add(new Label("quantity", new PropertyModel(item.getModelObject(), "quantity")));
                item.add(new Label("storageArea", new PropertyModel(item.getModelObject(), "storageArea")));
                item.add(new Label("productionDate", new PropertyModel(item.getModelObject(), "productionDate")));
                item.add(new Label("batch", new PropertyModel(item.getModelObject(), "batch")));

                final StockItem stockItem = item.getModelObject();

                //final ModalWindow modalWindow = new ModalWindow("modalWindow");
                //Label label = new Label(modalWindow.getContentId(), "I'm a modal window!");

                //Scheinbar egal, welchen Button (wicket vs jquery), da Bootstrap greift.
                item.add(new AjaxLink("newButton"){
                    @Override
                    public void onClick(AjaxRequestTarget target)
                    {
                        modal.setContent(new StockItemModal(modal.getContentId(), stockItem));
                        modal.setTitle("Edit stock item");

                        modal.show(target);
                    }
                });

                item.add(new Button("editButton") {

                    @Override
                    public void onSubmit() {
                        super.onSubmit();
                    }
                });
                item.add(new Button("deleteButton") {

                    @Override
                    public void onSubmit() {
                        stockItems.remove(stockItem);
                        setResponsePage(StockItemsPage.class);
                    }
                });
            }
        });
    }


    }
