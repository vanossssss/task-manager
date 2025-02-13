package org.example.entity;

import java.util.ArrayList;

public class Epic extends Task {
    private final ArrayList<Integer> subtasks = new ArrayList<>();

    public Epic(int id, String name, String description) {
        super(id, name, description);
    }

    public void addSubtask(int id) {
        subtasks.add(id);
    }

    public void deleteSubtask(int id) {
        subtasks.remove(id);
    }

    public void clearSubtasks() {
        subtasks.clear();
    }

    public ArrayList<Integer> getSubtasks(){
        return subtasks;
    }
}
