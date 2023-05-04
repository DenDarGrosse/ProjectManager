package ru.local.projectmanager.security;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.local.projectmanager.config.WebSecurityConfig;
import ru.local.projectmanager.controller.RegistrationController;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RegistrationController.class)
public class RegistrationSecurityTests extends AbstractSecurityTests{

    @Test
    @WithAnonymousUser
    void cannotGetCustomerIfNotAuthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/customer/{id}", 1L))
                .andExpect(status().isUnauthorized());
    }
}
