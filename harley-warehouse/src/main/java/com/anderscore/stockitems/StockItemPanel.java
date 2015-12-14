package com.anderscore.stockitems;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
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
public abstract class StockItemPanel extends Panel {


    private EditStockItemForm form;
    private StockItemModal modal;

    /**
     * @param id
     */
    public StockItemPanel(String id, final IModel<StockItem> stockItem, final StockItemFormStrategy strategy, final StockItemModal modal)
    {
        super(id);

        add(this.form = new EditStockItemForm("stockItemForm", stockItem, modal, strategy));
//        this.form.setOutputMarkupId(true);
//        this.setOutputMarkupId(true);
        add(new FeedbackPanel("feedback"));
        this.modal = modal;
    }

    //TODO workaround? Check in detail...
    protected void onSubmit(AjaxRequestTarget target) {
    }

    public final class EditStockItemForm extends BootstrapForm<StockItem>
    {

        public EditStockItemForm(final String id, IModel<StockItem> stockItem, final StockItemModal modal, final StockItemFormStrategy strategy)
        {
            super(id, new CompoundPropertyModel<>(stockItem));
            
            add(new TextField<>("id").setEnabled(false));
            add(new TextField<>("name"));
            add(new TextField<>("quantity"));
            add(new TextField<>("storageArea"));
         // Date Picker //
            DatePicker datePicker = new DatePicker("productionDate", new Options("dateFormat", Options.asString("dd.mm.yy")));
            datePicker.setRequired(true);
    		add(datePicker);
            add(new TextField<>("batch"));

            add(new AjaxSubmitLink("submitButton"){
                @Override
                protected void onSubmit(AjaxRequestTarget target, Form form){
                    strategy.onSubmit(EditStockItemForm.this.getModelObject());
                    modal.close(target);
                    onChanged(target);
                }
                
                @Override
                protected void onError(AjaxRequestTarget target, Form<?> form) {
                	super.onError(target, form);
                }
            });
        }

    }
    
    protected abstract void onChanged(AjaxRequestTarget target); 
}
