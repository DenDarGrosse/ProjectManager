package ru.local.projectmanager.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import ru.local.projectmanager.entity.enums.TaskStatus;
import ru.local.projectmanager.entity.enums.TaskType;

import java.util.Date;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode
@EntityListeners(AuditingEntityListener.class)
public class Task {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Project parent;

    @Column(nullable = false)
    private TaskType taskType;

    @Column(nullable = false)
    private TaskStatus taskStatus;

    @Column(nullable = false)
    private String taskName;

    @CreatedDate
    @Column(nullable = false)
    private Date createdDate;

    @LastModifiedDate
    @Column(nullable = false)
    private Date lastModifiedDate;
}
