package com.ods.learning.model;

public class Car {
    private Integer id;
    private String model;
    private String number;

    public Car(){}

    public Car(String model, String number) {
        this.model = model;
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
