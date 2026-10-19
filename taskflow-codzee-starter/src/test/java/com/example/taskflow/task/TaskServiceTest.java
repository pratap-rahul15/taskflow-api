
package com.example.taskflow.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {

    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskService = new TaskService();
    }

    @Test
    void shouldCreateTaskWithGeneratedIdAndIncompleteStatus() {
        CreateTaskRequest request =
                new CreateTaskRequest("Learn Spring Boot", "Write unit tests");

        Task task = taskService.create(request);

        assertNotNull(task.id());
        assertEquals(1L, task.id());
        assertEquals("Learn Spring Boot", task.title());
        assertEquals("Write unit tests", task.description());
        assertFalse(task.completed());
    }

    @Test
    void shouldCompleteExistingTask() {
        CreateTaskRequest request =
                new CreateTaskRequest("Finish assignment", "Review PRs");

        Task created = taskService.create(request);
        Task completed = taskService.complete(created.id());

        assertTrue(completed.completed());
        assertEquals(created.id(), completed.id());
        assertEquals(created.title(), completed.title());
        assertEquals(created.description(), completed.description());
    }

    @Test
    void shouldThrowWhenTaskDoesNotExist() {
        assertThrows(
                NoSuchElementException.class,
                () -> taskService.findById(999L)
        );
    }

    @Test
    void shouldReturnTasksInAscendingIdOrder() {
        Task first = taskService.create(
                new CreateTaskRequest("First task", "First")
        );
        Task second = taskService.create(
                new CreateTaskRequest("Second task", "Second")
        );

        assertEquals(
                java.util.List.of(first.id(), second.id()),
                taskService.findAll().stream().map(Task::id).toList()
        );
    }
}
