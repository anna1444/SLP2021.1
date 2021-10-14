package com.ods.learning;

import com.ods.learning.model.Car;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Car car = (Car) applicationContext.getBean("simpleCar");
        System.out.println(car);
    }
}
