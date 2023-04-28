package ru.local.projectmanager.repository;

import org.springframework.data.repository.CrudRepository;
import ru.local.projectmanager.entity.AbstractObject;

import java.util.List;
import java.util.UUID;

public interface AbstractObjectRepository extends CrudRepository<AbstractObject, UUID> {
    List<AbstractObject> findAllByParentIsNull();
}
