package com.ods.learning.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CarTest {

    private Car car;
    private Car carBean;

    @Before
    public void setup() {
        car = new Car();
        car.setModel("AUDI");

        carBean = (Car) new ClassPathXmlApplicationContext("applicationContext.xml").getBean("simpleCar");
    }

    @Test
    public void testSimple() {
        Assert.assertEquals("AUDI", car.getModel());
    }

    @Test
    public void checkCarBeanIsNotNull(){
        Assert.assertNotNull(carBean);
    }
}
