package com.inahon.OnlineBankingSystem;
import com.inahon.OnlineBankingSystem.model.AccountUser;
import com.inahon.OnlineBankingSystem.repository.AccountUserRepository;
import com.inahon.OnlineBankingSystem.service.Impl.AccountUserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class AccountUserServiceTest {
    @Mock
    private AccountUserRepository accountUserRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AccountUserServiceImpl accountUserService;

    private AccountUser testUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testUser = new AccountUser();
        testUser.setId(1L);
        testUser.setUsername("testuser");
        testUser.setPassword("rawpassword");
    }

    @Test
    void testRegisterUser() {
        when(passwordEncoder.encode(anyString())).thenReturn("hashedpassword");
        when(accountUserRepository.save(any(AccountUser.class))).thenReturn(testUser);

        AccountUser registeredUser = accountUserService.registerUser(testUser);

        assertNotNull(registeredUser);
        assertEquals("testuser", registeredUser.getUsername());
        verify(accountUserRepository, times(1)).save(any(AccountUser.class));
    }

    @Test
    void testLoginUser_Success() {
        when(accountUserRepository.findByUsername("testuser")).thenReturn(testUser);
        when(passwordEncoder.matches("rawpassword", "rawpassword")).thenReturn(true);

        AccountUser loggedInUser = accountUserService.loginUser("testuser", "rawpassword");

        assertNotNull(loggedInUser);
        assertEquals("testuser", loggedInUser.getUsername());
        verify(accountUserRepository, times(1)).findByUsername("testuser");
    }

    @Test
    void testLoginUser_Failure() {
        when(accountUserRepository.findByUsername("testuser")).thenReturn(testUser);
        when(passwordEncoder.matches("wrongpassword", "rawpassword")).thenReturn(false);

        Exception exception = assertThrows(RuntimeException.class, () ->
                accountUserService.loginUser("testuser", "wrongpassword")
        );

        assertEquals("Invalid username or password", exception.getMessage());
    }
}
