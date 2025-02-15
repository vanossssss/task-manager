package org.example.main;


import org.example.entity.*;
import org.example.utility.Managers;

import java.util.ArrayList;

import static org.example.enums.States.*;

public class Main {
    public static void main(String[] args) {
        Task firstTask = new Task(1, "Работа", "Надо работать");
        Task secondTask = new Task(2, "Отдых", "Надо отдыхать");

        Epic firstEpic = new Epic(3, "Сделать что-то эпичное", "для того,"
                + " чтобы это сделать нужно что-то взять");
        Subtask firstEpicSubtask = new Subtask(4,"Взять что-то эпичное", "нужно для, "
                + "того чтобы сделать эпик");
        firstEpicSubtask.setEpicId(firstEpic.getId());
        firstEpic.addSubtask(firstEpicSubtask.getId());

        Epic secondEpic = new Epic(5, "Поездка на работу", "пора работать");
        Subtask firstSecondEpicSubtask = new Subtask(6, "Заварить кофе", "кофе стоит на столе");
        firstSecondEpicSubtask.setEpicId(secondEpic.getId());
        secondEpic.addSubtask(firstSecondEpicSubtask.getId());

        Subtask secondSecondEpicSubtask = new Subtask(7,"Завести автомобиль", "Взять ключи");
        secondSecondEpicSubtask.setEpicId(secondEpic.getId());
        secondEpic.addSubtask(secondSecondEpicSubtask.getId());

        TaskManager inMemoryTaskManager = Managers.getDefault();
        inMemoryTaskManager.putTask(firstTask.getId(), firstTask);
        inMemoryTaskManager.putTask(secondTask.getId(), secondTask);

        inMemoryTaskManager.putEpic(firstEpic.getId(), firstEpic);
        inMemoryTaskManager.putEpic(secondEpic.getId(), secondEpic);

        inMemoryTaskManager.putSubtask(firstEpicSubtask.getId(), firstEpicSubtask);
        inMemoryTaskManager.putSubtask(firstSecondEpicSubtask.getId(), firstSecondEpicSubtask);
        inMemoryTaskManager.putSubtask(secondSecondEpicSubtask.getId(), secondSecondEpicSubtask);

        ArrayList<Task> taskList = new ArrayList<>(inMemoryTaskManager.getAllTasks());
        ArrayList<Epic> epicList = new ArrayList<>(inMemoryTaskManager.getAllEpics());
        ArrayList<Subtask> subtaskList = new ArrayList<>(inMemoryTaskManager.getAllSubtasks());

        inMemoryTaskManager.updateTask(1, "Работа", "Надо работать", DONE);
        inMemoryTaskManager.updateTask(2, "Отдых", "Надо отдыхать", IN_PROCESS);

        inMemoryTaskManager.updateSubtask(4,"Взять что-то эпичное", "нужно для, "
                + "того чтобы сделать эпик", DONE);
        inMemoryTaskManager.updateSubtask(6, "Заварить кофе", "кофе стоит на столе", DONE);

        for(Task task : taskList) {
            System.out.println(task);
        }

        for(Epic epic: epicList) {
            System.out.println(epic);
        }

        for(Subtask subtask: subtaskList) {
            System.out.println(subtask);
        }

        inMemoryTaskManager.getTask(1);
        inMemoryTaskManager.getEpic(3);
        inMemoryTaskManager.getSubtask(7);

        ArrayList<Task> history = new ArrayList<>(inMemoryTaskManager.history());

        for(Task task : history) {
            System.out.println(task);
        }
    }
}