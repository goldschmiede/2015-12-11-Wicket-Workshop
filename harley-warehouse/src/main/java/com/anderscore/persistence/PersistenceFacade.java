package com.anderscore.persistence;

/**
 * Created by dkraemer on 15.12.15.
 */
public class PersistenceFacade {

    private static final StockItemDAO STOCK_ITEM_DAO = new HibernateStockItemDAO();

    public static StockItemDAO getStockItemDAO(){
        return STOCK_ITEM_DAO;
    }
}
