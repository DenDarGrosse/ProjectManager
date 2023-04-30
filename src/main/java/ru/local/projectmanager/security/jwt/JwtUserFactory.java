package ru.local.projectmanager.security.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.local.projectmanager.entity.Role;
import ru.local.projectmanager.entity.User;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class JwtUserFactory {

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getUserId(),
                user.getLogin(),
                user.getPassword(),
                true,
                mapRolesToGrantedAuthorities(Collections.singletonList(user.getUserRoles()))
        );
    }

    private static List<GrantedAuthority> mapRolesToGrantedAuthorities(List<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());
    }
}
