package ru.local.projectmanager.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
//@AllArgsConstructor //fixme: uncomment, when where will be fields
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("project")
public class Project extends AbstractObject {
}
