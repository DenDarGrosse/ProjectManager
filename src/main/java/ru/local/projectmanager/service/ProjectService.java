package ru.local.projectmanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.local.projectmanager.dto.ProjectDto;
import ru.local.projectmanager.entity.Project;
import ru.local.projectmanager.repository.ProjectRepository;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectDto toDto(final Project project) {
        var parent = project.getParent();
        var parentId = Objects.isNull(parent) ? null : parent.getId();

        return ProjectDto.builder()
                .id(project.getId())
                .parent(parentId)
                .name(project.getProjectName())
                .createdDate(project.getCreatedDate())
                .lastModifiedDate(project.getLastModifiedDate())
                .build();
    }

    public Project fromDto(final ProjectDto projectDto) {
        var parent = find(projectDto.getParent());

        return Project.builder()
                .id(projectDto.getId())
                .parent(parent)
                .projectName(projectDto.getName())
                .createdDate(projectDto.getCreatedDate())
                .lastModifiedDate(projectDto.getLastModifiedDate())
                .build();
    }

    public ProjectDto get(final UUID id) {
        var project = find(id);
        return toDto(project);
    }

    public ProjectDto save(final ProjectDto projectDto) {
        var project = fromDto(projectDto);
        var resultProject = projectRepository.save(project);
        return toDto(resultProject);
    }

    public ProjectDto delete(final UUID id) {
        var project = find(id);
        projectRepository.delete(project);
        return toDto(project);
    }

    public Project find(final UUID id) {
        if (Objects.isNull(id)) {
            return null;
        }

        return projectRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }
}
