package ru.local.projectmanager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.local.projectmanager.dto.TaskDto;
import ru.local.projectmanager.service.TaskService;

import java.util.UUID;

@Controller
@RequestMapping(value = "/api/task")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable UUID id) {
        var taskDto = taskService.get(id);
        return ResponseEntity.ok().body(taskDto);
    }

    @PostMapping
    public ResponseEntity<TaskDto> saveTask(@RequestBody TaskDto taskDto) {
        var resultTaskDto = taskService.save(taskDto);
        return ResponseEntity.ok().body(resultTaskDto);
    }

    @PatchMapping
    public ResponseEntity<TaskDto> updateTask(@RequestBody TaskDto taskDto) {
        var resultTaskDto = taskService.update(taskDto);
        return ResponseEntity.ok().body(resultTaskDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskDto> deleteProject(@PathVariable UUID id) {
        var taskDto = taskService.delete(id);
        return ResponseEntity.ok().body(taskDto);
    }
}