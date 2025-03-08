package com.inahon.OnlineBankingSystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class AccountUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fullName;
    @Column(unique = true, nullable = false)
    private String email;
    private String phone;
    private String password;
    @OneToOne(mappedBy = "accountUser", cascade = CascadeType.ALL)
    private BankAccount bankAccount;
}
