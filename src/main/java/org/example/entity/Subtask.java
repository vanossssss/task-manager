package org.example.entity;

public class Subtask extends Task {
    private int epicId;

    public Subtask(int id, String name, String description, int epicId){
        super(id, name, description);
    };

    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }
}
