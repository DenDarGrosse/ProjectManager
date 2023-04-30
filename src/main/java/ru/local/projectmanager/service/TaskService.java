package ru.local.projectmanager.service;

import org.springframework.stereotype.Service;
import ru.local.projectmanager.router.dto.TaskDto;
import ru.local.projectmanager.entity.AbstractObject;
import ru.local.projectmanager.entity.Task;
import ru.local.projectmanager.repository.AbstractObjectRepository;
import ru.local.projectmanager.repository.TaskRepository;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;

@Service
public class TaskService extends AbstractObjectService {
    private final TaskRepository taskRepository;

    public TaskService(final AbstractObjectRepository abstractObjectRepository,
                       final TaskRepository taskRepository) {
        super(abstractObjectRepository);
        this.taskRepository = taskRepository;
    }

    @Override
    public Class<? extends AbstractObject> getObjectType() {
        return Task.class;
    }

    @Override
    public TaskDto toDto(final AbstractObject abstractObject) {
        var task = (Task) abstractObject;
        var taskDto = new TaskDto();

        toDto(task, taskDto);

        taskDto.setTaskType(task.getTaskType());
        taskDto.setTaskStatus(task.getTaskStatus());

        return taskDto;
    }

    public Task fromDto(final TaskDto taskDto) {
        var task = new Task();

        fromDto(taskDto, task);

        task.setTaskType(taskDto.getTaskType());
        task.setTaskStatus(taskDto.getTaskStatus());

        return task;
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

    public TaskDto update(final TaskDto taskDto) {
        var task = fromDto(taskDto);
        var oldTask = find(task.getId());

        update(oldTask, task);

        var resultTask = taskRepository.save(oldTask);
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
