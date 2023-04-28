package ru.local.projectmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.local.projectmanager.entity.enums.TaskStatus;
import ru.local.projectmanager.entity.enums.TaskType;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class TaskDto extends AbstractObjectDto{

    private TaskType taskType;
    private TaskStatus taskStatus;
}
