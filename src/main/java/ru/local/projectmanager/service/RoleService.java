package ru.local.projectmanager.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.local.projectmanager.entity.Role;
import ru.local.projectmanager.repository.RoleRepository;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role getRoleById(UUID roleId) {
        return roleRepository.findById(roleId)
                .orElseThrow(NoSuchElementException::new);
    }
}
