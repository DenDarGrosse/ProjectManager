package ru.local.projectmanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.local.projectmanager.router.dto.AbstractObjectDto;
import ru.local.projectmanager.entity.AbstractObject;
import ru.local.projectmanager.repository.AbstractObjectRepository;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public abstract class AbstractObjectService {

    private final AbstractObjectRepository abstractObjectRepository;

    public abstract Class<? extends AbstractObject> getObjectType();

    public abstract AbstractObjectDto toDto(final AbstractObject project);

    public void toDto(final AbstractObject abstractObject, final AbstractObjectDto abstractObjectDto) {
        var parent = abstractObject.getParent();
        var parentId = Objects.isNull(parent) ? null : parent.getId();

        abstractObjectDto.setId(abstractObject.getId());
        abstractObjectDto.setParent(parentId);
        abstractObjectDto.setName(abstractObject.getObjectName());
        abstractObjectDto.setCreatedDate(abstractObject.getCreatedDate());
        abstractObjectDto.setLastModifiedDate(abstractObject.getLastModifiedDate());
    }

    public void fromDto(final AbstractObjectDto abstractObjectDto, final AbstractObject abstractObject) {
        var parent = findParent(abstractObjectDto.getParent());

        abstractObject.setId(abstractObjectDto.getId());
        abstractObject.setParent(parent);
        abstractObject.setObjectName(abstractObjectDto.getName());
        abstractObject.setCreatedDate(abstractObjectDto.getCreatedDate());
        abstractObject.setLastModifiedDate(abstractObjectDto.getLastModifiedDate());
    }

    public AbstractObject findParent(final UUID id) {
        if (Objects.isNull(id)) {
            return null;
        }

        return abstractObjectRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    public void update(final AbstractObject oldAbstractObject, final AbstractObject newAbstractObject) {
        oldAbstractObject.setObjectName(newAbstractObject.getObjectName());
        oldAbstractObject.setParent(newAbstractObject.getParent());
    }
}
