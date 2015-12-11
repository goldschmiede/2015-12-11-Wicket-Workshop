package com.anderscore.stockitems;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.core.request.handler.IPartialPageRequestHandler;
import org.apache.wicket.model.IModel;

import com.anderscore.model.StockItem;

import de.agilecoders.wicket.core.markup.html.bootstrap.dialog.Modal;

/**
 * Created by pmoebius on 09.12.2015.
 */
public class StockItemModal extends Modal<StockItem> {

    private StockItemPanel modalPanel;

    public StockItemModal(String id, IModel<StockItem> stockItem, StockItemFormStrategy strategy, MarkupContainer table) {
        super(id);
        this.modalPanel = new StockItemPanel("content", stockItem, strategy, this) {

			@Override
			protected void onChanged(AjaxRequestTarget target) {
				StockItemModal.this.onChanged(target);
				
			}
        };
        add(modalPanel);
    }
    
    protected void onChanged(AjaxRequestTarget target) {
		// TODO Auto-generated method stub
		
	}

	@Override
    public Modal<StockItem> show(IPartialPageRequestHandler target) {
    	target.add(this);
    	return super.show(target);
    }

//    public StockItemModal(String id, StockItemFormStrategy strategy, MarkupContainer table) {
//        this(id, new StockItem(), strategy, table);
//    }

//    public void updateContent(AjaxRequestTarget target, StockItem stockItem) {
//    	target.add(this);
//        modalPanel.updateStockItemForm(stockItem);
//    }
}
