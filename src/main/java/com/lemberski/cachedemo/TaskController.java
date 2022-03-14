package com.lemberski.cachedemo;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public Iterable<Task> getAll() {
        return taskService.getAll();
    }

    @GetMapping("/{id}")
    public Task getById(@PathVariable UUID id) {
        Optional<Task> task = Optional.ofNullable(taskService.get(id));
        return task.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public Task create(String description) {
        return taskService.create(description);
    }

    @PutMapping
    public Task update(@RequestBody Task task) {
        Optional<Task> taskOptional = Optional.ofNullable(taskService.update(task));
        return taskOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        taskService.delete(id);
    }

}
