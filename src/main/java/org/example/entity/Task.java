package org.example.entity;

import static org.example.config.Constants.NEW;

public class Task {
    protected int id;
    protected String name;
    protected String description;
    protected int state;

    public Task(int id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
        this.state = NEW;
    };

    @Override
    public String toString(){
        return "id: " + id + ", name:  " + name + ", description: " + description + ", state: " + state;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
