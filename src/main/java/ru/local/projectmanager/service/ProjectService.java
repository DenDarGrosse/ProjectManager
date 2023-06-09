package ru.local.projectmanager.service;

import org.springframework.stereotype.Service;
import ru.local.projectmanager.entity.AbstractObject;
import ru.local.projectmanager.entity.Project;
import ru.local.projectmanager.repository.AbstractObjectRepository;
import ru.local.projectmanager.repository.ProjectRepository;
import ru.local.projectmanager.router.dto.ProjectDto;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;

@Service
public class ProjectService extends AbstractObjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(final AbstractObjectRepository abstractObjectRepository,
                          final ProjectRepository projectRepository,
                          final UserService userService) {
        super(abstractObjectRepository, userService);
        this.projectRepository = projectRepository;
    }

    @Override
    public Class<? extends AbstractObject> getObjectType() {
        return Project.class;
    }

    @Override
    public ProjectDto toDto(final AbstractObject abstractObject) {
        var project = (Project) abstractObject;
        var projectDto = new ProjectDto();

        toDto(project, projectDto);

        return projectDto;
    }

    public Project fromDto(final ProjectDto projectDto) {
        var project = new Project();

        fromDto(projectDto, project);

        return project;
    }

    public ProjectDto get(final UUID id) {
        var project = find(id);
        return toDto(project);
    }

    public ProjectDto create(final ProjectDto projectDto, final String login) {
        projectDto.setId(null);

        var user = userService.getUserByLogin(login);
        var project = fromDto(projectDto);

        project.setOwner(user);

        var resultProject = projectRepository.save(project);
        return toDto(resultProject);
    }

    public ProjectDto update(final ProjectDto projectDto) {
        var project = fromDto(projectDto);
        var oldProject = find(project.getId());

        update(oldProject, project);

        var resultProject = projectRepository.save(oldProject);
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
                .orElseThrow(() -> new NoSuchElementException("Can not find object with id: " + id));
    }
}
