package ru.local.projectmanager.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.local.projectmanager.router.dto.LoginDto;
import ru.local.projectmanager.router.dto.LoginResponseDto;
import ru.local.projectmanager.service.AuthorizationService;

import static ru.local.projectmanager.router.Router.LOGIN_ENDPOINT;

@Controller
@RequestMapping(value = LOGIN_ENDPOINT)
@AllArgsConstructor
public class LoginController {
    private final AuthorizationService authorizationService;

    @PostMapping
    public ResponseEntity<LoginResponseDto> proceedLogin(@RequestBody LoginDto loginDto) {
        var response = authorizationService.login(loginDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
