package ru.local.projectmanager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.local.projectmanager.dto.ProjectDto;
import ru.local.projectmanager.service.ProjectService;

import java.util.UUID;

@Controller
@RequestMapping(value = "/api/project")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getProject(@PathVariable UUID id) {
        var projectDto = projectService.get(id);
        return ResponseEntity.ok().body(projectDto);
    }

    @PostMapping
    public ResponseEntity<ProjectDto> saveProject(@RequestBody ProjectDto projectDto) {
        var resultProjectDto = projectService.save(projectDto);
        return ResponseEntity.ok().body(resultProjectDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProjectDto> deleteProject(@PathVariable UUID id) {
        var projectDto = projectService.delete(id);
        return ResponseEntity.ok().body(projectDto);
    }
}
