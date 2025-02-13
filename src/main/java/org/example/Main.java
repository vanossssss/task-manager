package org.example;


import org.example.entity.Epic;
import org.example.entity.Subtask;
import org.example.entity.Task;
import org.example.entity.TaskManager;

import java.util.ArrayList;

import static org.example.config.Constants.*;

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

        TaskManager taskManager = new TaskManager();
        taskManager.putTask(firstTask.getId(), firstTask);
        taskManager.putTask(secondTask.getId(), secondTask);

        taskManager.putEpic(firstEpic.getId(), firstEpic);
        taskManager.putEpic(secondEpic.getId(), secondEpic);

        taskManager.putSubtask(firstEpicSubtask.getId(), firstEpicSubtask);
        taskManager.putSubtask(firstSecondEpicSubtask.getId(), firstSecondEpicSubtask);
        taskManager.putSubtask(secondSecondEpicSubtask.getId(), secondSecondEpicSubtask);

        ArrayList<Task> taskList = new ArrayList<>(taskManager.getAllTasks());
        ArrayList<Epic> epicList = new ArrayList<>(taskManager.getAllEpics());
        ArrayList<Subtask> subtaskList = new ArrayList<>(taskManager.getAllSubtasks());

        taskManager.updateTask(1, "Работа", "Надо работать", DONE);
        taskManager.updateTask(2, "Отдых", "Надо отдыхать", IN_PROCESS);

        taskManager.updateSubtask(4,"Взять что-то эпичное", "нужно для, "
                + "того чтобы сделать эпик", DONE);
        taskManager.updateSubtask(6, "Заварить кофе", "кофе стоит на столе", DONE);

        for(Task task : taskList) {
            System.out.println(task);
        }

        for(Epic epic: epicList) {
            System.out.println(epic);
        }

        for(Subtask subtask: subtaskList) {
            System.out.println(subtask);
        }
    }
}