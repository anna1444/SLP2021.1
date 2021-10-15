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

public class EngineHibernateTest {
    private StandardServiceRegistry ssr;
    private Metadata meta;
    private SessionFactory factory;
    private Engine engine;

    @Before
    public void setup() {
        ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        meta = new MetadataSources(ssr).getMetadataBuilder().build();
        factory = meta.getSessionFactoryBuilder().build();
    }

    @Test
    public void saveEngineInDatabaseTest(){
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        engine = new Engine();
        engine.setModel("WEWE12");

        session.save(engine);
        t.commit();

        Transaction t2 = session.beginTransaction();
        Engine engineFromDatabase = session.get(Engine.class, 2);
        t2.commit();

        Assert.assertNotNull(engineFromDatabase);
        //  factory.close();
        session.close();
    }
}
