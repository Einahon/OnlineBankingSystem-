package com.inahon.OnlineBankingSystem.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;



@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private BankAccount account;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private LocalDateTime timestamp;

    // Private constructor to enforce the use of Builder
    public Transaction(TransactionBuilder builder) {
        this.id = builder.id;
        this.account = builder.account;
        this.amount = builder.amount;
        this.type = builder.type;
        this.timestamp = builder.timestamp;
    }

    public Transaction() {

    }

    // Static inner Builder class
    public static class TransactionBuilder {
        private Long id;
        private BankAccount account;
        private Double amount;
        private TransactionType type;
        private LocalDateTime timestamp;

        public TransactionBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public TransactionBuilder account(BankAccount account) {
            this.account = account;
            return this;
        }

        public TransactionBuilder amount(Double amount) {
            this.amount = amount;
            return this;
        }

        public TransactionBuilder type(TransactionType type) {
            this.type = type;
            return this;
        }

        public TransactionBuilder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Transaction build() {
            return new Transaction(this);
        }
    }

    // Static method to get builder
    public static TransactionBuilder builder() {
        return new TransactionBuilder();
    }

    }

