package ru.local.projectmanager.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.local.projectmanager.entity.User;
import ru.local.projectmanager.security.jwt.JwtUserFactory;
import ru.local.projectmanager.service.UserService;

import static java.lang.String.format;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        var user = userService.getUserByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException(format("User with login: %s not found", login));
        }
        return JwtUserFactory.create(user);
    }
}
