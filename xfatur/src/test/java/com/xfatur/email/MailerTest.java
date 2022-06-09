package com.xfatur.email;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.base.BaseTest;

@DisplayName("E-mail")
class MailerTest extends BaseTest {

    @Autowired
    Mailer mailer;

    @Test
    void test() {

    }

}
