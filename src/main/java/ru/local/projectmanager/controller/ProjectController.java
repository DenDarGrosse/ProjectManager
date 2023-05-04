package ru.local.projectmanager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.local.projectmanager.entity.User;
import ru.local.projectmanager.router.dto.AbstractObjectDto;
import ru.local.projectmanager.router.dto.ProjectDto;
import ru.local.projectmanager.security.jwt.JwtUser;
import ru.local.projectmanager.service.HierarchyService;
import ru.local.projectmanager.service.ProjectService;

import java.util.List;
import java.util.UUID;

import static ru.local.projectmanager.router.Router.PROJECT_ENDPOINT;

@Controller
@RequestMapping(value = PROJECT_ENDPOINT)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProjectController {

    private final ProjectService projectService;
    private final HierarchyService hierarchyService;

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getProject(@PathVariable UUID id) {
        var projectDto = projectService.get(id);
        return ResponseEntity.ok().body(projectDto);
    }

    @GetMapping("/{id}/child")
    public ResponseEntity<List<AbstractObjectDto>> getChildProjects(@PathVariable UUID id) {
        var child = hierarchyService.getChild(id);
        return ResponseEntity.ok().body(child);
    }

    @GetMapping()
    public ResponseEntity<List<AbstractObjectDto>> getRootProjects() {
        var rootProjects = hierarchyService.getRoots();
        return ResponseEntity.ok().body(rootProjects);
    }

    @PostMapping
    public ResponseEntity<ProjectDto> createProject(@RequestBody ProjectDto projectDto,
                                                    @AuthenticationPrincipal JwtUser jwtUser) {
        var resultProjectDto = projectService.create(projectDto, jwtUser.getUsername());
        return ResponseEntity.ok().body(resultProjectDto);
    }

    @PatchMapping
    public ResponseEntity<ProjectDto> updateProject(@RequestBody ProjectDto projectDto) {
        var resultProjectDto = projectService.update(projectDto);
        return ResponseEntity.ok().body(resultProjectDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProjectDto> deleteProject(@PathVariable UUID id) {
        var projectDto = projectService.delete(id);
        return ResponseEntity.ok().body(projectDto);
    }
}
