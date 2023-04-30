package ru.local.projectmanager.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.local.projectmanager.router.dto.RegistrationDto;
import ru.local.projectmanager.service.AuthorizationService;

import static ru.local.projectmanager.router.Router.REGISTRATION_ENDPOINT;

@Log4j2
@Controller
@RequestMapping(value = REGISTRATION_ENDPOINT)
@AllArgsConstructor
public class RegistrationController {
    private final AuthorizationService authorizationService;

    @PostMapping
    public ResponseEntity<String> registration(@RequestBody RegistrationDto registrationDto) {
        var response = authorizationService.register(registrationDto);
        return ResponseEntity.ok().body(response);
    }

}
