package com.inahon.OnlineBankingSystem;

import com.inahon.OnlineBankingSystem.controller.BankAccountController;
import com.inahon.OnlineBankingSystem.service.BankAccountService;
import com.inahon.OnlineBankingSystem.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BankAccountControllerTest {
    private MockMvc mockMvc;

    @Mock
    private BankAccountService bankAccountService;

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private BankAccountController bankAccountController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bankAccountController).build();
    }

    @Test
    void testGetBalance() throws Exception {
        when(bankAccountService.checkBalance(1L)).thenReturn(500.0);

        mockMvc.perform(get("/accounts/1/balance"))
                .andExpect(status().isOk())
                .andExpect(content().string("500.0"));

        verify(bankAccountService, times(1)).checkBalance(1L);
    }

    @Test
    void testDeposit() throws Exception {
        mockMvc.perform(post("/accounts/1/deposit")
                        .param("amount", "200.0")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(content().string("Deposit of $200.0 successful."));

        verify(bankAccountService, times(1)).deposit(1L, 200.0);
        verify(transactionService, times(1)).recordTransaction(1L, 200.0, "DEPOSIT");
    }

    @Test
    void testWithdraw() throws Exception {
        mockMvc.perform(post("/accounts/1/withdraw")
                        .param("amount", "100.0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Withdrawal of $100.0 successful."));

        verify(bankAccountService, times(1)).withdraw(1L, 100.0);
        verify(transactionService, times(1)).recordTransaction(1L, 100.0, "WITHDRAWAL");
    }

}
