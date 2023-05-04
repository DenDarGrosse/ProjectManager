package ru.local.projectmanager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.local.projectmanager.router.dto.TaskDto;
import ru.local.projectmanager.security.jwt.JwtUser;
import ru.local.projectmanager.service.TaskService;

import java.util.UUID;

import static ru.local.projectmanager.router.Router.TASK_ENDPOINT;

@Controller
@RequestMapping(value = TASK_ENDPOINT)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable UUID id) {
        var taskDto = taskService.get(id);
        return ResponseEntity.ok().body(taskDto);
    }

    @PostMapping
    public ResponseEntity<TaskDto> saveTask(@RequestBody TaskDto taskDto,
                                            @AuthenticationPrincipal JwtUser jwtUser) {
        var resultTaskDto = taskService.create(taskDto, jwtUser.getUsername());
        return ResponseEntity.ok().body(resultTaskDto);
    }

    @PatchMapping
    public ResponseEntity<TaskDto> updateTask(@RequestBody TaskDto taskDto,
                                              @AuthenticationPrincipal JwtUser jwtUser) {
        var resultTaskDto = taskService.update(taskDto, jwtUser.getUsername());
        return ResponseEntity.ok().body(resultTaskDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskDto> deleteProject(@PathVariable UUID id,
                                                 @AuthenticationPrincipal JwtUser jwtUser) {
        var taskDto = taskService.delete(id, jwtUser.getUsername());
        return ResponseEntity.ok().body(taskDto);
    }
}