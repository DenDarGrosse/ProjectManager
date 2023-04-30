package ru.local.projectmanager.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.local.projectmanager.entity.User;
import ru.local.projectmanager.router.dto.LoginDto;
import ru.local.projectmanager.router.dto.LoginResponseDto;
import ru.local.projectmanager.router.dto.RegistrationDto;
import ru.local.projectmanager.security.jwt.JwtTokenProvider;

import java.util.Collections;
import java.util.Objects;

import static java.lang.String.format;

@Slf4j
@Service
@AllArgsConstructor
public class AuthorizationService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final RoleService roleService;

    public LoginResponseDto login(final LoginDto loginDto) {
        try {
            var login = loginDto.getLogin();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login,
                    loginDto.getPassword()));

            var user = userService.getUserByLogin(login);
            if (Objects.isNull(user)) {
                throw new UsernameNotFoundException(format("User with login: %s not found", login));
            }

            var token = jwtTokenProvider.createToken(login, Collections.singletonList(user.getUserRoles()));
            return new LoginResponseDto(login, token);
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid credentials"); //todo think about exceptions here
        }
    }

    public String register(final RegistrationDto registrationDto) {
        var login = registrationDto.getLogin();
        if (Objects.nonNull(userService.getUserByLogin(login))) {
            throw new BadCredentialsException(format("Пользователь с логином %s уже существует", login));
        }

        var role = roleService.getRoleById(registrationDto.getUserRoles());
        if (Objects.isNull(role)) {
            throw new BadCredentialsException("Роль не существует");
        }

        var user = new User();
        user.setLogin(registrationDto.getLogin());
        user.setPassword(registrationDto.getPassword());
        user.setUserRoles(role);

        var createdUser = userService.createUser(user, role);
        if (createdUser == null) {
            throw new BadCredentialsException("Пользователь не может быть создан");
        }

        log.info("User was created successfully");
        return "Пользователь был успешно создан";
    }
}
