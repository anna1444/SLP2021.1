package com.ods.learning.model;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class HQLTest {
    private StandardServiceRegistry ssr;
    private Metadata meta;
    private SessionFactory factory;
    private Car car;

    @Before
    public void setup() {
        ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        meta = new MetadataSources(ssr).getMetadataBuilder().build();
        factory = meta.getSessionFactoryBuilder().build();
    }

    @Test
    public void getAllCarsFromDBTest(){
        Session session = factory.openSession();
        Query th = session.createQuery("from Car");
        List<Car> cars = th.list();
        session.close();
        Assert.assertFalse(cars.isEmpty());
    }

    @Test
    public void updateCarModelTest() {
        Session session = factory.openSession();
        Transaction tr = session.beginTransaction();
        Query query = session.createQuery("update Car set model =:m where id=:i");
        query.setParameter("m", "Suzuki");
        query.setParameter("i", 1);

        int status = query.executeUpdate();
        tr.commit();
        session.close();

        Assert.assertEquals(1, status);
    }

    @Test
    public void deleteCarByModelTest() {
        Session session = factory.openSession();
        Transaction tr = session.beginTransaction();
        Query query = session.createQuery("delete from Car where model=:m");
        query.setParameter("m", "Suzuki");

        Assert.assertEquals(1, query.executeUpdate());
        tr.commit();
        session.close();
    }

    @Test
    public void getAllCarsByCriteriaTest() {
        Session session = factory.openSession();
        Criteria criteria = session.createCriteria(Car.class);
        List cars = criteria.list();
        Assert.assertFalse(cars.isEmpty());
        session.close();
    }

    @Test
    public void getAllCarsByCriteriaWithRestrictionTest() {
        Session session = factory.openSession();
        Criteria criteria = session.createCriteria(Car.class);
        criteria.add(Restrictions.eq("model", "Audi"));

        Assert.assertFalse(criteria.list().isEmpty());
    }
}
