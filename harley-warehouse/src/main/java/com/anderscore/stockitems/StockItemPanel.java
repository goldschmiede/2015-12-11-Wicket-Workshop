package com.anderscore.stockitems;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;

import com.anderscore.model.StockItem;

import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;

/**
 * Created by dkraemer on 08.12.15.
 */
public class StockItemPanel extends Panel {


    private EditStockItemForm form;
    private final WebMarkupContainer container;

    /**
     * @param id
     */
    public StockItemPanel(String id, final StockItem stockItem, final StockItemFormStrategy strategy, WebMarkupContainer container)
    {
        super(id);
        
        this.container = container;
        
        Label panelTitle = new Label("title", "Test"); 
        this.add(panelTitle);

        add(this.form = new EditStockItemForm("stockItemForm", stockItem));
        add(new FeedbackPanel("feedback"));

        this.form.add(new AjaxFormSubmitBehavior("submit") {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onError(AjaxRequestTarget target) {
                target.add(form);
            }

            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                strategy.onSubmit(form.getStockItem());
                target.add(StockItemPanel.this.container);
                
                ((StockItemModal) StockItemPanel.this.getParent()).close(target);
            }
        });
        
        this.form.add(new AjaxSubmitLink("saveButton")
        {
            @Override
            public void onSubmit(AjaxRequestTarget target, Form form)
            {
            	 strategy.onSubmit(((EditStockItemForm) form).getStockItem());
                 target.add(StockItemPanel.this.container);
               
               ((StockItemModal) StockItemPanel.this.getParent()).close(target);
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

        public EditStockItemForm(final String id, final StockItem stockItem)
        {
            super(id, new CompoundPropertyModel<>(stockItem));
            
            add(new TextField<>("id").setEnabled(false));
            add(new TextField<>("name"));
            add(new TextField<>("quantity"));
            add(new TextField<>("storageArea"));
            //add(new TextField<>("productionDate"));
            add(new TextField<>("batch"));
        }

        @Override
        public final void onSubmit()
        {
        }

        public void setStockItem(StockItem stockItem){
            getModel().setObject(stockItem);
        }

        public StockItem getStockItem(){
            return getModel().getObject();
        }
        
        
    }
}
