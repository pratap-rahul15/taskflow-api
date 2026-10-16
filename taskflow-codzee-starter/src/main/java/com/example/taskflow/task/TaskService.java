package com.example.taskflow.task;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {
    private final Map<Long, Task> tasks = new LinkedHashMap<>();
    private final AtomicLong nextId = new AtomicLong(1);

    public List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }

    public Task findById(long id) {
        Task task = tasks.get(id);
        if (task == null) {
            throw new NoSuchElementException("Task not found: " + id);
        }
        return task;
    }

    public Task create(CreateTaskRequest request) {
        long id = nextId.getAndIncrement();
        Task task = new Task(id, request.title(), request.description(), false);
        tasks.put(id, task);
        return task;
    }

    public Task complete(long id) {
        Task existing = findById(id);
        Task updated = new Task(
                existing.id(),
                existing.title(),
                existing.description(),
                true
        );
        tasks.put(id, updated);
        return updated;
    }
}
