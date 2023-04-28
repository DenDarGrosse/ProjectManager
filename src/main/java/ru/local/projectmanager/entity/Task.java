package ru.local.projectmanager.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;
import ru.local.projectmanager.entity.enums.TaskStatus;
import ru.local.projectmanager.entity.enums.TaskType;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("task")
public class Task extends AbstractObject {

    @Column(nullable = false)
    private TaskType taskType;

    @Column(nullable = false)
    private TaskStatus taskStatus;
}
