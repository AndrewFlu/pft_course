package ru.moneta.pft.mantis.model;

public class Project {

    // fields
    private int id;
    private String name;

    // getters & setters

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Project withId(int id) {
        this.id = id;
        return this;
    }

    public Project withName(String name) {
        this.name = name;
        return this;
    }
}
