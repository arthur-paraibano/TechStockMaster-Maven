package com.techstockmaster.model.entities;

public class Supervisor {

    private int id;
    private String name;

    public Supervisor() {
    }

    public Supervisor(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
