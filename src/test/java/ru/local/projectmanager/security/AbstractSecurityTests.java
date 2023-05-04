package ru.local.projectmanager.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.local.projectmanager.config.WebSecurityConfig;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@Import(WebSecurityConfig.class)
@AutoConfigureMockMvc
public class AbstractSecurityTests {

    @Autowired
    protected MockMvc mockMvc;
}
