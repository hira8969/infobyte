package com.atm.service;

import com.atm.exception.AuthenticationException;
import com.atm.repository.BankRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AuthenticationServiceTest {
    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp() {
        authenticationService = new AuthenticationService(BankRepository.createWithSampleData());
    }

    @Test
    void loginSuccess() {
        assertNotNull(authenticationService.login("1001", "1234"));
        assertTrue(authenticationService.isLoggedIn());
    }

    @Test
    void loginFailure() {
        assertThrows(AuthenticationException.class, () -> authenticationService.login("1001", "9999"));
        assertEquals(1, authenticationService.getFailedAttempts("1001"));
    }

    @Test
    void threeFailedAttemptsLocksSession() {
        for (int i = 0; i < 3; i++) {
            assertThrows(AuthenticationException.class, () -> authenticationService.login("1001", "9999"));
        }

        AuthenticationException exception = assertThrows(AuthenticationException.class,
                () -> authenticationService.login("1001", "1234"));
        assertTrue(exception.getMessage().contains("locked"));
    }
}
