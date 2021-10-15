package com.ods.learning.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CarHibernateTest {
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
    public void checkSavingCarInDatabaseTest() {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        car = new Car();
        car.setNumber("BK1234");
        car.setModel("Honda");

        session.save(car);
        t.commit();

        Transaction t2 = session.beginTransaction();
        Car carFromDatabase = session.get(Car.class, 1);
        t2.commit();

        Assert.assertNotNull(carFromDatabase);
      //  factory.close();
        session.close();
    }

    @Test
    public void checkCarPropertiesAreSavedInDatabaseTest(){
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        Car carFromDatabase = session.get(Car.class, 1);
        t.commit();
        session.close();

        Assert.assertEquals(carFromDatabase.getId(), Integer.valueOf(1));
        Assert.assertEquals(carFromDatabase.getModel(), "Honda");
        Assert.assertEquals(carFromDatabase.getNumber(), "BK1234");
    }

    @Test
    public void checkSavingEnginesForCarInDatabaseTest(){
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        Engine engine = new Engine();
        engine.setModel("Engine2");

        Car car = new Car();
        car.setNumber("ERT23");
        car.setModel("Audi");
        car.setEngine(engine);

        session.save(engine);
        session.save(car);
        t.commit();

        Assert.assertNotEquals(null, engine);
    }

   /* @Test
    public void deleteCarIfTheSameModelAlreadyDeleteTest(){
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        Car carFromDatabase = session.get(Car.class, 2);
        if (carFromDatabase.getModel().equals("Honda")) {
            session.delete(carFromDatabase);
        }
        t.commit();

        Car notExistingCar = session.get(Car.class, 2);
        session.close();

        Assert.assertNull(notExistingCar);
    }*/

}
