package org.example.entity;

import org.example.enums.States;
import org.example.utility.Managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.example.enums.States.*;

public class InMemoryTaskManager implements TaskManager{
    private int id = 0;
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, Epic> epics = new HashMap<>();
    private HashMap<Integer, Subtask> subtasks = new HashMap<>();
    private HistoryManager historyManager = Managers.getDefaultHistory();

    @Override
    public void createTask(Task task) {
        task.setId(id);
        tasks.put(task.getId(), task);
        id++;
    }

    @Override
    public void createEpic(Epic epic) {
        epic.setId(id);
        epics.put(epic.getId(), epic);
        id++;
    }

    @Override
    public void createSubtask(Subtask subtask) {
        subtask.setId(id);
        subtasks.put(subtask.getId(), subtask);
        id++;
    }

    @Override
    public void putTask(int id, Task task) {
        tasks.put(id, task);
    }

    @Override
    public void putEpic(int id, Epic epic) {
        epics.put(id, epic);
    }

    @Override
    public void putSubtask(int id, Subtask subtask) {
        subtasks.put(id, subtask);
    }

    @Override
    public void updateTask(int id, String name, String description, States state) {
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
                States currentState = subtasks.get(subtaskId).getState();
                if(currentState.equals(NEW)) {
                    hasNew = true;
                }
                if((currentState == DONE)&&(!hasNew)) {
                    isDone = true;
                } else if(currentState.equals(IN_PROCESS)) {
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

    @Override
    public void updateSubtask(int id, String name, String description, States state) {
        Subtask subtask = subtasks.get(id);
        subtasks.remove(id);
        subtask.setName(name);
        subtask.setDescription(description);
        subtask.setState(state);
        subtasks.put(id, subtask);
        updateEpic(epics.get(subtask.getEpicId()));
    }

    @Override
    public Task getTask(int id) {
        historyManager.add(tasks.get(id));
        return tasks.get(id);
    }

    @Override
    public Epic getEpic(int id) {
        historyManager.add(epics.get(id));
        return epics.get(id);
    }

    @Override
    public Subtask getSubtask(int id) {
        historyManager.add(subtasks.get(id));
        return subtasks.get(id);
    }

    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public List<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public List<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    public List <Task> history(){
        return historyManager.getHistory();
    }

    @Override
    public void deleteTask(int id) {
        tasks.remove(id);
    }

    @Override
    public void deleteEpic(int id) {
        epics.remove(id);
    }

    @Override
    public void deleteSubtask(int id) {
        Epic epic = epics.get(subtasks.get(id).getEpicId());
        epic.deleteSubtask(id);
        subtasks.remove(id);
        updateEpic(epic);
    }

    @Override
    public void deleteAllTasks() {
        tasks.clear();
    }

    @Override
    public void deleteAllEpics() {
        epics.clear();
    }

    @Override
    public void deleteAllSubtasks() {
        for(Subtask subtask : getAllSubtasks()) {
            epics.get(subtask.getEpicId()).clearSubtasks();
        }
        subtasks.clear();
    }
}
