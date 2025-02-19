package org.example.entity;

import java.util.Collection;

public class TaskPrinter {
    public void printTasks(Collection<? extends Task> tasks){
        for(Task task : tasks){
            System.out.println(task);
        }
    }
}
