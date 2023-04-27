package ru.local.projectmanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.local.projectmanager.dto.TaskDto;
import ru.local.projectmanager.entity.Task;
import ru.local.projectmanager.repository.TaskRepository;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectService projectService;

    public TaskDto toDto(final Task task) {
        var parent = task.getParent();
        var parentId = Objects.isNull(parent) ? null : parent.getId();

        return TaskDto.builder()
                .id(task.getId())
                .parent(parentId)
                .taskType(task.getTaskType())
                .taskStatus(task.getTaskStatus())
                .name(task.getTaskName())
                .createdDate(task.getCreatedDate())
                .lastModifiedDate(task.getLastModifiedDate())
                .build();
    }

    public Task fromDto(final TaskDto taskDto) {
        var parent = projectService.find(taskDto.getParent());

        return Task.builder()
                .id(taskDto.getId())
                .parent(parent)
                .taskType(taskDto.getTaskType())
                .taskStatus(taskDto.getTaskStatus())
                .taskName(taskDto.getName())
                .createdDate(taskDto.getCreatedDate())
                .lastModifiedDate(taskDto.getLastModifiedDate())
                .build();
    }

    public TaskDto get(final UUID id) {
        var task = find(id);
        return toDto(task);
    }

    public TaskDto save(final TaskDto taskDto) {
        var task = fromDto(taskDto);
        var resultTask = taskRepository.save(task);
        return toDto(resultTask);
    }

    public TaskDto delete(final UUID id) {
        var task = find(id);
        taskRepository.delete(task);
        return toDto(task);
    }

    public Task find(final UUID id) {
        if (Objects.isNull(id)) {
            return null;
        }

        return taskRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }
}
