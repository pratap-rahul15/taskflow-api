package com.example.taskflow.task;

public record CreateTaskRequest(
        String title,
        String description
) {
}
