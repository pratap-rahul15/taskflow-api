package com.example.taskflow.task;

public record Task(
        Long id,
        String title,
        String description,
        boolean completed
) {
}
