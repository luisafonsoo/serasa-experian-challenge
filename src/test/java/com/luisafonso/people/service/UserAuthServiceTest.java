package com.luisafonso.people.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.luisafonso.people.model.UserAuth;
import com.luisafonso.people.repository.UserAuthRepository;
import com.luisafonso.people.service.UserAuthService;

@ExtendWith(MockitoExtension.class)

public class UserAuthServiceTest {

    @Mock
    private UserAuthRepository authRepository;

    @InjectMocks
    private UserAuthService authService;

    private UserAuth userAuth;

    @BeforeEach
    public void setup() {
        userAuth = UserAuth.builder().username("username").password("password")
                .build();
    }

    @DisplayName("JUnit test for loadUserByUsername method")
    @Test
    public void givenUsername_whenLoadUserByUsername_thenThrownException() {

        when(authRepository.findByUsername(anyString()))
                .thenReturn(Optional.empty());

        Throwable exception = assertThrows(UsernameNotFoundException.class,
                () -> authService.loadUserByUsername("TESTE"));
        assertEquals("User not found", exception.getMessage());

    }

    @DisplayName("JUnit test for loadUserByUsername method")
    @Test
    public void givenUsername_whenLoadUserByUsername_thenReturnNotNull() {

        try {
            when(authRepository.findByUsername(anyString()))
                    .thenReturn(Optional.of(userAuth));

            UserDetails user = authService.loadUserByUsername("TESTE");

            assertNotNull(user);

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

}
