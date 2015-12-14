package com.anderscore.stockitems;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.core.request.handler.IPartialPageRequestHandler;
import org.apache.wicket.model.IModel;

import com.anderscore.model.StockItem;

import de.agilecoders.wicket.core.markup.html.bootstrap.dialog.Modal;

/**
 * Created by pmoebius on 09.12.2015.
 */
public class StockItemModal extends Modal<StockItem> {

    private StockItemModalStrategy strategy;

    public StockItemModal(String id, IModel<StockItem> stockItem, StockItemModalStrategy strategy) {
        super(id, stockItem);

        this.strategy = strategy;
        add(new StockItemPanel("content", stockItem, this));
    }

    public StockItemModal(String id, IModel<StockItem> stockItem) {
        this(id, stockItem, StockItemModalStrategy.NOOP);
    }

    protected void onSubmit(AjaxRequestTarget target){
        strategy.onSubmit(getModelObject());
        close(target);
        onChanged(target);
    }
    
    protected void onChanged(AjaxRequestTarget target) {
	}

	@Override
    public Modal<StockItem> show(IPartialPageRequestHandler target) {
    	target.add(this);
    	return super.show(target);
    }

    public StockItemModalStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(StockItemModalStrategy strategy) {
        this.strategy = strategy;
    }
}
