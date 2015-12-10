package com.anderscore.stockitems;

import com.googlecode.wicket.jquery.ui.markup.html.link.AjaxLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;

import com.anderscore.model.StockItem;

/**
 * Created by dkraemer on 08.12.15.
 */
public class StockItemPanel extends Panel {


    private EditStockItemForm form;
    private StockItemModal modal;

    /**
     * @param id
     */
    public StockItemPanel(String id, final StockItem stockItem, final StockItemFormStrategy strategy, final StockItemModal modal, final MarkupContainer table)
    {
        super(id);

        add(this.form = new EditStockItemForm("stockItemForm", stockItem, modal, strategy, table));
        this.form.setOutputMarkupId(true);
        add(new FeedbackPanel("feedback"));
        this.modal = modal;

        this.form.add(new AjaxFormSubmitBehavior("submit") {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onError(AjaxRequestTarget target) {
                target.add(form);
            }

            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                strategy.onSubmit(form.getStockItem());
                target.add(form);
                StockItemPanel.this.onSubmit(target);

            }
        });
    }

    //TODO workaround? Check in detail...
    protected void onSubmit(AjaxRequestTarget target) {
    }

    public void updateStockItemForm(StockItem stockItem) {
        form.setStockItem(stockItem);
    }

    static public final class EditStockItemForm extends BootstrapForm<StockItem>
    {

        public EditStockItemForm(final String id, final StockItem stockItem, final StockItemModal modal, final StockItemFormStrategy strategy, final MarkupContainer table)
        {
            super(id, new CompoundPropertyModel<>(stockItem));
            
            add(new TextField<>("id"));
            add(new TextField<>("name"));
            add(new TextField<>("quantity"));
            add(new TextField<>("storageArea"));
            //add(new TextField<>("productionDate"));
            add(new TextField<>("batch"));
            add(new AjaxSubmitLink("submitButton"){
                @Override
                protected void onSubmit(AjaxRequestTarget target, Form form){
                    strategy.onSubmit(getStockItem());
                    modal.close(target);
                    target.add(table);
                }
            });
        }

        public void setStockItem(StockItem stockItem){
            getModel().setObject(stockItem);
        }

        public StockItem getStockItem(){
            return getModel().getObject();
        }
    }
}
