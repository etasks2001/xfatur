package com.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.xfatur.XFaturApplication;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = { XFaturApplication.class })
@AutoConfigureMockMvc
@WithMockUser(username = "msergiost@homail.com", authorities = { "FINANCEIRO", "FATURAMENTO", "FISCAL" })
@ActiveProfiles("dev")
public abstract class BaseTest {

    @Autowired
    protected MockMvc mock;

}
