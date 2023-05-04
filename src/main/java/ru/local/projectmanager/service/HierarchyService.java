package ru.local.projectmanager.service;

import org.springframework.stereotype.Service;
import ru.local.projectmanager.entity.AbstractObject;
import ru.local.projectmanager.repository.AbstractObjectRepository;
import ru.local.projectmanager.router.dto.AbstractObjectDto;

import java.util.*;

@Service
public class HierarchyService {

    private final Map<Class<? extends AbstractObject>, AbstractObjectService> objectServices;

    private final AbstractObjectRepository abstractObjectRepository;

    public HierarchyService(final List<AbstractObjectService> objectServices,
                            final AbstractObjectRepository abstractObjectRepository) {
        this.abstractObjectRepository = abstractObjectRepository;

        this.objectServices = new HashMap<>();

        objectServices.forEach(service -> {
            var type = service.getObjectType();
            this.objectServices.put(type, service);
        });
    }

    public List<AbstractObjectDto> getChild(final UUID id) {
        var project = find(id);
        var child = project.getChildren();
        return child
                .stream()
                .map(this::toDto)
                .toList();
    }

    public List<AbstractObjectDto> getRoots() {
        var projects = abstractObjectRepository.findAllByParentIsNull();
        return projects.stream().map(this::toDto).toList();
    }

    private AbstractObject find(final UUID id) {
        if (Objects.isNull(id)) {
            return null;
        }

        return abstractObjectRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Can not find object with id: " + id));
    }

    private AbstractObjectDto toDto(final AbstractObject abstractObject) {
        var type = abstractObject.getClass();
        var service = objectServices.get(type);
        return service.toDto(abstractObject);
    }
}
