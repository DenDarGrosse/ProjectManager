package ru.local.projectmanager.dto;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode
public class ProjectDto {

    private UUID id;
    private UUID parent;
    private String name;
    private Date createdDate;
    private Date lastModifiedDate;
}
