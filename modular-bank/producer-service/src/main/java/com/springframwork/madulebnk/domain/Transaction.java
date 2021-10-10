package com.springframwork.madulebnk.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.springframwork.madulebnk.enums.TransactionType;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Component
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = Transaction.class)
public class Transaction extends BaseObject {
    private int transactionId;

    @NotNull(message = "AccountId requires valid value")
    private Integer accountId;

    @NotNull(message = "Amount requires valid value")
    private Long amount;

    private String currency;

    private TransactionType directionOfTransaction;

    @NotNull(message = "Description requires valid value")
    @NotEmpty(message = "Description requires non empty value")
    @Pattern(regexp = "[A-Za-z. ]*", message = "Description requires valid character")
    private String description;

    private Long balanceAfterTransaction;

    public Transaction(int transactionId, Integer accountId, Long amount, String currency, TransactionType directionOfTransaction, String description, Long balanceAfterTransaction) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.amount = amount;
        this.currency = currency;
        this.directionOfTransaction = directionOfTransaction;
        this.description = description;
        this.balanceAfterTransaction = balanceAfterTransaction;
    }

    public Transaction() {

    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public TransactionType getDirectionOfTransaction() {
        return directionOfTransaction;
    }

    public void setDirectionOfTransaction(TransactionType directionOfTransaction) {
        this.directionOfTransaction = directionOfTransaction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getBalanceAfterTransaction() {
        return balanceAfterTransaction;
    }

    public void setBalanceAfterTransaction(Long balanceAfterTransaction) {
        this.balanceAfterTransaction = balanceAfterTransaction;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", accountId=" + accountId +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", directionOfTransaction=" + directionOfTransaction +
                ", description='" + description + '\'' +
                ", balanceAfterTransaction=" + balanceAfterTransaction +
                '}';
    }
}
