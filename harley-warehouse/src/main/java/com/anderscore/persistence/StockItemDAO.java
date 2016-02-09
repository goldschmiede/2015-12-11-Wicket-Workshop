package com.anderscore.persistence;

import com.anderscore.model.StockItem;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

/**
 * Created by dkraemer on 14.12.15.
 */
public abstract class StockItemDAO implements Serializable {

    public abstract StockItem getById(Long id);
    public abstract void create(StockItem stockItem);
    public abstract void update(StockItem stockItem);
    public abstract void delete (StockItem stockItem);
    public abstract List<StockItem> getAll();
    public abstract List<StockItem> getRange(long start, long end);
    public abstract List<StockItem> getRangeSorted(long start, long end, SortParam<String> sortParam);
    public abstract long countAll();

    protected static Comparator<StockItem> getComparator(SortParam<String> sortParam) {
        String sortProperty = sortParam.getProperty();
        boolean ascending = sortParam.isAscending();
        Comparator comparator = null;

        if ("id".equals(sortProperty)) {
            comparator = new Comparator<StockItem>() {
                @Override
                public int compare(StockItem o1, StockItem o2) {
                    return o1.getId().compareTo(o2.getId());
                }
            };
        } else if ("name".equals(sortProperty)) {
            comparator = new Comparator<StockItem>() {
                @Override
                public int compare(StockItem o1, StockItem o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            };
        } else if ("quantity".equals(sortProperty)) {
            comparator = new Comparator<StockItem>() {
                @Override
                public int compare(StockItem o1, StockItem o2) {
                    return o1.getQuantity().compareTo(o2.getQuantity());
                }
            };
        } else if ("storageArea".equals(sortProperty)) {
            comparator = new Comparator<StockItem>() {
                @Override
                public int compare(StockItem o1, StockItem o2) {
                    return o1.getStorageArea().compareTo(o2.getStorageArea());
                }
            };
        } else if ("productionDate".equals(sortProperty)) {
            comparator = new Comparator<StockItem>() {
                @Override
                public int compare(StockItem o1, StockItem o2) {
                    return o1.getProductionDate().compareTo(o2.getProductionDate());
                }
            };
        } else if ("batch".equals(sortProperty)) {
            comparator = new Comparator<StockItem>() {
                @Override
                public int compare(StockItem o1, StockItem o2) {
                    return o1.getBatch().compareTo(o2.getBatch());
                }
            };

        } else {
            return null;
        }

        return ascending ? comparator : comparator.reversed();
    }
}
