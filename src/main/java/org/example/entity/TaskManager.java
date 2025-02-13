package org.example.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.example.config.Constants.*;

public class TaskManager {
    private int id = 0;
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, Epic> epics = new HashMap<>();
    private HashMap<Integer, Subtask> subtasks = new HashMap<>();

    public void createTask(Task task) {
        task.setId(id);
        tasks.put(task.getId(), task);
        id++;
    }

    public void createEpic(Epic epic) {
        epic.setId(id);
        epics.put(epic.getId(), epic);
        id++;
    }

    public void createSubtask(Subtask subtask) {
        subtask.setId(id);
        subtasks.put(subtask.getId(), subtask);
        id++;
    }

    public void putTask(int id, Task task) {
        tasks.put(id, task);
    }

    public void putEpic(int id, Epic epic) {
        epics.put(id, epic);
    }

    public void putSubtask(int id, Subtask subtask) {
        subtasks.put(id, subtask);
    }

    public void updateTask(int id, String name, String description, int state) {
        Task task = tasks.get(id);
        task.setName(name);
        task.setDescription(description);
        task.setState(state);
        tasks.put(task.getId(), task);
    }

    private void updateEpic(Epic epic) {
        boolean isDone = false;
        boolean hasNew = false;
        for(int subtaskId : epic.getSubtasks()){
            if(!epic.getSubtasks().isEmpty()) {
                int currentState = subtasks.get(subtaskId).getState();
                if(currentState == NEW) {
                    hasNew = true;
                }
                if((currentState == DONE)&&(!hasNew)) {
                    isDone = true;
                } else if(currentState == IN_PROCESS) {
                    epic.setState(IN_PROCESS);
                    epics.remove(epic.getId());
                    epics.put(epic.getId(), epic);
                    break;
                }
            }
        }
        if(isDone&&hasNew) {
            epic.setState(IN_PROCESS);
            epics.remove(epic.getId());
            epics.put(epic.getId(), epic);
        } else if(isDone) {
            epic.setState(DONE);
            epics.remove(epic.getId());
            epics.put(epic.getId(), epic);
        }
    }

    public void updateSubtask(int id, String name, String description, int state) {
        Subtask subtask = subtasks.get(id);
        subtasks.remove(id);
        subtask.setName(name);
        subtask.setDescription(description);
        subtask.setState(state);
        subtasks.put(id, subtask);
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

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public List<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    public List<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    public void deleteTask(int id) {
        tasks.remove(id);
    }

    public void deleteEpic(int id) {
        epics.remove(id);
    }

    public void deleteSubtask(int id) {
        Epic epic = epics.get(subtasks.get(id).getEpicId());
        epic.deleteSubtask(id);
        subtasks.remove(id);
        updateEpic(epic);
    }

    public void deleteAllTasks() {
        tasks.clear();
    }

    public void deleteAllEpics() {
        epics.clear();
    }

    public void deleteAllSubtasks() {
        for(Subtask subtask : getAllSubtasks()) {
            epics.get(subtask.getEpicId()).clearSubtasks();
        }
        subtasks.clear();
    }
}
