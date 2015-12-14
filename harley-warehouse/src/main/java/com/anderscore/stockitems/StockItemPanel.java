package com.anderscore.stockitems;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import com.anderscore.model.StockItem;
import com.googlecode.wicket.jquery.core.Options;
import com.googlecode.wicket.jquery.ui.form.datepicker.DatePicker;

import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;

/**
 * Created by dkraemer on 08.12.15.
 */
public class StockItemPanel extends Panel {

    private StockItemModal modal;

    /**
     * @param id
     */
    public StockItemPanel(final String id, final IModel<StockItem> stockItem, final StockItemModal modal) {
        super(id, new CompoundPropertyModel<>(stockItem));

        add(new EditStockItemForm("stockItemForm"));
//        this.form.setOutputMarkupId(true);
//        this.setOutputMarkupId(true);
        this.modal = modal;
        add(new FeedbackPanel("feedback"));
    }

    protected void onSubmit(AjaxRequestTarget target) {
        modal.onSubmit(target);
    }

    public final class EditStockItemForm extends BootstrapForm<StockItem> {

        public EditStockItemForm(final String id) {
            super(id);

            add(new TextField<>("id").setEnabled(false));
            add(new TextField<>("name"));
            add(new TextField<>("quantity"));
            add(new TextField<>("storageArea"));
            add(new DatePicker("productionDate", new Options("dateFormat", Options.asString("dd.mm.yy"))).setRequired(true));
            add(new TextField<>("batch"));

            add(new AjaxSubmitLink("submitButton") {
                @Override
                protected void onSubmit(AjaxRequestTarget target, Form form) {
                    StockItemPanel.this.onSubmit(target);
                }

                @Override
                protected void onError(AjaxRequestTarget target, Form<?> form) {
                    super.onError(target, form);
                }
            });
        }
    }
}