package org.example.main;


import org.example.entity.*;
import org.example.utility.Managers;

import java.util.ArrayList;

import static org.example.enums.States.*;

public class Main {
    public static void main(String[] args) {
        Task firstTask = new Task(1, "Работа", "Надо работать");
        Task secondTask = new Task(2, "Отдых", "Надо отдыхать");
        Epic firstEpic = new Epic(3, "Сделать что-то эпичное", "");
        Subtask firstEpicSubtask = new Subtask(4,"Взять что-то эпичное", "", 3);
        Epic secondEpic = new Epic(5, "Поездка на работу", "пора работать");
        Subtask firstSecondEpicSubtask = new Subtask(6, "Заварить кофе", "кофе на столе", 5);
        Subtask secondSecondEpicSubtask = new Subtask(7,"Завести авто", "Взять ключи", 5);
        TaskPrinter printer = new TaskPrinter();
        TaskManager inMemoryTaskManager = Managers.getDefault();

        firstEpic.addSubtask(firstEpicSubtask.getId());
        secondEpic.addSubtask(firstSecondEpicSubtask.getId());
        secondEpic.addSubtask(secondSecondEpicSubtask.getId());

        inMemoryTaskManager.putTask(firstTask.getId(), firstTask);
        inMemoryTaskManager.putTask(secondTask.getId(), secondTask);

        inMemoryTaskManager.putEpic(firstEpic.getId(), firstEpic);
        inMemoryTaskManager.putEpic(secondEpic.getId(), secondEpic);

        inMemoryTaskManager.putSubtask(firstEpicSubtask.getId(), firstEpicSubtask);
        inMemoryTaskManager.putSubtask(firstSecondEpicSubtask.getId(), firstSecondEpicSubtask);
        inMemoryTaskManager.putSubtask(secondSecondEpicSubtask.getId(), secondSecondEpicSubtask);

        inMemoryTaskManager.updateTask(1, "Работа", "Надо работать", DONE);
        inMemoryTaskManager.updateTask(2, "Отдых", "Надо отдыхать", IN_PROCESS);
        inMemoryTaskManager.updateSubtask(4,"Взять что-то эпичное", "", DONE, 3);
        inMemoryTaskManager.updateSubtask(6, "Заварить кофе", "кофе на столе", DONE, 5);

        printer.printTasks(inMemoryTaskManager.getAllTasks());
        printer.printTasks(inMemoryTaskManager.getAllEpics());
        printer.printTasks(inMemoryTaskManager.getAllSubtasks());

        inMemoryTaskManager.getTask(1);
        inMemoryTaskManager.getEpic(3);
        inMemoryTaskManager.getSubtask(7);

        printer.printTasks(inMemoryTaskManager.history());
    }
}