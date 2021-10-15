package com.ods.learning.model;


import javax.persistence.*;

@Entity
public class Engine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "engine_id")
    private Integer id;
    private String model;

    public Engine(){}


    public Engine(String model) {
        this.model = model;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
