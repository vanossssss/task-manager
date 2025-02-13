package org.example.entity;

import java.util.Collection;
import java.util.HashMap;

import static org.example.config.Constants.*;

public class TaskManager {
    private int id = 0;
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, Epic> epics = new HashMap<>();
    private HashMap<Integer, Subtask> subtasks = new HashMap<>();

    public void createTask(Task task) {
        task.setId(id);
        this.tasks.put(task.getId(), task);
        this.id++;
    }

    public void createEpic(Epic epic) {
        epic.setId(id);
        this.epics.put(epic.getId(), epic);
        this.id++;
    }

    public void createSubtask(Subtask subtask) {
        subtask.setId(id);
        this.subtasks.put(subtask.getId(), subtask);
        this.id++;
    }

    public void putTask(int id, Task task) {
        this.tasks.put(id, task);
    }

    public void putEpic(int id, Epic epic) {
        this.epics.put(id, epic);
    }

    public void putSubtask(int id, Subtask subtask) {
        this.subtasks.put(id, subtask);
    }

    public void updateTask(int id, String name, String description, int state) {
        Task task = this.tasks.get(id);
        task.setName(name);
        task.setDescription(description);
        task.setState(state);
        this.tasks.put(task.getId(), task);
    }

    private void updateEpic(Epic epic) {
        boolean isDone = false;
        boolean hasNew = false;
        for(int subtaskId : epic.getSubtasks()){
            if(!epic.getSubtasks().isEmpty()) {
                int currentState = this.subtasks.get(subtaskId).getState();
                if(currentState == NEW) {
                    hasNew = true;
                }
                if((currentState == DONE)&&(!hasNew)) {
                    isDone = true;
                } else if(currentState == IN_PROCESS) {
                    epic.setState(IN_PROCESS);
                    this.epics.remove(epic.getId());
                    this.epics.put(epic.getId(), epic);
                    break;
                }
            }
        }
        if(isDone&&hasNew) {
            epic.setState(IN_PROCESS);
            this.epics.remove(epic.getId());
            this.epics.put(epic.getId(), epic);
        } else if(isDone) {
            epic.setState(DONE);
            this.epics.remove(epic.getId());
            this.epics.put(epic.getId(), epic);
        }
    }

    public void updateSubtask(int id, String name, String description, int state) {
        Subtask subtask = subtasks.get(id);
        this.subtasks.remove(id);
        subtask.setName(name);
        subtask.setDescription(description);
        subtask.setState(state);
        this.subtasks.put(id, subtask);
        updateEpic(epics.get(subtask.getEpicId()));
    }

    public Task getTask(int id) {
        return tasks.get(id);
    }

    public Epic getEpic(int id) {
        return epics.get(id);
    }


    public Subtask getSubtask(int id) {
        return subtasks.get(id);
    }

    public Collection<Task> getAllTasks() {
        return tasks.values();
    }

    public Collection<Epic> getAllEpics() {
        return epics.values();
    }

    public Collection<Subtask> getAllSubtasks() {
        return subtasks.values();
    }

    public void deleteTask(int id) {
        this.tasks.remove(id);
    }

    public void deleteEpic(int id) {
        this.epics.remove(id);
    }

    public void deleteSubtask(int id) {
        this.subtasks.remove(id);
    }

    public void deleteAllTasks() {
        this.tasks.clear();
    }

    public void deleteAllEpics() {
        this.epics.clear();
    }

    public void deleteAllSubtasks() {
        this.subtasks.clear();
    }
}
