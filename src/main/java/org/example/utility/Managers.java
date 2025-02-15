package org.example.utility;

import org.example.entity.InMemoryHistoryManager;
import org.example.entity.InMemoryTaskManager;
import org.example.entity.TaskManager;

public final class Managers {
    private Managers() {};

    public static TaskManager getDefault(){
        return new InMemoryTaskManager();
    };

    public static InMemoryHistoryManager getDefaultHistory(){
        return new InMemoryHistoryManager();
    }
}
