package com.anderscore.persistence;

import com.anderscore.model.StockItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by dkraemer on 14.12.15.
 */
public class SimpleStockItemDAO implements StockItemDAO{

    private final List<StockItem> stockItems;

    public SimpleStockItemDAO(){
        //Long id, String name, Integer quantity, String storageArea, DateTime productionDate, String chargeNumber
        this.stockItems = new ArrayList<>(Arrays.asList(
                new StockItem(12311L, "Lighting HF500", 3, "A", new Date(), "C1"),
                new StockItem(35323L, "Lighting AC200", 5, "A", new Date(), "C44"),
                new StockItem(13245L, "Lighting FG", 1, "A", new Date(), "C24"),
                new StockItem(21431L, "Lighting AC201", 0, "A", new Date(), "C2"),
                new StockItem(99999L, "Lighting UL55", 10, "A", new Date(), "CA4"),

                new StockItem(52499L, "Ignition QuickStart 55", 9, "B", new Date(), "C66"),
                new StockItem(12509L, "Ignition FlameBoost", 2, "B", new Date(), "C90"),
                new StockItem(37509L, "Ignition XXX", 0, "B", new Date(), "A0"))
        );
    }

    @Override
    public List<StockItem> getStockItems() {
        return stockItems;
    }

    @Override
    public void delete(StockItem stockItem) {
        stockItems.remove(stockItem);
    }
}
