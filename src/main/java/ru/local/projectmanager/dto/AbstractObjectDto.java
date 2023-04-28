package ru.local.projectmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class AbstractObjectDto {
    private UUID id;
    private UUID parent;
    private String name;
    private Date createdDate;
    private Date lastModifiedDate;
}
