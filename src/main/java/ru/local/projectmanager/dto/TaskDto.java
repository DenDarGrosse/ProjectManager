package ru.local.projectmanager.dto;

import lombok.*;
import ru.local.projectmanager.entity.enums.TaskStatus;
import ru.local.projectmanager.entity.enums.TaskType;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode
public class TaskDto {

    private UUID id;
    private UUID parent;
    private TaskType taskType;
    private TaskStatus taskStatus;
    private String name;
    private Date createdDate;
    private Date lastModifiedDate;
}
