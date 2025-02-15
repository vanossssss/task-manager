package org.example.entity;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    ArrayList<Task> historyList = new ArrayList<>();

    public void add(Task task) {
        if(historyList.size() < 10)
            historyList.add(task);
        else {
            historyList.remove(0);
            historyList.add(task);
        }
    }

    public List<Task> getHistory() {
        return historyList;
    }
}
