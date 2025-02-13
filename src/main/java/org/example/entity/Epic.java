package org.example.entity;

import java.util.ArrayList;

public class Epic extends Task {
    private final ArrayList<Integer> subtasks = new ArrayList<>();

    public Epic(int id, String name, String description) {
        super(id, name, description);
    }

    public void addSubtask(int id) {
        this.subtasks.add(id);
    }

    public ArrayList<Integer> getSubtasks(){
        return subtasks;
    }
}
