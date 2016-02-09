package com.anderscore.persistence;

import com.anderscore.model.StockItem;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.SessionFactoryImpl;

import java.util.*;

/**
 * Created by Max on 05.02.2016.
 */
public class HibernateStockItemDAO extends StockItemDAO {

    Session session;
    private final SessionFactory sessionFactory;


    public HibernateStockItemDAO() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibternate.dialect", "org.hibernate.dialect.HSQLDialect");
        hibernateProperties.put("hibernate.show_sql", true);
        hibernateProperties.put("hibernate.hbm2ddl.auto", "create");
        hibernateProperties.put("hibernate.connection.driver_class", "org.hsqldb.jdbcDriver");
        hibernateProperties.put("hibernate.connection.url", "jdbc:hsqldb:hsql://localhost/testdb");
        hibernateProperties.put("hibernate.connection.username", "sa");
        hibernateProperties.put("hibernate.connection.password", "");
        hibernateProperties.put("hibernate.connection.pool_size", 2);
        hibernateProperties.put("hibernate.connection.autocommit", false);
        hibernateProperties.put("hibernate.dbcp.initalSize",8);
        hibernateProperties.put("hibernate.dbcp.maxActive",20);
        hibernateProperties.put("hibernate.dbcp.maxIdle",20);
        hibernateProperties.put("hibernate.dbcp.minIdle",0);
        hibernateProperties.put("hibernate.datasource","org.apache.commons.dbcp.BasicDataSource");

        sessionFactory = new Configuration()
                .addAnnotatedClass(StockItem.class)
                .addProperties(hibernateProperties)
                .buildSessionFactory();

        addDummyContentFromSimpleDAO();
    }

    @Override
    public StockItem getById(Long id) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from StockItem where id= :id");
        query.setParameter("id", id);
        StockItem stockItem = (StockItem) query.uniqueResult();
        session.close();
        return stockItem;
    }

    @Override
    public void create(StockItem stockItem) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(stockItem);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(StockItem stockItem) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(stockItem);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(StockItem stockItem) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(stockItem);
        transaction.commit();
        session.close();
    }

    @Override
    public List<StockItem> getAll() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from StockItem");
        List list = query.list();
        session.close();
        return list;
    }

    @Override
    public List<StockItem> getRange(long start, long end) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from StockItem ");
        List list = query.list();
        session.close();
        if (start <= Integer.MAX_VALUE && end <= Integer.MAX_VALUE) {
            return list.subList((int) start, (int) end);
        } else {
            return null;
        }

    }

    @Override
    public List<StockItem> getRangeSorted(long start, long end, SortParam<String> sortParam) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from StockItem ");
        List<StockItem> stockItems = new ArrayList<>(query.list());
        session.close();
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
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from StockItem ");
        Integer count = query.list().size();
        session.close();
        return count;
    }

    private void addDummyContentFromSimpleDAO() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<StockItem> stockItems = new SimpleStockItemDAO().getAll();
        for(StockItem stockItem : stockItems) {
            session.save(stockItem);
        }
        transaction.commit();
    }
}
