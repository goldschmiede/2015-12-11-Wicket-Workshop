package com.anderscore.persistence;

import com.anderscore.model.StockItem;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;

import java.util.*;

/**
 * Created by dkraemer on 14.12.15.
 */
public class SimpleStockItemDAO extends StockItemDAO {

    //Long id, String name, Integer quantity, String storageArea, DateTime productionDate, String chargeNumber
    private static final List<StockItem> STOCK_ITEMS = new ArrayList<>(Arrays.asList(
            new StockItem(12311L, "Lighting HF500", 3, "A", new Date(), "C1"),
            new StockItem(35323L, "Lighting AC200", 5, "A", new Date(), "C44"),
            new StockItem(13245L, "Lighting FG", 1, "A", new Date(), "C24"),
            new StockItem(21431L, "Lighting AC201", 0, "A", new Date(), "C2"),
            new StockItem(99999L, "Lighting UL55", 10, "A", new Date(), "CA4"),

            new StockItem(52499L, "Ignition QuickStart 55", 9, "B", new Date(), "C66"),
            new StockItem(12509L, "Ignition FlameBoost", 2, "B", new Date(), "C90"),
            new StockItem(37509L, "Ignition XXX", 0, "B", new Date(), "A0"))
    );

    public SimpleStockItemDAO() {
    }

    @Override
    public StockItem getById(Long id) {
        for (StockItem stockItem : STOCK_ITEMS) {
            Long stockItemId = stockItem.getId();
            if (stockItemId != null && stockItemId.equals(id)) {
                return stockItem;
            }
        }
        return null;
    }

    @Override
    public void create(StockItem stockItem) {
        STOCK_ITEMS.add(stockItem);
    }

    @Override
    public void update(StockItem stockItem) {
        // NOOP
    }

    @Override
    public void delete(StockItem stockItem) {
        STOCK_ITEMS.remove(stockItem);
    }

    @Override
    public List<StockItem> getAll() {
        List<StockItem> stockItems = new ArrayList<>();
        stockItems.addAll(STOCK_ITEMS);
        return stockItems;
    }

    @Override
    public List<StockItem> getRange(long start, long end) {
        if (start <= Integer.MAX_VALUE && end <= Integer.MAX_VALUE) {
            return STOCK_ITEMS.subList((int) start, (int) end);
        } else {
            return null;
        }
    }

    @Override
    public List<StockItem> getRangeSorted(long start, long end, SortParam<String> sortParam) {
        List<StockItem> stockItems = new ArrayList<>(STOCK_ITEMS);
        Comparator comparator = getComparator(sortParam);

        if (comparator != null) {
            Collections.sort(stockItems, comparator);
        }

        if (start <= Integer.MAX_VALUE && end <= Integer.MAX_VALUE) {
            return stockItems.subList((int) start, (int) end);
        } else {
            return null;
        }
    }



    @Override
    public long countAll() {
        return STOCK_ITEMS.size();
    }
}
