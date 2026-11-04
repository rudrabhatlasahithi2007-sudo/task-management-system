package com.example.demo.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Task;
import com.example.demo.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public Task createTask(Task task) {
        return repository.save(task);
    }

    // READ ALL
    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    // READ BY ID
    public Task getTaskById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                    new TaskNotFoundException(
                        "Task not found with ID: " + id
                    ));
    }

    // UPDATE
    public Task updateTask(Long id, Task updatedTask) {

        Task existingTask = getTaskById(id);

        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(
                updatedTask.getDescription());
        existingTask.setPriority(
                updatedTask.getPriority());
        existingTask.setStatus(
                updatedTask.getStatus());
        existingTask.setDueDate(
                updatedTask.getDueDate());

        return repository.save(existingTask);
    }

    // DELETE
    public void deleteTask(Long id) {

        Task existingTask = getTaskById(id);

        repository.delete(existingTask);
    }
    public Page<Task> getTasksWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable);
    }
}
