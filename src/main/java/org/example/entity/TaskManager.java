package org.example.entity;

import org.example.enums.States;

import java.util.List;

public interface TaskManager {
    void createTask(Task task) ;

    void createEpic(Epic epic) ;

    void createSubtask(Subtask subtask) ;

    void putTask(int id, Task task);

    void putEpic(int id, Epic epic);

    void putSubtask(int id, Subtask subtask);

    void updateTask(int id, String name, String description, States state);

    void updateSubtask(int id, String name, String description, States state);

    Task getTask(int id);

    Epic getEpic(int id);

    Subtask getSubtask(int id);

    List<Task> getAllTasks();

    List<Epic> getAllEpics();

    List<Subtask> getAllSubtasks();

    List<Task> history();

    void deleteTask(int id);

    void deleteEpic(int id);

    void deleteSubtask(int id);

    void deleteAllTasks();

    void deleteAllEpics();

    void deleteAllSubtasks();
}
