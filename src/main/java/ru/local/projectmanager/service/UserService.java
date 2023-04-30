package ru.local.projectmanager.service;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import ru.local.projectmanager.entity.Role;
import ru.local.projectmanager.entity.User;
import ru.local.projectmanager.repository.UserRepository;
import ru.local.projectmanager.utils.BCryptEncoderUtil;

import java.util.Objects;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public User createUser(User user, Role role) {
        var newUser = new User();
        if (Objects.isNull(user.getLogin()) || Objects.isNull(user.getPassword())) {
            throw new BadCredentialsException("Login or password is empty");
        }
        newUser.setPassword(BCryptEncoderUtil.encryptPassword(user.getPassword()));
        newUser.setLogin(user.getLogin());
        newUser.setUserRoles(role);
        return userRepository.save(newUser);
    }

}
