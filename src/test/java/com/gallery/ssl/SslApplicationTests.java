package com.gallery.ssl;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class SslApplicationTests {

    @Autowired
    private SslApplication sslApplication;

    @Test
    void contextLoads() {
    }

    @Test
    public void testPasswordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = sslApplication.passwordEncoder();
        Assert.assertNotEquals(bCryptPasswordEncoder.encode("1234"), "");
    }
}
